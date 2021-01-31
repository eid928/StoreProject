<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>使用者登錄</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
</head>

<body>
<div class="header">智捷商城</div>
<hr width="100%" />
<%--顯示從Controller回傳的驗證失敗訊息 --%>
<ul>
    <c:forEach var="error" items="${errors }">
        <li class="error">${error }</li>
    </c:forEach>
</ul>
<form action="Controller" method="post" >
  <table width="100%" align="center" >
    <tr height="40" >
      <td colspan="2" align="center"><strong>登錄</strong></td>
    </tr>
    <tr height="40" >
      <td width="50%" align="right" ><img src="images/3.jpg" align="absmiddle"/>&nbsp;&nbsp;使用者帳號：</td>
      <td><input type="text" name="userid" class="textfield" /></td>
    </tr>
    <tr height="40" >
      <td width="50%" align="right"><img src="images/2.jpg" align="absmiddle"/>&nbsp;&nbsp;使用者密碼：</td>
      <td><input type="password" name="password" class="textfield" /></td>
    </tr>
    <tr height="40" >
      <td align="right">&nbsp;</td>
      <td ><input type="image" src="images/login_button.jpg" onclick="document.forms[0].fn.value='login'" />
        &nbsp;&nbsp;&nbsp;&nbsp; <a href="customer_reg.jsp"><img src="images/reg_button.jpg" border="0" /></a></td>
    </tr>
  </table>
  <input type="hidden" name="action" value="login">
</form>
<%@include file="footer.jsp" %>
</body>
</html>