VERSION 5.00
Begin VB.Form Form3 
   BorderStyle     =   3  'Fixed Dialog
   Caption         =   "FWeight 称重管理软件 beta 1.0"
   ClientHeight    =   7170
   ClientLeft      =   150
   ClientTop       =   540
   ClientWidth     =   11685
   ControlBox      =   0   'False
   Icon            =   "Form3.frx":0000
   LinkTopic       =   "Form3"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   Moveable        =   0   'False
   ScaleHeight     =   7170
   ScaleWidth      =   11685
   StartUpPosition =   2  '屏幕中心
   WindowState     =   2  'Maximized
   Begin VB.Frame Frame7 
      Height          =   6975
      Left            =   120
      TabIndex        =   51
      Top             =   240
      Width           =   11535
      Begin VB.Frame Frame11 
         Caption         =   "经营户"
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   10.5
            Charset         =   134
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   6495
         Left            =   3360
         TabIndex        =   60
         Top             =   240
         Width           =   3015
         Begin VB.CommandButton Command10 
            Caption         =   "添加"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   1560
            TabIndex        =   65
            Top             =   4320
            Width           =   975
         End
         Begin VB.CommandButton Command7 
            Caption         =   "删除"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   1560
            TabIndex        =   63
            Top             =   5280
            Width           =   975
         End
         Begin VB.TextBox tt2 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   405
            Left            =   1440
            TabIndex        =   62
            Top             =   3480
            Width           =   1335
         End
         Begin VB.ListBox txtjingying 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   5580
            Left            =   120
            TabIndex        =   61
            Top             =   360
            Width           =   1215
         End
      End
      Begin VB.Frame Frame12 
         Caption         =   "货物信息"
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   10.5
            Charset         =   134
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   6495
         Left            =   120
         TabIndex        =   56
         Top             =   240
         Width           =   3135
         Begin VB.CommandButton Command9 
            Caption         =   "添加"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   1560
            TabIndex        =   64
            Top             =   4320
            Width           =   975
         End
         Begin VB.CommandButton Command8 
            Caption         =   "删除"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   1560
            TabIndex        =   59
            Top             =   5280
            Width           =   975
         End
         Begin VB.TextBox tt1 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   405
            Left            =   1440
            TabIndex        =   58
            Top             =   3480
            Width           =   1335
         End
         Begin VB.ListBox txthuo 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   5340
            ItemData        =   "Form3.frx":1CFA
            Left            =   120
            List            =   "Form3.frx":1CFC
            TabIndex        =   57
            Top             =   480
            Width           =   1215
         End
      End
      Begin VB.Frame Frame10 
         Caption         =   "发货单位"
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   10.5
            Charset         =   134
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   6495
         Left            =   6480
         TabIndex        =   52
         Top             =   240
         Width           =   4815
         Begin VB.CommandButton Command11 
            Caption         =   "添加"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   3000
            TabIndex        =   66
            Top             =   4320
            Width           =   975
         End
         Begin VB.CommandButton Command6 
            Caption         =   "删除"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   3000
            TabIndex        =   55
            Top             =   5280
            Width           =   975
         End
         Begin VB.TextBox tt3 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   420
            Left            =   2040
            TabIndex        =   54
            Top             =   3480
            Width           =   2655
         End
         Begin VB.ListBox txtfahuo 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   5820
            Left            =   120
            TabIndex        =   53
            Top             =   360
            Width           =   1815
         End
      End
   End
   Begin VB.Frame Frame1 
      Caption         =   "用户设置"
      BeginProperty Font 
         Name            =   "宋体"
         Size            =   10.5
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   6615
      Left            =   120
      TabIndex        =   9
      Top             =   240
      Width           =   5295
      Begin VB.ListBox List2 
         Enabled         =   0   'False
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   12
            Charset         =   134
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   1740
         ItemData        =   "Form3.frx":1CFE
         Left            =   2760
         List            =   "Form3.frx":1D00
         TabIndex        =   39
         Top             =   600
         Width           =   1695
      End
      Begin VB.Frame Frame6 
         Height          =   4095
         Left            =   240
         TabIndex        =   11
         Top             =   2400
         Width           =   4695
         Begin VB.CheckBox Check1 
            Alignment       =   1  'Right Justify
            Caption         =   "是否管理员"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   2400
            TabIndex        =   50
            Top             =   2040
            Width           =   1815
         End
         Begin VB.Frame Frame9 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   10.5
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   1695
            Left            =   120
            TabIndex        =   46
            Top             =   2400
            Width           =   4335
            Begin VB.TextBox txtyuan 
               BeginProperty Font 
                  Name            =   "宋体"
                  Size            =   12
                  Charset         =   134
                  Weight          =   400
                  Underline       =   0   'False
                  Italic          =   0   'False
                  Strikethrough   =   0   'False
               EndProperty
               Height          =   420
               IMEMode         =   3  'DISABLE
               Left            =   2280
               PasswordChar    =   "*"
               TabIndex        =   49
               Top             =   690
               Width           =   1815
            End
            Begin VB.CommandButton Command2 
               Caption         =   "修改密码"
               BeginProperty Font 
                  Name            =   "宋体"
                  Size            =   12
                  Charset         =   134
                  Weight          =   400
                  Underline       =   0   'False
                  Italic          =   0   'False
                  Strikethrough   =   0   'False
               EndProperty
               Height          =   375
               Left            =   120
               TabIndex        =   47
               Top             =   720
               Width           =   1215
            End
            Begin VB.Label Label16 
               Caption         =   "原密码"
               BeginProperty Font 
                  Name            =   "宋体"
                  Size            =   12
                  Charset         =   134
                  Weight          =   400
                  Underline       =   0   'False
                  Italic          =   0   'False
                  Strikethrough   =   0   'False
               EndProperty
               Height          =   255
               Left            =   1440
               TabIndex        =   48
               Top             =   840
               Width           =   855
            End
         End
         Begin VB.TextBox txtch 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   405
            IMEMode         =   3  'DISABLE
            Left            =   2400
            PasswordChar    =   "*"
            TabIndex        =   45
            Top             =   1320
            Width           =   1815
         End
         Begin VB.TextBox txtxin 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   405
            IMEMode         =   3  'DISABLE
            Left            =   2400
            PasswordChar    =   "*"
            TabIndex        =   44
            Top             =   840
            Width           =   1815
         End
         Begin VB.TextBox txtname 
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   420
            Left            =   2400
            TabIndex        =   41
            Top             =   330
            Width           =   1815
         End
         Begin VB.CommandButton Command3 
            Caption         =   "添加"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   360
            TabIndex        =   13
            Top             =   1080
            Width           =   855
         End
         Begin VB.CommandButton Command1 
            Caption         =   "删除"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   495
            Left            =   360
            TabIndex        =   12
            Top             =   360
            Width           =   855
         End
         Begin VB.Label Label18 
            Caption         =   "重输入"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   1440
            TabIndex        =   43
            Top             =   1320
            Width           =   975
         End
         Begin VB.Label Label17 
            Caption         =   "新密码"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   1440
            TabIndex        =   42
            Top             =   840
            Width           =   1095
         End
         Begin VB.Label Label15 
            Caption         =   "司磅员"
            BeginProperty Font 
               Name            =   "宋体"
               Size            =   12
               Charset         =   134
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   255
            Left            =   1440
            TabIndex        =   40
            Top             =   360
            Width           =   975
         End
      End
      Begin VB.ListBox List1 
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   12
            Charset         =   134
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   1740
         ItemData        =   "Form3.frx":1D02
         Left            =   360
         List            =   "Form3.frx":1D04
         TabIndex        =   10
         Top             =   600
         Width           =   2415
      End
      Begin VB.Label Label5 
         Caption         =   "管理员"
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   12
            Charset         =   134
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   255
         Left            =   3000
         TabIndex        =   15
         Top             =   240
         Width           =   735
      End
      Begin VB.Label Label4 
         Caption         =   "司磅员"
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   12
            Charset         =   134
            Weight          =   400
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   255
         Left            =   960
         TabIndex        =   14
         Top             =   240
         Width           =   855
      End
   End
   Begin VB.Frame Frame2 
      Caption         =   "参数设置"
      Enabled         =   0   'False
      BeginProperty Font 
         Name            =   "宋体"
         Size            =   10.5
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   6615
      Left            =   5640
      TabIndex        =   1
      Top             =   240
      Width           =   5895
      Begin VB.Frame Frame13 
         Height          =   2295
         Left            =   240
         TabIndex        =   67
         Top             =   4200
         Width           =   5175
      End
      Begin VB.CommandButton Command5 
         Caption         =   "设置"
         Height          =   375
         Left            =   1440
         TabIndex        =   29
         Top             =   2400
         Width           =   975
      End
      Begin VB.CommandButton Command4 
         Caption         =   "打开端口"
         Height          =   375
         Left            =   240
         TabIndex        =   28
         Top             =   2400
         Width           =   975
      End
      Begin VB.Frame Frame8 
         Caption         =   "串口(&C)"
         Height          =   1095
         Left            =   240
         TabIndex        =   16
         Top             =   1080
         Width           =   2415
         Begin VB.OptionButton Option4 
            Caption         =   "COM4"
            Enabled         =   0   'False
            Height          =   375
            Left            =   1200
            TabIndex        =   19
            Top             =   600
            Width           =   735
         End
         Begin VB.OptionButton Option3 
            Caption         =   "COM3"
            Enabled         =   0   'False
            Height          =   255
            Left            =   1200
            TabIndex        =   18
            Top             =   240
            Width           =   855
         End
         Begin VB.OptionButton Option2 
            Caption         =   "COM2"
            Height          =   375
            Left            =   240
            TabIndex        =   17
            Top             =   600
            Width           =   735
         End
         Begin VB.OptionButton Option1 
            Caption         =   "COM1"
            Height          =   255
            Left            =   240
            TabIndex        =   0
            Top             =   240
            Value           =   -1  'True
            Width           =   855
         End
      End
      Begin VB.Frame Frame3 
         Height          =   3975
         Left            =   2880
         TabIndex        =   2
         Top             =   120
         Width           =   2535
         Begin VB.ComboBox Combo1 
            Height          =   300
            Left            =   1080
            TabIndex        =   8
            Text            =   "本机默认"
            Top             =   240
            Width           =   1215
         End
         Begin VB.Frame Frame4 
            Caption         =   "截取值设置(&K)"
            Height          =   1455
            Left            =   120
            TabIndex        =   4
            Top             =   600
            Width           =   2295
            Begin VB.TextBox Text4 
               Height          =   270
               Left            =   1080
               TabIndex        =   27
               Top             =   1080
               Width           =   855
            End
            Begin VB.TextBox Text3 
               Height          =   270
               Left            =   1080
               TabIndex        =   26
               Top             =   840
               Width           =   855
            End
            Begin VB.TextBox Text2 
               Height          =   270
               Left            =   1080
               TabIndex        =   25
               Top             =   600
               Width           =   855
            End
            Begin VB.TextBox Text1 
               Height          =   270
               Left            =   1080
               TabIndex        =   21
               Top             =   360
               Width           =   855
            End
            Begin VB.Label Label9 
               Caption         =   "有效位数"
               Height          =   255
               Left            =   240
               TabIndex        =   24
               Top             =   1080
               Width           =   855
            End
            Begin VB.Label Label8 
               Caption         =   "开始长度"
               Height          =   255
               Left            =   240
               TabIndex        =   23
               Top             =   840
               Width           =   1095
            End
            Begin VB.Label Label7 
               Caption         =   "有效长度"
               Height          =   255
               Left            =   240
               TabIndex        =   22
               Top             =   600
               Width           =   975
            End
            Begin VB.Label Label6 
               Caption         =   "关 键 字"
               Height          =   255
               Left            =   240
               TabIndex        =   20
               Top             =   360
               Width           =   855
            End
         End
         Begin VB.Frame Frame5 
            Caption         =   "串口设置(&B)"
            Height          =   1695
            Left            =   120
            TabIndex        =   3
            Top             =   2160
            Width           =   2295
            Begin VB.ComboBox Combo5 
               Height          =   300
               ItemData        =   "Form3.frx":1D06
               Left            =   1080
               List            =   "Form3.frx":1D22
               TabIndex        =   38
               Text            =   "1"
               Top             =   1320
               Width           =   855
            End
            Begin VB.ComboBox Combo4 
               Height          =   300
               ItemData        =   "Form3.frx":1D3E
               Left            =   1080
               List            =   "Form3.frx":1D4E
               TabIndex        =   37
               Text            =   "7"
               Top             =   960
               Width           =   855
            End
            Begin VB.ComboBox Combo3 
               Height          =   300
               ItemData        =   "Form3.frx":1D5E
               Left            =   1080
               List            =   "Form3.frx":1D68
               TabIndex        =   36
               Text            =   "e"
               Top             =   600
               Width           =   855
            End
            Begin VB.ComboBox Combo2 
               Height          =   300
               ItemData        =   "Form3.frx":1D72
               Left            =   1080
               List            =   "Form3.frx":1D85
               TabIndex        =   35
               Text            =   "9600"
               Top             =   240
               Width           =   855
            End
            Begin VB.Label Label14 
               Caption         =   "停 止 位"
               Height          =   255
               Left            =   240
               TabIndex        =   34
               Top             =   1320
               Width           =   735
            End
            Begin VB.Label Label13 
               Caption         =   "数 据 位"
               Height          =   255
               Left            =   240
               TabIndex        =   33
               Top             =   960
               Width           =   735
            End
            Begin VB.Label Label12 
               Caption         =   "奇偶校验"
               Height          =   255
               Left            =   240
               TabIndex        =   32
               Top             =   600
               Width           =   735
            End
            Begin VB.Label Label11 
               Caption         =   "波 特 率"
               Height          =   255
               Left            =   240
               TabIndex        =   31
               Top             =   240
               Width           =   735
            End
         End
         Begin VB.Label Label3 
            Caption         =   "默认参数:"
            Height          =   255
            Left            =   240
            TabIndex        =   7
            Top             =   240
            Width           =   975
         End
      End
      Begin VB.Label Label10 
         BorderStyle     =   1  'Fixed Single
         Height          =   1095
         Left            =   240
         TabIndex        =   30
         Top             =   2880
         Width           =   2415
      End
      Begin VB.Label Label2 
         Alignment       =   2  'Center
         Appearance      =   0  'Flat
         BackColor       =   &H80000005&
         BorderStyle     =   1  'Fixed Single
         Caption         =   "5212117"
         BeginProperty Font 
            Name            =   "宋体"
            Size            =   26.25
            Charset         =   134
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H00FF0000&
         Height          =   615
         Left            =   240
         TabIndex        =   5
         Top             =   360
         Width           =   2415
      End
   End
   Begin VB.Label Label1 
      Caption         =   "Label1"
      Height          =   495
      Left            =   7560
      TabIndex        =   6
      Top             =   6960
      Visible         =   0   'False
      Width           =   975
   End
   Begin VB.Menu caidan 
      Caption         =   "菜单(&O)"
      Index           =   0
      Begin VB.Menu genggai 
         Caption         =   "更改用户(&C)"
         Index           =   1
      End
      Begin VB.Menu tuichu 
         Caption         =   "退出(&X)"
         Index           =   2
      End
   End
   Begin VB.Menu jbzl 
      Caption         =   "基本资料(&J)"
      Index           =   6
   End
   Begin VB.Menu shj 
      Caption         =   "系统设置(&S)"
      Index           =   6
   End
   Begin VB.Menu bangzhu 
      Caption         =   "帮助(&H)"
      Index           =   3
      Begin VB.Menu shiyong 
         Caption         =   "使用帮助"
         Index           =   4
         Shortcut        =   {F1}
      End
      Begin VB.Menu guanyu 
         Caption         =   "关于软件"
         Index           =   5
      End
   End
