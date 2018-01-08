using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Xml;
using System.IO;

using System.Runtime;
using System.Runtime.InteropServices;
using System.Reflection;

namespace WindowsApplication1
{
    public partial class Form1 : Form
    {
        //关闭屏保
        const uint SPI_SETSCREENSAVEACTIVE = 0x0011;
        [System.Runtime.InteropServices.DllImport("User32")]
        extern static bool SystemParametersInfoW(uint uiAction, uint uiParam, IntPtr pvParam, uint fWinIni);
        [StructLayout(LayoutKind.Sequential, Pack = 1)]
        internal struct TokPriv1Luid
        {
            public int Count;
            public long Luid;
            public int Attr;
        }
        [DllImport("kernel32.dll", ExactSpelling = true)]
        internal static extern IntPtr GetCurrentProcess();

        [DllImport("advapi32.dll", ExactSpelling = true, SetLastError = true)]
        internal static extern bool OpenProcessToken(IntPtr h, int acc, ref IntPtr phtok);

        [DllImport("advapi32.dll", SetLastError = true)]
        internal static extern bool LookupPrivilegeValue(string host, string name, ref long pluid);

        [DllImport("advapi32.dll", ExactSpelling = true, SetLastError = true)]
        internal static extern bool AdjustTokenPrivileges(IntPtr htok, bool disall,
         ref TokPriv1Luid newst, int len, IntPtr prev, IntPtr relen);

        [DllImport("user32.dll", ExactSpelling = true, SetLastError = true)]
        internal static extern bool ExitWindowsEx(int flg, int rea);

        internal const int SE_PRIVILEGE_ENABLED = 0x00000002;
        internal const int TOKEN_QUERY = 0x00000008;
        internal const int TOKEN_ADJUST_PRIVILEGES = 0x00000020;
        internal const string SE_SHUTDOWN_NAME = "SeShutdownPrivilege";
        internal const int EWX_LOGOFF = 0x00000000;
        internal const int EWX_SHUTDOWN = 0x00000001;
        internal const int EWX_REBOOT = 0x00000002;
        internal const int EWX_FORCE = 0x00000004;
        internal const int EWX_POWEROFF = 0x00000008;
        internal const int EWX_FORCEIFHUNG = 0x00000010;


        //获得最后输入时间
        internal struct LASTINPUTINFO
        {
            public uint cbSize;

            public uint dwTime;
        }

        [DllImport("User32.dll")]
        private static extern bool GetLastInputInfo(ref LASTINPUTINFO plii);

        //url ,返回时间
        string strUrl = "";
        string strReturnTime = "";
        string strOffTime = "";
        string strExitkey = "";
        //private static long t1 = 0;
        //private static long t2 = 0;
        private static string exitkey = "";
        private static long ht = 30;
        private bool setoff = true;
        LASTINPUTINFO LastInputInfo = new LASTINPUTINFO();
        private static int djs = 0;
        public Form1()
        {
            InitializeComponent();
            try
            {
                //url ,返回时间
                strUrl = GetConfigValue("starturl");
                strReturnTime = GetConfigValue("returntime");
                strOffTime = GetConfigValue("offtime");
                strExitkey = GetConfigValue("exitkey");
                //全屏
                this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
                this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
                this.TopMost = true;
                int w=0; //宽
                int h=0; //高
                djs=int.Parse(strReturnTime) ;
                label2.Text = djs.ToString();

                //取得屏幕分辨率
                Screen[] screens = Screen.AllScreens;
                int upperBound = screens.GetUpperBound(0);
                for (int index = 0; index <= upperBound; index++)
                {
                    h = screens[index].Bounds.Height;
                    w = screens[index].Bounds.Width;
                }
                panel1.Left = w / 2 - 117;//
                panel1.Top = h / 2 -100;


                //首页地址
                webBrowser1.Url = new Uri(strUrl);

                SystemParametersInfoW(SPI_SETSCREENSAVEACTIVE, 0, IntPtr.Zero, 0);  //禁用屏保
                // SystemParametersInfoW(SPI_SETSCREENSAVEACTIVE, 1, IntPtr.Zero, 0);  //启用屏保

                //定时
                timer1.Interval = 1000;
                timer1.Enabled = true;
                timer2.Interval = 1000;
                timer2.Enabled = true;

                Browse.UserActivityHook uah = new Browse.UserActivityHook();
                uah.KeyPress += new KeyPressEventHandler(MyKeyPress);
            }
            catch (Exception e)
            {
                MessageBox.Show("错误：" + e.ToString());
            }

        }

