<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Welcome View </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
<link href="css/global.css" type="text/css"  rel="stylesheet" >

<style>

body{
opacity: 50px;
background-size: cover;
background-image: url("<%=ORSView.APP_CONTEXT%>/image/lib5.jpg");

}
.welcome{
	
	color :#2E8B57;
 	font-size: 150%;
	text-align:center;
   margin-top: 70px; 
	height: 480px;
	font-style: italic;
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<div class="wrapper">
<%@include file="Header.jsp" %>
<div class="content">
<div class="welcome">
	<h1>Welcome To</h1>
	 
	<h1>Online Result System</h1>
</div>
</div>
<%@include file="Footer.jsp" %>
</div>
</body>
</html>