End
Attribute VB_Name = "Form3"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim cnnUser As New ADODB.Connection
Dim rstUser As New ADODB.Recordset

Private Sub Command1_Click()
 Dim xname As String
 Dim tmpop As String
 If List1.List(List1.ListIndex) <> "" Then
 tmpop = MsgBox("确定删除？", vbOKCancel, "提示：")
 If tmpop = 1 Then
     xname = Trim(List1.List(List1.ListIndex))
     cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
     "Data Source=" & App.Path & "\db.mpp;" & _
     "Mode= ReadWrite | Share Deny None"
     cnnUser.Open
     With rstUser
     Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
     End With
     With rstUser
     If .State = adOpenKeyset Then .Close
     .Open "select * from [admin] where [name]='" & xname & "'", cnnUser, adOpenKeyset, adLockOptimistic
     .Delete
     .Update
     .Close
     End With
     cnnUser.Close
     List1.Clear
     List2.Clear
     data
 End If
 Else
 MsgBox "请选择用户！", vbOKOnly, "提示："
 End If
 
End Sub

Private Sub data()
  Dim cc As String
  Dim i As Integer
  Dim dd As String
  
  cc = Label1.Caption
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
     If .State = adStateOpen Then .Close
     .Open ("Select * from admin")
      
      Do While Not .EOF
      dd = ""
      If rstUser.Fields("adm").Value = 1 Then
      dd = "是"
      End If
      If rstUser.Fields("name").Value <> "admin" Then
      List1.AddItem " " & rstUser.Fields("name").Value
      List2.AddItem " " & dd
      End If
     .MoveNext
      Loop
  
  End With
   cnnUser.Close
  
