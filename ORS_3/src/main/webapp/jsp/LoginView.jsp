
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.controller.LoginCtl"%>
<%@page import="in.co.rays.ors.util.ServletUtility" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<head>
<title>Login View </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
<style type="text/css">



/* body {
	background-image: url('./img/bg1.jpg');
	background-size: cover;
background-repeat: no-repeat;
height: 100%;
font-family: 'Numans', sans-serif;
} */

.btn-primary{
/* 	margin-top:10px; */
	width: 150px;
	position: center;
	margin-left: 20px;
}


.container{
height: 530px;

}	
	
</style>
<title>Login</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
	
	
	
</head>
<body>
	<%@ include file="Header.jsp"%>
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.UserDTO"
		scope="request"></jsp:useBean>

	
		<div class="container" style="border:5px;">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.LOGIN_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent;border-color: darkblue;">
								<h2 class="card-title" style="color: red">Login</h2>
							</div>
<br>

							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px; ">
								
								<div class="alert-icon">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
								<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
									<b><%=ServletUtility.getSuccessMessage(request)%></b>
								
								</div>
							</div>
							<%
								}
							%>

							<%
								if (ServletUtility.getErrorMessage(request) != null
										&& ServletUtility.getErrorMessage(request).length() > 0) {
							%>
							<div class="alert alert-danger"
								style="line-height: 30px; margin-left: 20px; margin-right: 20px;">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
								<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
									<b><%=ServletUtility.getErrorMessage(request)%></b>
								
								</div>
							
							<%
								}
							%>

							<%
								String msg =(String) request.getAttribute("message");
								if (msg!= null ) {
							%>
							<div class="alert alert-danger"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								
								<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
									<b><%=msg%></b>
								
							</div>
							<%
								}
							%>







		<input type="hidden" name="id" value="<%=dto.getId()%>"> 
		<input type="hidden" name="createdby" value="<%=dto.getCreatedby()%>">
		<input type="hidden" name="modifiedby" value="<%=dto.getModifiedby()%>">
		<input type="hidden" name="createddatetime" value="<%=dto.getCreateddatetime()%>">
		<input type="hidden" name="modifieddatetime" value="<%=dto.getModifieddatetime()%>">
				
	
							<div class="card-body">
								<div class="form-group">
									<label>Login_id <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-envelope"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your login id" name="login"
										value="<%=DataUtility.getStringData(dto.getLogin())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("login", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("login", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
								<div class="form-group">
									<label>Password <span style="color:red ">*</span></label>
								<div class="input-group">
								
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-key"></i>
										</span>
									</div>

									<input type="password" class="form-control col-lg-12"
										placeholder=" Enter Password" name="pass"
										value="<%=DataUtility.getStringData(dto.getPassword())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("pass", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("pass", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
									
															
							</div>
							
							<button type="submit" class="btn btn-primary " name="operation"  value="<%=LoginCtl.OP_SIGN_IN %>">Login</button>
					<br><br>
					<a style="margin-left: 20px" href="<%=ORSView.FORGET_PASSWORD_CTL %>" class="forget" ><i class="fa fa-unlock-alt " ></i> Forget Password ?</a>
				
							
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<%@ include file ="Footer.jsp" %>
</div>
</body>

</html>