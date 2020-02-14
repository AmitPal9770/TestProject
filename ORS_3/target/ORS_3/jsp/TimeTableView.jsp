<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.controller.TimeTableCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<html>
<head>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
  <script>
	var d = new Date();
	function disableSunday(d){
	var day = d.getDay();
	if(day == 0)
	{
		return [false];
	}else{
		return [true];
		}
	}
   
   $( function() {
    $("#datepicker").datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange:'1970:2025',
	  dateFormat:'dd-mm-yy',
		minDate : 0 ,
	beforeShowDay : disableSunday
    });
  });  
  </script>




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
<title>Add TimeTable </title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
	
	
</head>
<body>
	<%@ include file="Header.jsp"%>
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.TimeTableDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px; border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.TIMETABLE_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent; border-color: darkblue">
<%if(dto!=null && dto.getId()>0){ %>
						<h4 class="card-title" style="color: red">Update TimeTable</h4>
			
<%}else{ %>
					<h4 class="card-title" style="color: red">Add TimeTable</h4>
		
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
List coulist = (List)request.getAttribute("courselist");
List sublist = (List)request.getAttribute("subjectlist");
%>

	
							<div class="card-body">
		
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
								<label>Semester <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-book"></i>
										</span>
									</div >
									
								<%	LinkedHashMap map = new LinkedHashMap();
									//HashMap map = new HashMap();
									map.put("1 st", "1 st");
									map.put("2 nd", "2 nd");
									map.put("3 rd", "3 rd");
									map.put("4 th", "4 th");
									map.put("5 th", "5 th");
									map.put("6 th" , "6 th");

								%>
								<%=HTMLUtility.getList("semester",dto.getSemester() , map) %>
				
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("semester", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("semester", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									
								</div>

						<div class="form-group">
									<label>Exam Date<span style="color:red ">*</span></label>
									<div class="input-group">
									
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="fa fa-calendar-plus-o" aria-hidden="true"
											style="font-size: 1.1em;"></i>

										</span>
									</div>
									
									<input type="text"  id="datepicker"  class="form-control"
										name="examDate" readonly placeholder="Select Date of Exam" value="<%=DataUtility.getDateString(dto.getExamDate())%>">
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("examDate", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("examDate", request)%>">
										<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
									</span>
									<%
										}
									%>
								</div>
									</div>

								<div class="form-group">
								<label>Exam Time <span style="color:red ">*</span></label>
								<div class="input-group">
								
									 <div class="input-group-prepend">
										<span class="input-group-text"> 
										<i class="fa fa-clock"></i>
										</span>
									</div >
									
							<% 
									LinkedHashMap mapp = new LinkedHashMap();
									mapp.put("8:00 am to 11:00 am", "8:00 am to 11:00 am");
									mapp.put("12:00 pm to 3:00 pm", "12:00 pm to 3:00 pm");
									mapp.put("3:00 pm to 6:00 pm", "3:00 pm to 6:00 pm");
								

								%>
								<%=HTMLUtility.getList("examTime",dto.getExamTime() , mapp) %>
				
									<%
										if (DataValidator.isNotNull(ServletUtility.getErrormessage("examTime", request))) {
									%>
									<span class="text-danger pt-3" data-toggle="tooltip"
										data-placement="left"
										title="
										<%=ServletUtility.getErrormessage("examTime", request)%>">
										<i class="fa fa-exclamation-circle"></i>
									</span>
									<%
										}
									%>
									</div>
									
								</div>
							

							</div>
	<%if(dto.getId() > 0){ %>						
							<button type="submit" class="btn btn-success " name="operation"  value="<%=TimeTableCtl.OP_UPDATE %>">Update</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=TimeTableCtl.OP_CANCEL %>">Cancel</button>
	<%}else{ %>
							<button type="submit" class="btn btn-success " name="operation"  value="<%=TimeTableCtl.OP_SAVE %>">Save</button>
							<button type="submit" class="btn btn-warning " name="operation"  value="<%=TimeTableCtl.OP_RESET %>">Reset</button>
	
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