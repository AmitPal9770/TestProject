<%@page import="in.co.rays.ors.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.ors.util.HTMLUtility"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

.btn-success{

	 margin-left: 80px;
    	width: 150px;
}
.btn-warning{

	 margin-left: 80px;
    	width: 150px;
}
</style>
<title>User Registration</title>
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
					<div class="card card-login" style="margin-bottom: 20px ; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.USER_REGISTRATION_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent;border-color: darkblue">
								<h4 class="card-title" style="color: red">User Registration</h4>
							</div>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success" style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
								
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
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times fg-2px"></i></span>
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
							<div class="form-group">
								<label>First Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your first name" name="name"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>">
										
									<%
										if (ServletUtility.getErrormessage("name", request) != null && ServletUtility.getErrormessage("name", request).length()>0) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip" data-placement="left"
										title="<%=ServletUtility.getErrormessage("name", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									
									</div>
								</div>
								<div class="form-group">
								<label>Last Name <span style="color:red ">*</span></label>
								<div class="input-group">
									
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your last name" name="lname"
										value="<%=DataUtility.getStringData(dto.getLastName())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("lname", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="<%=ServletUtility.getErrormessage("lname", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
								<div class="form-group">
									<label>Email <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-envelope"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your login id" name="email"
										value="<%=DataUtility.getStringData(dto.getLogin())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("email", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("email", request)%>">
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
										placeholder=" Enter password here" name="password"
										value="<%=DataUtility.getStringData(dto.getPassword())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("password", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("password", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
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
										<span class="input-group-text"> <i class="fa fa-key"></i>
										</span>
									</div>

									<input type="password" class="form-control col-lg-12"
										placeholder=" Re-Enter password" name="confirmpassword"
										value="<%=DataUtility.getStringData(dto.getPassword())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("confirmpassword", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("confirmpassword", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
																	
										<div class="form-group">
									<label>DOB<span style="color:red ">*</span></label>
									<div class="input-group">
									
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>
									
									<input type="text"  id="datepicker1"  class="form-control"
										name="dateofbirth" readonly placeholder="Select your Date of birth">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("dateofbirth", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("dateofbirth", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
									</div>
								<div class="form-group">
								<label>Gender <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-venus-double"></i>
										</span>
									</div >
									
									<%LinkedHashMap map= new LinkedHashMap() ;
									map.put("Male", "Male");
									map.put("Female", "Female");
								String list=	HTMLUtility.getList("gender", DataUtility.getStringData(dto.getGender()), map);
								
									%>
									<%=list %>
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("gender", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("gender", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									
								</div>
								<div class="form-group">
								<label>Mobile NO<span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-phone-square"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your mobile no" name="mobile"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("mobile", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("mobile", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
									
							</div>
							
							<button type="submit" class="btn btn-success " name="operation"  value="<%=UserRegistrationCtl.OP_SIGN_UP %>">Sign Up</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=UserRegistrationCtl.OP_RESET %>">Reset</button>
	
							
						</form>
					</div>
				</div>
			</div>
		</div>
<br>
<br>		
<%@ include file ="Footer.jsp" %>
</div>
</body>

</html>