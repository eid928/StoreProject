<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>使用者註冊</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        .boder {
            border: 1px solid #5B96D0;
        }

        .col1 {
            background-color: #A6D2FF;
            text-align: right;
            padding-right: 10px;
            border: 1px solid #5B96D0;
            line-height: 50px;
        }

        .col2 {
            padding-left: 10px;
            border: 1px solid #5B96D0;
            line-height: 50px;
        }

        .textfield {
            height: 20px;
            width: 200px;
            border: 1px solid #999999;
            text-align: left;
            font-size: medium;
            line-height: 50px;
        }
    </style>
    
    <script type="text/javascript">
        function verify(myForm) {
			var errorMes = "";
			if(myForm.userid.value == "") {
				errorMes += "帳號不能為空。\n";
			}
			if(myForm.name.value == "") {
				errorMes += "姓名不能為空。\n";
			}
			if(myForm.password.value == "") {
				errorMes += "密碼不能為空。\n";
			}
			if(myForm.password2.value == "") {
				errorMes += "請再次輸入密碼。\n";
			}
			if(myForm.password.value != "" && myForm.password2.value != "" && myForm.password.value != myForm.password2.value) {
				errorMes += "兩次輸入的密碼不一致。\n";
			}
			
			var pattern = /^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$/;
			
			if (!pattern.test(myForm.birthday.value)) {
				errorMes += "生日日期格式無效。\n";
			}
			if (errorMes == "") {
				return true;
			} else {
				alert(errorMes);
				return false;
			}
		}
    </script>
    
</head>

<body>
<div><img src="images/reg.jpg" align="absmiddle" /></div>
<br>
<hr width="100%" />
<div class="text3" align="center">請填寫以下訊息</div>
<br>
<%--顯示從Controller回傳的驗證失敗訊息 --%>
<ul>
    <c:forEach var="error" items="${errors }">
        <li class="error">${error }</li>
    </c:forEach>
</ul>
<form action="Controller" method="post" onsubmit="return verify(this)">
<%--onsubmit="return verify(this)" --%>
<%--此處為前端驗證，onsubmit後面寫return，若回傳值為false，則submit會中止，便不會提交給Controller --%>
<table width="60%" border="0" align="center" class="boder">
  <tr>
    <td width="35%" height="27" class="col1">使用者帳號：</td>
    <td class="col2"><input type="text" name="userid" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">使用者姓名：</td>
    <td class="col2"><input type="text" name="name" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">使用者密碼：</td>
    <td class="col2"><input type="password" name="password" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">請再次輸入密碼：</td>
    <td class="col2"><input type="password" name="password2" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">出生日期：</td>
    <td  class="col2"><input type="text" name="birthday" />*
      格式（YYYY-MM-DD）</td>
  </tr>
  <tr>
    <td height="27" class="col1">通訊地址：</td>
    <td class="col2"><input type="text" name="address"  /></td>
  </tr>
  <tr>
    <td height="27" class="col1">電話號碼：</td>
    <td class="col2"><input type="text" name="phone"   /></td>
  </tr>
</table>
<br>
<div align="center">
  <input type="image" src="images/submit_button.jpg" />
</div>
<input type="hidden" name="action" value="reg">
</form>
<%@include file="footer.jsp" %>
</body>
</html>
