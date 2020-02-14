<%@page import="in.co.rays.ors.controller.RoleCtl"%>
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
	height: 558px;
}
</style>
<title>Add Role </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
	
	
</head>
<body>
<div class = "wrapper">
	<%@ include file="Header.jsp"%>
<div class = "content">
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.RoleDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px ; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.ROLE_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent; border-color: darkblue">
<%if(dto.getId() > 0){ %>
	<h4 class="card-title" style="color: red">Update Role</h4>
<%}else{ %>							
	<h4 class="card-title" style="color: red">Add Role</h4>
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
								<label>Role Name <span style="color:red ">*</span></label>	
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-user-circle"></i>
										</span>
									</div >
									
									<input type="text" class="form-control col-lg-12"
										placeholder="Enter Role name" name="name"
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
								<label>Description <span style="color:red ">*</span></label>
								<div class="input-group">
									
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-stream"></i>
										</span>
									</div >
									
									<textarea type="text" class="form-control col-lg-12"
										placeholder="Enter Description" name="description"
										value="<%=DataUtility.getStringData(dto.getDescription())%>"></textarea>
										
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("description", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("description", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									</div>

							</div>
<%if(dto.getId()> 0){ %>							
							<button type="submit" class="btn btn-success " name="operation"  value="<%=RoleCtl.OP_SAVE %>">Update</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=RoleCtl.OP_CANCEL %>">Cancel</button>
<%}else{ %>
							<button type="submit" class="btn btn-success " name="operation"  value="<%=RoleCtl.OP_SAVE %>">Save</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=RoleCtl.OP_RESET %>">Reset</button>
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