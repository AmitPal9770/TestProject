<%@page import="in.co.rays.ors.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<head>

<style type="text/css">

.btn-success{

	 margin-left: 80px;
    	width: 150px;
}
.btn-primary
{
	 margin-left: 80px;
    	width: 150px;
}

.container{
height: 530px;

}	
	
</style>
<title>Change Password</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
	
	
	
	
</head>
<body>
	<%@ include file="Header.jsp"%>
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.UserDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.CHANGE_PASSWORD_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent;border-color: darkblue">
								<h2 class="card-title" style="color: red">Change Password</h2>
							</div>
							<br>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								
								<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
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
							<div class="alert alert-danger"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								<div class="container" style="text-align: center;">
									<div class="alert-icon">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</div>
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times fg-2px"></i></span>
									</button>
									<b><%=ServletUtility.getErrorMessage(request)%></b>
								</div>
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
									<label>Old Password <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-lock"></i>
										</span>
									</div >
									
									<input type="password" class="form-control col-lg-12"
										placeholder="Enter Old Password" name="oldpassword"
										value="<%=DataUtility.getStringData(request.getParameter("oldpassword")) %>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("oldpassword", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("oldpassword", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
																							
							<div class="form-group">
									<label>New Password <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-lock"></i>
										</span>
									</div >
									
									<input type="password" class="form-control col-lg-12"
										placeholder="Enter New Password" name="newpassword"
										value="<%=DataUtility.getStringData(request.getParameter("newpassword")) %>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("newpassword", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="right"
										title="
										<%=ServletUtility.getErrormessage("newpassword", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
						
							<div class="form-group">
									<label>Confirm Password <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-lock"></i>
										</span>
									</div >
									
									<input type="password" class="form-control col-lg-12"
										placeholder="Re-Enter Password" name="confirmPassword"
										value="<%=DataUtility.getStringData(request.getParameter("confirmPassword")) %>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("confirmPassword", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("confirmPassword", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
						
						
							</div>
							
							<button type="submit" class="btn btn-success " name="operation"  value="<%=ChangePasswordCtl.OP_SAVE %>">Save</button>
							<button style="color: blue" type="submit" class="btn btn-primary " name="operation"  value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE %>">My Profile</button>
			
									
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<%@ include file ="Footer.jsp" %>
</div>
</body>

</html>