        //按照设定的密码退出程序
        public void MyKeyPress(object sender, KeyPressEventArgs e)
        {
            int keylen = strExitkey.Length;
            
            exitkey += e.KeyChar;
            if (exitkey.Length == keylen)
            {
                if (strExitkey.Equals(exitkey))
                {
                    exitkey = "";
                    ExitApp();
                }
                exitkey = "";

            }
            if (e.KeyChar == (char)'*') {
                exitkey = "";
            }
        }


        //获得配置文件的值
        protected string GetConfigValue(string configName)
        {
            XmlDocument xmlDoc = new XmlDocument();

            xmlDoc.Load(Application.StartupPath + "/SystemConfig.xml");

            XmlNodeList nodeList = xmlDoc.SelectSingleNode("systemConfig").ChildNodes;//获取systemConfig节点的所有子节点

            foreach (XmlNode xn in nodeList)//遍历所有子节点
            {
                if (xn.Name == configName)
                {
                    return xn.InnerText;
                }
            }

            return "";

        }
        //判断多长时间无操作返回首页
        private void timer1_Tick(object sender, EventArgs e)
        {
            LastInputInfo.cbSize = (uint)System.Runtime.InteropServices.Marshal.SizeOf(LastInputInfo);
            GetLastInputInfo(ref LastInputInfo);
            uint i = ((uint)Environment.TickCount - LastInputInfo.dwTime);
            if (webBrowser1.Url != new Uri(strUrl))
            {
                //11MessageBox.Show((i / 1000).ToString());
                int vl = 0;
                if ((i / 1000).ToString() != "0")
                {
                    vl = djs - int.Parse((i / 1000).ToString());
                    label2.Text = (vl.ToString());
                }
                else
                {
                    label2.Text = djs.ToString();
                }
                if (label2.Visible == false) { label2.Visible = true;label2.BackColor=System.Drawing.Color.Transparent; }
            }
            else
            {
                if (label2.Visible == true) { label2.Visible = false; }
                label2.Text = djs.ToString(); 
            }
            if ((i / 1000).ToString() == (djs).ToString())
            {
                //首页地址
                webBrowser1.Url = new Uri(strUrl);
                if (label2.Visible == true) { label2.Visible = false; }
                label2.Text = djs.ToString();
                
            }

        }
        //每日定时关机；及周六周日时直接提示关机可取消关机；
        private void timer2_Tick(object sender, EventArgs e)
        {

            DateTime nowtime = DateTime.Now;
            DateTime offtime = Convert.ToDateTime(strOffTime);
            //TimeSpan diff1 = offtime.Subtract(nowtime);

            int intw = (int)nowtime.DayOfWeek;//数字   

            if ((intw == 0 || intw == 6) && setoff)
            {
                panel1.Visible = true;
                timer3.Interval = 1000;
                timer3.Enabled = true;
            }
            if (nowtime.Hour==offtime.Hour && nowtime.Minute==offtime.Minute&&nowtime.Second==offtime.Second)
            {
                panel1.Visible = true;

                timer3.Interval = 1000;
                timer3.Enabled = true;
                
            }
        }
        //关机延时
        private void timer3_Tick(object sender, EventArgs e)
        {

            ht = ht - 1;
            label1.Text = "电脑将在" + ht + "秒后关机...";
            if (ht == 0)
            {
                DoExitWin(1); //执行关机事件
            }


        }
        private void DoExitWin(int flg)
        {
            bool ok;
            TokPriv1Luid tp;
            IntPtr hproc = GetCurrentProcess();
            IntPtr htok = IntPtr.Zero;
            ok = OpenProcessToken(hproc, TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY, ref htok);
            tp.Count = 1;
            tp.Luid = 0;
            tp.Attr = SE_PRIVILEGE_ENABLED;
            ok = LookupPrivilegeValue(null, SE_SHUTDOWN_NAME, ref tp.Luid);
            ok = AdjustTokenPrivileges(htok, false, ref tp, 0, IntPtr.Zero, IntPtr.Zero);
            ok = ExitWindowsEx(flg, 0);
        }

        public void Reboot()
        {
            DoExitWin(EWX_REBOOT);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            DoExitWin(1); //执行关机事件
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ht = 30;
            timer3.Enabled = false;
            setoff = false;
            panel1.Visible = false;
            label1.Text = "电脑将在30秒后关机...";
        }

        public void ExitApp()
        {
            MessageBoxButtons messButton = MessageBoxButtons.OKCancel;
            DialogResult dr = MessageBox.Show("确定要退出触摸屏浏览器？", "退出程序", messButton);
            if (dr == DialogResult.OK)//如果点击“确定”按钮
            {  
                Application.ExitThread();

            }


        }

       




    }

}