VERSION 5.00
Begin VB.Form Form1 
   BorderStyle     =   3  'Fixed Dialog
   Caption         =   "用户登陆"
   ClientHeight    =   1875
   ClientLeft      =   45
   ClientTop       =   435
   ClientWidth     =   3180
   Icon            =   "Form1.frx":0000
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   Picture         =   "Form1.frx":27A2
   ScaleHeight     =   1875
   ScaleWidth      =   3180
   StartUpPosition =   2  '屏幕中心
   Begin VB.TextBox Text1 
      Height          =   270
      Left            =   360
      TabIndex        =   7
      Text            =   "Text1"
      Top             =   2040
      Width           =   975
   End
   Begin VB.ComboBox Combo1 
      Height          =   300
      Left            =   1200
      TabIndex        =   0
      Top             =   360
      Width           =   1695
   End
   Begin VB.CommandButton Command2 
      Caption         =   "管理员登陆"
      Height          =   375
      Left            =   1680
      TabIndex        =   5
      Top             =   1320
      Width           =   1095
   End
   Begin VB.CommandButton Command1 
      Caption         =   "司镑员登陆"
      Height          =   375
      Left            =   240
      TabIndex        =   4
      Top             =   1320
      Width           =   1215
   End
   Begin VB.TextBox Text2 
      Height          =   270
      IMEMode         =   3  'DISABLE
      Left            =   1200
      MaxLength       =   20
      PasswordChar    =   "*"
      TabIndex        =   3
      Top             =   840
      Width           =   1695
   End
   Begin VB.Label txtadm 
      Caption         =   "0"
      Height          =   495
      Left            =   1800
      TabIndex        =   6
      Top             =   2880
      Width           =   855
   End
   Begin VB.Label Label2 
      BackStyle       =   0  'Transparent
      Caption         =   "密  码："
      Height          =   255
      Left            =   360
      TabIndex        =   2
      Top             =   840
      Width           =   735
   End
   Begin VB.Label Label1 
      BackStyle       =   0  'Transparent
      Caption         =   "用户名："
      Height          =   255
      Left            =   360
      TabIndex        =   1
      Top             =   360
      Width           =   975
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim cnnUser As New ADODB.Connection
Dim rstUser As New ADODB.Recordset


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

Private Sub Combo2_Change()

End Sub

Private Sub Command1_Click()

  Dim cc As String
  cc = Combo1.Text
  If cc = "" Then
    Combo1.SetFocus
    MsgBox "请输入用户名！", vbOKOnly, "输入不正确！"
    Exit Sub
  End If
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
"Data Source=" & App.Path & "\db.mpp;" & _
"Mode= ReadWrite | Share Deny None"
   'cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   '"Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
    If .State = adStateOpen Then .Close
     .Open ("Select * from admin where name='" & cc & "'")
      If Trim(Combo1.Text & "") = Trim(rstUser.Fields("name").Value) And Trim(Text2.Text & "") = decipher(Trim(rstUser.Fields("password").Value)) Then
     If rstUser.Fields("adm").Value = 1 Then
     txtadm.Caption = "1"
     Else
     txtadm.Caption = "0"
     End If
   Text2.Text = ""
   Me.Hide
   Form2.txtname.Caption = rstUser.Fields("name").Value
   Form2.Show
   
  Else
   
    Text2.Text = ""
    Combo1.SetFocus
    MsgBox "请检查用户名和密码！", vbOKOnly, "输入不正确！"
    cnnUser.Close
    Exit Sub
  End If

     .Close
  End With
 cnnUser.Close
 
  
 Form2.admm
End Sub

Private Sub Command2_Click()


  Dim cc As String
  cc = Combo1.Text
  If cc = "" Then
   Combo1.SetFocus
    MsgBox "请输入用户名！", vbOKOnly, "输入不正确！"
    Exit Sub
  End If
   cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
    If .State = adStateOpen Then .Close
    .Open ("Select * from admin where name='" & cc & "'")
    If Trim(Combo1.Text & "") = Trim(rstUser.Fields("name").Value) And Trim(Text2.Text & "") = decipher(Trim(rstUser.Fields("password").Value)) And rstUser.Fields("adm").Value = 1 Then
   
   Text2.Text = ""
   Me.Hide
   Form3.Label1.Caption = rstUser.Fields("name").Value
   Form3.Show
   
   Else
   If Trim(Combo1.Text & "") = Trim(rstUser.Fields("name").Value) And Trim(Text2.Text & "") = decipher(Trim(rstUser.Fields("password").Value)) Then
   Text2.Text = ""
    Combo1.SetFocus
    
    MsgBox "对不起，您没有权限！", vbOKOnly, "输入不正确！"
   
   Else

    Text2.Text = ""
    Combo1.SetFocus
    MsgBox "请检查用户名和密码！", vbOKOnly, "输入不正确！"
    cnnUser.Close
    Exit Sub
  End If
  
 End If
 .Close
  End With
 
 
 cnnUser.Close

End Sub



Private Sub Command3_Click()

End Sub

Private Sub Form_Activate()
Combo1.Clear
  cnnUser.ConnectionString = "Provider=Microsoft.Jet.OLEDB.4.0;" & _
   "Data Source=" & App.Path & "\db.mpp"
  cnnUser.Open
  With rstUser
    Set .ActiveConnection = cnnUser
    .CursorType = adOpenKeyset  'adOpenKeyset,adOpenStatic可使用Recordset的RecordCount,其他两个属性不行
  End With
  With rstUser
    If .State = adStateOpen Then .Close
    .Open "Select * from admin"
    Do While Not .EOF
    Combo1.AddItem rstUser.Fields("name").Value
    'Combo1.AddItem decipher(Trim(rstUser.Fields("password").Value))
    .MoveNext
     Loop
    .Close
    
  End With
  cnnUser.Close
End Sub

Private Sub Form_Unload(Cancel As Integer)
End
End Sub

Private Sub Text2_KeyPress(KeyAscii As Integer)
If KeyAscii = 13 Then
Command1_Click
End If
End Sub
