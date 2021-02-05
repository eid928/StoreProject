<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>智捷網上電腦商城</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
a:link {
	font-size: 18px;
	color: #DB8400;
	text-decoration: none;
	font-weight: bolder;
}
a:visited {
	font-size: 18px;
	color: #DB8400;
	text-decoration: none;
	font-weight: bolder;
}
a:hover {
	font-size: 18px;
	color: #DB8400;
	text-decoration: underline;
	font-weight: bolder;
}
</style>
</head>

<body>
<div class="header">智捷網上電腦商城</div>
<hr width="100%" />
<div>
  <p class="text1"> <img src="images/4.jpg"   align="absmiddle" /> <a href="Controller?action=list">商品列表</a> </p>
  <p class="text2"> 您可以從產品列表中瀏覽感興趣的產品進行購買 </p>
</div>
<hr width="100%" />
<div>
  <p class="text1"> <img src="images/mycar1.jpg" align="absmiddle"  /> <a href="Controller?action=cart">購物車</a> </p>
  <p class="text2"> 您可以把感興趣的商品暫時放在購物車中 </p>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