End Sub


Private Sub Command10_Click()
If tt2.Text <> "" Then
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
     With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [jingyinghu] where jingying = '" & tt2.Text & "'"
     If Not .EOF Then
     MsgBox "数据已存在！", vbOKOnly, "提示："
     tt2.Text = ""
     .Close
     cnnUser.Close
     Exit Sub
     End If
     .Close
  End With
  
  
  
  With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [jingyinghu]", cnnUser, adOpenKeyset, adLockOptimistic
     .AddNew
     ![jingying] = tt2.Text
     .Update
     .Close
  End With
  cnnUser.Close
  Call ddt
 
 Else
  MsgBox "请填写内容！", vbOKOnly, "提示："
End If
tt2.Text = ""
End Sub

Private Sub Command11_Click()
If tt3.Text <> "" Then
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
     With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [fahuo] where fahuo = '" & tt3.Text & "'"
     If Not .EOF Then
     MsgBox "数据已存在！", vbOKOnly, "提示："
     tt3.Text = ""
     .Close
     cnnUser.Close
     Exit Sub
     End If
     .Close
  End With
  
  
  
  With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [fahuo]", cnnUser, adOpenKeyset, adLockOptimistic
     .AddNew
     ![fahuo] = tt3.Text
     .Update
     .Close
  End With
  cnnUser.Close
  Call ddt
  
 Else
  MsgBox "请填写内容！", vbOKOnly, "提示："
