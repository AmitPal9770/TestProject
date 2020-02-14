<%@page import="in.co.rays.ors.controller.CollegeCtl"%>
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
.btn-warning{

	 margin-left: 80px;
    	width: 150px;
}
.content{
	height: 720px;
}
</style>
<title>Add College </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
	
	
</head>
<body>
<div class = "wrapper">
	<%@ include file="Header.jsp"%>
<div class = "content">
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.CollegeDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.COLLEGE_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent;border-color: darkblue">
								<%if(dto!=null && dto.getId()>0){ %>
			<h4 class="card-title" style="color: red">Update College</h4>
<%}else{ %>
				<h4 class="card-title" style="color: red">Add College</h4>
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
	
	

	
							<div class="card-body">

							<div class="form-group">
								<label>College Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter College name" name="name"
										value="<%=DataUtility.getStringData(dto.getName())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("name", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("name", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
								<div class="form-group">
								<label>Address <span style="color:red ">*</span></label>
								<div class="input-group">
									
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-address-book"></i>
										</span>
									</div >
									
									<textarea type="text" class="form-control col-lg-12"
										placeholder="Enter Address" name="address"
										value="<%=DataUtility.getStringData(dto.getAddress())%>"></textarea>
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("address", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("address", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>

								<div class="form-group">
								<label>State <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-city"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter State name" name="state"
										value="<%=DataUtility.getStringData(dto.getState())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("state", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("state", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
							

								<div class="form-group">
								<label>City <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-city"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter City name" name="city"
										value="<%=DataUtility.getStringData(dto.getCity())%>">
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("city", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("city", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
								</div>
							

								<div class="form-group">
								<label>Mobile No <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-mobile"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter Mobile No" name="mobileNo"
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
<%if(dto.getId()> 0 ){ %>							
							<button type="submit" class="btn btn-success " name="operation"  value="<%=CollegeCtl.OP_UPDATE %>">Update</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=CollegeCtl.OP_CANCEL %>">Cancel</button>
	<%}else{ %>
								<button type="submit" class="btn btn-success " name="operation"  value="<%=CollegeCtl.OP_SAVE %>">Save</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=CollegeCtl.OP_RESET %>">Reset</button>
	
	<%} %>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<%@ include file ="Footer.jsp" %>
</div>

</body>

</html>