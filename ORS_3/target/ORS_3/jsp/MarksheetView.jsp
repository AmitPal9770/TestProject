<%@page import="in.co.rays.ors.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.ors.controller.MarksheetCtl"%>
<%@page import="in.co.rays.ors.util.HTMLUtility"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
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
<title>Add Marksheet</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
	
	
</head>
<body>

	<%@ include file="Header.jsp"%>
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.MarksheetDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px ; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.MARKSHEET_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent; border-color: darkblue">
		<% if(dto.getId()>0){ %>
			<h4 class="card-title" style="color: red">Update Marksheet</h4>
		<%}else{ %>
			<h4 class="card-title" style="color: red">Add Marksheet</h4>
		<%} %>							
							</div>
							<br>
							<%
								if (ServletUtility.getSuccessMessage(request) != null
										&& ServletUtility.getSuccessMessage(request).length() > 0) {
							%>
							<div class="alert alert-success"
								style="line-height: 10px; margin-left: 20px; margin-right: 20px;">
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
				
<% List slist = (List)request.getAttribute("studentlist") ; %>	
	
							<div class="card-body">
							<div class="form-group">
								<label>Roll No <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your Roll No" name="rollNo"
										value="<%=DataUtility.getStringData(dto.getRollNo())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("rollNo", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("rollNo", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
								<div class="form-group">
								<label>Student Name <span style="color:red ">*</span></label>
								<div class="input-group">
									
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<%=HTMLUtility.getList("StudentName", String.valueOf(dto.getStudentId()), slist) %>	
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("StudentName", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("StudentName", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
								<div class="form-group">
									<label>Physics <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-pencil-ruler"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your Marks" name="physics"
										value="<%=(DataUtility.getStringData(dto.getPhysics()).equals("0")?"":DataUtility.getStringData(dto.getPhysics()))%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("physics", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("physics", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
								<div class="form-group">
									<label>Chemistry <span style="color:red ">*</span></label>
								<div class="input-group">
								
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-pencil-ruler"></i>
										</span>
									</div>

									<input type="text" class="form-control col-lg-12"
										placeholder=" Enter Your Marks" name="chemistry"
										value="<%=(DataUtility.getStringData(dto.getChemistry()).equals("0")?"":DataUtility.getStringData(dto.getChemistry()))%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("chemistry", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("chemistry", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
									
								<div class="form-group">
										<label>Maths <span style="color:red ">*</span></label>
								<div class="input-group">
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-pencil-ruler"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter your Marks" name="maths"
										value="<%=(DataUtility.getStringData(dto.getMaths()).equals("0")?"":DataUtility.getStringData(dto.getMaths()))%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("maths", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("maths", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>
									
														</div>
							
	<% if(dto.getId() > 0){ %>
					<button type="submit" class="btn btn-success " name="operation"  value="<%=MarksheetCtl.OP_UPDATE %>">Update</button>
 					<button type="submit" class="btn btn-warning " name="operation"  value="<%=MarksheetListCtl.OP_CANCEL %>">Cancel</button>
	<%}else{ %>
					<button type="submit" class="btn btn-success " name="operation"  value="<%=MarksheetCtl.OP_SAVE %>">Save</button>
					<button type="submit" class="btn btn-warning " name="operation"  value="<%=MarksheetCtl.OP_RESET %>">Reset</button>
	
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