End If
tt3.Text = ""
End Sub

Private Sub Command2_Click()
     Dim xname As String
     xname = Form3.txtname.Text
     cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
     "Data Source=" & App.Path & "\db.mpp;" & _
     "Mode= ReadWrite | Share Deny None"
     cnnUser.Open
     With rstUser
     Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
     End With
     With rstUser
     If .State = adOpenKeyset Then .Close
     .Open "select * from [admin] where [name]='" & xname & "'", cnnUser, adOpenKeyset, adLockOptimistic

     If Not .EOF Then
        If decipher(rstUser.Fields("password").Value) <> Form3.txtyuan.Text Then
           MsgBox "原密码不正确！", vbOKOnly, "错误："
           cnnUser.Close
           txtyuan.Text = ""
           Exit Sub
        End If
     Else
           MsgBox "无此用户名！", vbOKOnly, "错误："
           cnnUser.Close
           Form3.txtyuan.Text = ""
           Form3.txtch.Text = ""
           Form3.txtxin.Text = ""
           Exit Sub
     End If
           ![Password] = cipher(Form3.txtxin.Text)
           .Update
           .Close
           MsgBox "修改成功", vbOKOnly, "提示："
           Form3.txtch.Text = ""
           Form3.txtxin.Text = ""
           Form3.txtyuan.Text = ""
     End With
    cnnUser.Close
