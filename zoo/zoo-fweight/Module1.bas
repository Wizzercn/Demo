Attribute VB_Name = "Module1"
Sub Main()
On Error GoTo error
''系统检测是否有date.mdb文件,如果没有,则是系统第一次启动,则建立之
If Dir("c:\windows\system\fish.mdb") = "" Then

''注意在开始,您要确定您的工程引用了Microsoft dao 2.5/3.5 compatibility library 在"工程"==>"引用"中.

Dim WS As Workspace
Dim DB As Database
Dim TD As TableDef
Dim FLD As Field
Dim IDX As Index
Dim rd As Recordset
Set WS = DBEngine.Workspaces(0)
Set DB = WS.CreateDatabase("c:\windows\system\fish.mdb", dbLangGeneral)
DB.Connect = ";pwd=andy"
Set TD = DB.CreateTableDef("date")
TD.Attributes = 0
TD.Connect = ""
TD.SourceTableName = ""
TD.ValidationRule = ""
TD.ValidationText = ""
'' Field first_time
Set FLD = TD.CreateField("first_time", 8, 8)
FLD.Attributes = 1
FLD.DefaultValue = ""
FLD.OrdinalPosition = 0
FLD.Required = False
FLD.ValidationRule = ""
FLD.ValidationText = ""
TD.Fields.Append FLD
'' Field last_time
Set FLD = TD.CreateField("last_time", 8, 8)
FLD.Attributes = 1
FLD.DefaultValue = ""
FLD.OrdinalPosition = 1
FLD.Required = False
FLD.ValidationRule = ""
FLD.ValidationText = ""
TD.Fields.Append FLD
'' Field times
Set FLD = TD.CreateField("times", 3, 2)
FLD.Attributes = 1
FLD.DefaultValue = ""
FLD.OrdinalPosition = 2
FLD.Required = False
FLD.ValidationRule = ""
FLD.ValidationText = ""
TD.Fields.Append FLD
DB.TableDefs.Append TD
DB.Close
Set DB = WS.OpenDatabase("c:\windows\system\fish.mdb")
Set rd = DB.OpenRecordset("date")
With rd
.AddNew
.Fields("first_time") = Date
.Fields("last_time") = Date
.Fields("times") = 1
.Update
End With

DB.Close
MsgBox "这是您第一次启动本系统!您的试用期为30天,今天是第一天.谢谢使用!", 48, "www.CdmTsq.com"

''效果如图1 (见附件1)

Form1.Show ''启动您的主窗体



Else ''系统有date.mdb文件,则不是第一次运行,就不用建立数据库文件了.

Dim WS2 As Workspace
Dim DB2 As Database
Dim rd2 As Recordset
Set WS2 = Workspaces(0)
Set DB2 = WS2.OpenDatabase("c:\windows\system\fish.mdb", pwd = "springlover")
Set rd2 = DB2.OpenRecordset("date")
''开始检测用户是否修改了系统日期
rd2.MoveFirst
If rd2.Fields("last_time") > Date Then
MsgBox "对不起,您在本软件的试用期内不可以修改系统日期,否则将取消您对本系统的试用权.如果您想继续使用本软件,请您恢复系统日期.谢谢合作!", 48, "www.CdmTsq.com"



End
End If

''开始检测是否超期

If Date - rd2.Fields("first_time") >= 30 Then ''设定试用期为30天
MsgBox "您已经启动本系统" & rd2.Fields("times") & "次了,而且已经到了30天的试用期,如果您想继续使用本软件,请您到本公司注册并购买正版的软件! QQ:11624317", 48, "www.CdmTsq.com"



End

Else

''仍在试用期内
num% = rd2.Fields("times")
rd2.Edit
rd2.Fields("last_time") = Date
rd2.Fields("times") = num + 1
rd2.Update

MsgBox "这是您第" & rd2.Fields("times") & "次使用本系统,您还有" & 30 - (Date - rd2.Fields("first_time")) & "天的试用期,祝您今天工作愉快!", 48, "www.CdmTsq.com"

''效果如图2 (见附件2)

Form1.Show ''启动您的主窗体


End If





End If
Exit Sub
error:
MsgBox "系统错误!"

End Sub

