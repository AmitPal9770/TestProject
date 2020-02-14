<%@page import="in.co.rays.ors.controller.MyProfileCtl"%>
<%@page import="in.co.rays.ors.util.HTMLUtility"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<html>
<head>

<style type="text/css">
.btn-success{

	 margin-left: 80px;
    	width: 150px;
}
.btn-primary{

	 margin-left: 80px;
    	width: 150px;
}

</style>
<title>My Profile </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
	
</head>
<body>
	<%@ include file="Header.jsp"%>
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.UserDTO" scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px ; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.MY_PROFILE_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent;border-color: darkblue">
								<h2 class="card-title" style="color: red">My Profile</h2>
							</div>
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
									<label>Email_Id <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-envelope"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										 name="login" readonly="readonly"
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
								<label>First Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your first name" name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("firstName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("firstName", request)%>">
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
										 name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastName())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("lastName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("lastName", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
									
								<div class="form-group">
									<label>Date of Birth<span style="color:red ">*</span></label>
									<div class="input-group">
									
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>
									
									<input type="text"  id="datepicker1"  class="form-control col-lg-12"
										name="dob"  readonly="readonly" value="<%=DataUtility.getDateString(dto.getDob()) %>" >
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("dob", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("dob", request)%>">
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
										placeholder="Enter your mobile no" name="mobileNo"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("mobileNo", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("mobileNo", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
									
			
							</div>
							
							<button type="submit" class="btn btn-success " name="operation"  value="<%=MyProfileCtl.OP_SAVE %>">Save</button>
							<button type="submit" class="btn btn-primary " name="operation"  value="<%=ORSView.CHANGE_PASSWORD_CTL %>">Change Password</button>

							
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