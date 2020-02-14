<%@page import="in.co.rays.ors.controller.GetMarksheetCtl"%>
<%@page import="in.co.rays.ors.util.DataValidator"%>
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<html>
<head>
<style type="text/css">
body {
	background-image: url('./img/bg1.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	height: 100%;
	font-family: 'Numans', sans-serif;
}

.btn-primary{
    margin-left: 80px;
    	width: 150px;
}
.content{
height: 700px;
}



</style>
<title>Get Marksheet</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
	
</head>
<body>
<div class = "wrapper">
	<%@ include file="Header.jsp"%>
<div class="content">
<br>
	<jsp:useBean id="dto" class="in.co.rays.ors.dto.MarksheetDTO"
		scope="request"></jsp:useBean>

	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 ml-auto mr-auto">
					<div class="card card-login" style="margin-bottom: 20px ;border-color: darkblue" >
						<form class="form" method="post" action="<%=ORSView.GET_MARKSHEET_CTL%>">
							
							
							<div class="card-header card-header-primary text-center" style="background-color: transparent;border-color: darkblue">
								<h4 class="card-title" style="color: red">Get Marksheet</h4>
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
								
					</div>
							
							<button type="submit" class="btn btn-primary " name="operation"  value="<%=GetMarksheetCtl.OP_GO %>">Go</button>
							<button type="submit" class="btn btn-primary " name="operation"  value="<%=GetMarksheetCtl.OP_RESET %>">Reset</button>
	
							
						</form>
					</div>
				</div>
			</div>
		</div>
		
	
<div align="center" style="margin-bottom: 15px" >
                <%
                     if (dto.getRollNo() != null  && dto.getRollNo().trim().length() > 0 ) { 
                	    if (dto.getStudentname() != null ) {
                %>
			<table border="5">
				<table border="1" width="40%">
				  <tr align="center"><th></th>
				  	<td><h2>Rays Technologies</h2></td>
				  </tr></table>
				  
				 <table border="1" width="40%">
				 	<tr>
				 		<th align="center"> Name</th>
				 		<td align="center"> <%=DataUtility.getStringData(dto.getStudentname()) %></td>
				 		<th align="center"> Roll No</th>
				 		<td align="center"> <%=DataUtility.getStringData(dto.getRollNo()) %> </td>
				 						 
					</tr>
				 	<tr>
				 		<td align="center"> Status</td>
				 		<th align="center">Regular</th>
				 		<td align="center"> Course</td>
				 		<th align="center">BE</th>				 
					</tr>			
				 </table> 
	<%
	int phy =DataUtility.getInt(DataUtility.getStringData(dto.getPhysics()));
	int chem =DataUtility.getInt(DataUtility.getStringData(dto.getChemistry()));
	int math =DataUtility.getInt(DataUtility.getStringData(dto.getMaths()));
	int total = (phy+chem+math);
	float perc = total/3;
	%>
					  
			<table border="1" width="40%">
				<tr>
					<th align="center" style="width: 25%">Subject</th>
					<th align="center" style="width: 25%">Maximum Marks</th>
					<th align="center" style="width: 25%">Minimum Marks</th>
					<th align="center" style="width: 25%">Marks Obtained</th>
					<th align="center" style="width: 25%">Grade</th>
				</tr>
				<tr>
				<td align="center">Physics</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=phy %>
				
				<%if(phy<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(phy <=100 && phy >85){ %> A+
				<%} else if(phy<=85 && phy > 75 ) {%>B+
				<%} else if(phy<=75 && phy > 65 ) {%>B
				<%} else if(phy<=65 && phy > 55 ){ %>C+
				<%} else if(phy<=55 && phy >=33  ){ %>C
				
				<%} else if(phy< 33 && phy >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
			
			<tr>
				<td align="center">Chemistry</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=chem %>
				
				<%if(chem<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(chem <=100 && chem >85){ %> A+
				<%} else if(chem<=85 && chem > 75 ) {%>B+
				<%} else if(chem <=75 && chem > 65 ) {%>B
				<%} else if(chem <=65 && chem > 55 ){ %>C+
				<%} else if(chem <=55 && chem >=33  ){ %>C
				
				<%} else if(chem < 33 && chem >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
					
			<tr>
				<td align="center"> Maths</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=math %>
				
				<%if(math<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(math <=100 && math >85){ %> A+
				<%} else if(math <=85 && math > 75 ) {%>B+
				<%} else if( math <=75 && math > 65 ) {%>B
				<%} else if(math <=65 && math > 55 ){ %>C+
				<%} else if(math <=55 && math >=33  ){ %>C
				
				<%} else if(math < 33 && math >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
			</table>	  
			
			<table border="1" width="40%">
			<tr>
				<th>Total</th>
				<th>Percentage</th>
				<th>Division</th>
				<th>Result</th>
			</tr>
			<tr>
				<th><%=total %>
				<% if(total<99 || phy<33|| chem<33|| 	math<33){ %>
				<span style="color: red">*</span>
				<% } %>
				</th>
			
				<th><%=perc %> %</th>
				<th>
				<% if(perc <100 && perc >= 60){ %>1<sup>st</sup>
				<%} else if(perc <60 && perc >=40){ %>2<sup>st</sup>
				<%} else if(perc <40 && perc >=0){ %>3<sup>st</sup>
				<%} %>
				</th>
				
				<th>
				<%
				if(phy >=33 && chem>=33 && math >= 33) { %>
				<span style="color: green"> Pass</span>
				<%}else{ %>
				<span style="color: red"> Fail</span>
				<% } %>
				
				</th>
				</tr>
			</table>
			
			<%}
			} %>
	</table>		
</div>
	
	
	
		
</div>		
<%@ include file ="Footer.jsp" %>
</div>
</body>

</html>