End Sub

Private Sub Command3_Click()
Dim a As Integer
If Check1.Value = 1 Then
  a = 1
  Else
  a = 0
End If
If Form3.txtxin.Text = Form3.txtch.Text Then
     Dim xname As String
     xname = Form3.txtname.Text
     cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
     "Data Source=" & App.Path & "\db.mpp;" & _
     "Mode= ReadWrite | Share Deny None"
     cnnUser.Open
     With rstUser
     Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
     End With
     With rstUser
     If .State = adOpenKeyset Then .Close
     .Open "select * from [admin] where [name]='" & xname & "'"
     If Not .EOF Then
        MsgBox "已存数据！", vbOKOnly, "错误："
        Form3.txtch.Text = ""
        Form3.txtxin.Text = ""
        cnnUser.Close
        Exit Sub
     End If
     .Close
     End With
     With rstUser
     If .State = adOpenKeyset Then .Close
     .Open "select * from [admin] where [name]='" & xname & "'", cnnUser, adOpenKeyset, adLockOptimistic
     .AddNew
     ![Name] = Form3.txtname.Text
     ![Password] = cipher(Form3.txtxin.Text)
     ![adm] = a
     .Update
     .Close
     End With
     cnnUser.Close
     Form3.txtch.Text = ""
     Form3.txtxin.Text = ""
     List1.Clear
     List2.Clear
     data
