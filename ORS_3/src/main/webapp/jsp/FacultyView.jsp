<%@page import="in.co.rays.ors.controller.FacultyCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<html>
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
</style>
<title>Add Faculty </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
	
</head>
<body>
	<%@ include file="Header.jsp"%>
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.FacultyDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.FACULTY_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent; border-color: darkblue">
<%if(dto!=null && dto.getId()>0){ %>
<h4 class="card-title" style="color: red">Update Faculty</h4>
<%}else{ %>
				<h4 class="card-title" style="color: red">Add Faculty</h4>
<%} %>		
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
									
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true"><i class="fa fa-times fg-2px"></i></span>
									</button>
									<b><%=ServletUtility.getErrorMessage(request)%></b>
								</div>
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
		<% 
List collist = (List)request.getAttribute("collegelist");
List coulist = (List)request.getAttribute("courselist");
List sublist = (List)request.getAttribute("subjectlist");
%>

	
							<div class="card-body">
	
							<div class="form-group">
								<label>College Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
								<%=HTMLUtility.getList("CollegeName", String.valueOf(dto.getCollegeid()), collist) %>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("CollegeName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("CollegeName", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
	
							<div class="form-group">
								<label>Course Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
							<%=HTMLUtility.getList("CourseName", String.valueOf(dto.getCourseId()), coulist) %>
			
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("CourseName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("CourseName", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
	
							<div class="form-group">
								<label>Subject Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<%=HTMLUtility.getList("SubjectName", String.valueOf(dto.getSubjectId()), sublist) %>	
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("SubjectName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("SubjectName", request)%>">
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
										placeholder="Enter your last name" name="lastName"
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
									<label>Date of Joining<span style="color:red ">*</span></label>
									<div class="input-group">
									
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>
									
									<input type="text"  id="datepicker1"  class="form-control"
										name="dateofjoining" readonly placeholder="Select Date of Joining" value=<%=DataUtility.getDateString(dto.getDateofjoining()) %>>
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("dateofjoining", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("dateofjoining", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
									</div>

								<div class="form-group">
									<label>Qualification <span style="color:red ">*</span></label>
								<div class="input-group">
								
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-book"></i>
										</span>
									</div>

									<input type="text" class="form-control col-lg-12"
										placeholder=" Enter Qualification" name="qualification"
										value="<%=DataUtility.getStringData(dto.getQualification())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("qualification", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("qualification", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
									

									
									
								<div class="form-group">
									<label>Email_Id <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-envelope"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your Email id" name="emailid"
										value="<%=DataUtility.getStringData(dto.getEmailId())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("emailid", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("emailid", request)%>">
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
										placeholder="Enter your mobile no" name="mobileno"
										value="<%=DataUtility.getStringData(dto.getMobileno())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("mobileno", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("mobileno", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
		


							</div>
		<%if(dto.getId()> 0){ %>					
							<button type="submit" class="btn btn-success " name="operation"  value="<%=FacultyCtl.OP_UPDATE %>">Update</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=FacultyCtl.OP_CANCEL %>">Cancel</button>
	<%}else{ %>
						<button type="submit" class="btn btn-success " name="operation"  value="<%=FacultyCtl.OP_SAVE %>">Save</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=FacultyCtl.OP_RESET %>">Reset</button>
	
	<%} %>
							
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