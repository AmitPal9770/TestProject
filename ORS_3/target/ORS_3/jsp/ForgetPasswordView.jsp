<%@page import="in.co.rays.ors.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<head>

<style type="text/css">



.btn-success{

	 margin-left: 80px;
    	width: 150px;
}
.btn-warning{

	 margin-left: 80px;
    	width: 150px;
}
.container{
height: 535px;

}	
	
</style>
<title>Forget Password</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
	
	
	
</head>
<body>
	<%@ include file="Header.jsp"%>
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.UserDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px" >
						<form class="form" method="post" action="<%=ORSView.FORGET_PASSWORD_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent;">
								<h2 class="card-title" style="color: red">Forget Password</h2>
							</div>
							<br>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success" style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
									<b><%=ServletUtility.getSuccessMessage(request)%></b>	
							</div>
							<%
								}
							%>
							<%
								if (ServletUtility.getErrorMessage(request) != null
										&& ServletUtility.getErrorMessage(request).length() > 0) {
							%>
							<div class="alert alert-danger" style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
									<button type="button" class="close" data-dismiss="alert"  aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times"></i></span>
									</button>
									<b><%=ServletUtility.getErrorMessage(request)%></b>
								
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
							
							<p style="color: darkblue ; font-size: 20px" align="center"> Enter your Valid Email_id to Reset Your Password</p>
							
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
																							
							</div>
							
							<button type="submit" class="btn btn-success" name="operation"  value="<%=ForgetPasswordCtl.OP_GO %>">Submit</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=ForgetPasswordCtl.OP_RESET %>">Reset</button>
			
									
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<%@ include file ="Footer.jsp" %>
</div>
</body>

</html>