Else
     MsgBox "两次密码输入不正确！", vbOKOnly, "错误："
End If
End Sub

Private Sub Command6_Click()
Dim pkey As String
pkey = txtfahuo.List(txtfahuo.ListIndex)
If pkey <> "" Then
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [fahuo] where fahuo = '" + pkey + "'", cnnUser, adOpenKeyset, adLockOptimistic
     .Delete
     .Update
     .Close
  End With
  cnnUser.Close
  Call ddt
Else
  MsgBox "请选择删除项！", vbOKOnly, "提示："
End If

End Sub

Private Sub Command7_Click()
Dim pkey As String
pkey = txtjingying.List(txtjingying.ListIndex)
If pkey <> "" Then
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [jingyinghu] where jingying = '" + pkey + "'", cnnUser, adOpenKeyset, adLockOptimistic
     .Delete
     .Update
     .Close
  End With
  cnnUser.Close
  Call ddt
Else
  MsgBox "请选择删除项！", vbOKOnly, "提示："
End If


End Sub

Private Sub Command8_Click()
Dim pkey As String
pkey = txthuo.List(txthuo.ListIndex)
If pkey <> "" Then
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [food] where mz = '" + pkey + "'", cnnUser, adOpenKeyset, adLockOptimistic
     .Delete
     .Update
     .Close
  End With
  cnnUser.Close
  Call ddt
Else
  MsgBox "请选择删除项！", vbOKOnly, "提示："
End If

End Sub

Private Sub Command9_Click()
If tt1.Text <> "" Then
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
   With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [food] where mz = '" & tt1.Text & "'"
     If Not .EOF Then
     MsgBox "数据已存在！", vbOKOnly, "提示："
     tt1.Text = ""
     .Close
     cnnUser.Close
     Exit Sub
     End If
     .Close
  End With
  
  
  
  
  
  With rstUser
     If .State = adStateOpen Then .Close
     .Open "Select * from [food]", cnnUser, adOpenKeyset, adLockOptimistic
     .AddNew
     ![mz] = tt1.Text
     .Update
     .Close
  End With
  cnnUser.Close
  Call ddt

 Else
  MsgBox "请填写内容！", vbOKOnly, "提示："
End If
tt1.Text = ""
 
End Sub

Private Sub Form_Load()
data
Me.Frame1.Visible = False
Me.Frame2.Visible = False
Me.Frame7.Visible = True
Call ddt
End Sub
Private Sub ddt()

txthuo.Clear
txtjingying.Clear
txtfahuo.Clear
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
     If .State = adStateOpen Then .Close
     .Open ("Select * from [food]")
      Do While Not .EOF
      txthuo.AddItem rstUser.Fields("mz").Value
      
     .MoveNext
      Loop
     .Close
  End With
    With rstUser
     If .State = adStateOpen Then .Close
     .Open ("Select * from [jingyinghu]")
      Do While Not .EOF
      txtjingying.AddItem rstUser.Fields("jingying").Value
      
     .MoveNext
      Loop
     .Close
  End With
    With rstUser
     If .State = adStateOpen Then .Close
     .Open ("Select * from [fahuo]")
      Do While Not .EOF
      txtfahuo.AddItem rstUser.Fields("fahuo").Value
      
     .MoveNext
      Loop
     .Close
  End With
   cnnUser.Close


End Sub
Private Sub Form_Unload(Cancel As Integer)
End
End Sub

Private Sub genggai_Click(Index As Integer)
  Me.Hide
  Form1.Show
End Sub

Private Sub guanyu_Click(Index As Integer)
frmAbout.Show
End Sub

Private Sub jbzl_Click(Index As Integer)
Me.Frame1.Visible = False
Me.Frame2.Visible = False
Me.Frame7.Visible = True
End Sub

Private Sub List1_Click()
 Form3.txtname.Text = Trim(List1.List(List1.ListIndex))
 If Trim(List2.List(List1.ListIndex)) = "是" Then
  Form3.Check1.Value = 1
  Else
  Form3.Check1.Value = 0
 End If
  Form3.txtch.Text = ""
  Form3.txtxin.Text = ""
End Sub

Private Sub shj_Click(Index As Integer)
Me.Frame1.Visible = True
Me.Frame2.Visible = True
Me.Frame7.Visible = False

End Sub

Private Sub tt1_KeyPress(KeyAscii As Integer)
If KeyAscii = 13 Then
Command9_Click
End If
End Sub

Private Sub tt2_KeyPress(KeyAscii As Integer)
If KeyAscii = 13 Then
Command10_Click
End If
End Sub

Private Sub tt3_KeyPress(KeyAscii As Integer)
If KeyAscii = 13 Then
Command11_Click
End If
End Sub

Private Sub tuichu_Click(Index As Integer)
Dim tm As Integer
tm = MsgBox("确定退出？", vbOKCancel, "提示：")
If tm = 1 Then

  End
Else
Exit Sub
End If
End Sub

Private Sub txtname_Change()
Check1.Value = 0
End Sub


Function cipher(stext As String)    '加密程序
    Const min_asc = 32
    Const max_asc = 126
    Const num_asc = max_asc - min_asc + 1
    Dim offset As Long
    Dim strlen As Integer
    Dim i As Integer
    Dim ch As Integer
    offset = 123
    Rnd (-1)
    Randomize (offset)
    strlen = Len(stext)
    For i = 1 To strlen
       ch = Asc(Mid(stext, i, 1))
       If ch >= min_asc And ch <= max_asc Then
           ch = ch - min_asc
           offset = Int((num_asc + 1) * Rnd())
           ch = ((ch + offset) Mod num_asc)
           ch = ch + min_asc
           ptext = ptext & Chr(ch)
       End If
    Next i
    cipher = ptext
End Function

Function decipher(stext As String)      '解密程序
    Const min_asc = 32
    Const max_asc = 126
    Const num_asc = max_asc - min_asc + 1
    Dim offset As Long
    Dim strlen As Integer
    Dim i As Integer
    Dim ch As Integer
    offset = 123
    Rnd (-1)
    Randomize (offset)
    strlen = Len(stext)
    For i = 1 To strlen
       ch = Asc(Mid(stext, i, 1))
       If ch >= min_asc And ch <= max_asc Then
           ch = ch - min_asc
           offset = Int((num_asc + 1) * Rnd())
           ch = ((ch - offset) Mod num_asc)
           If ch < 0 Then
               ch = ch + num_asc
           End If
           ch = ch + min_asc
           ptext = ptext & Chr(ch)
       End If
    Next i
    decipher = ptext
End Function
 

