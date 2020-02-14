
<%@page import="in.co.rays.ors.util.DataUtility"%>
<%@page import="in.co.rays.ors.util.HTMLUtility"%>
<%@page import="in.co.rays.ors.model.ModelFactory"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ors.controller.MarksheetListCtl"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.rays.ors.dto.StudentDTO"%>
<%@page import="in.co.rays.ors.model.StudentModelInt"%>
<%@page import="in.co.rays.ors.controller.MarksheetCtl"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<%@page import="in.co.rays.ors.dto.MarksheetDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Marksheet List</title>
<style type="text/css">
.input-group-text{
  width:40px;
  height:40px;
}
.fa-check{
color: green;
}

.content{

height: 780px;
/* width: 1327px; */
}

</style>

</head>
<body>
<div class="wrapper">
<%@ include file="Header.jsp" %>
<div class="content">

<jsp:useBean id="dto" class="in.co.rays.ors.dto.MarksheetDTO" scope="request"></jsp:useBean>
	  <h2 align="center" style="color: red">Marksheet List</h2>
 
 <div align="center">
 <% if (ServletUtility.getSuccessMessage(request) != null && ServletUtility.getSuccessMessage(request).length() > 0) { %>
		<div class="alert alert-success alert-dismissible fade show">
	        <strong>Success!</strong> <%=ServletUtility.getSuccessMessage(request) %>
	        <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
 <% } %>
 
	<% if (ServletUtility.getErrorMessage(request) != null && ServletUtility.getErrorMessage(request).length() > 0) {
%>
		<div class="alert alert-danger alert-dismissible fade show">
	        <strong>Error!</strong> <%=ServletUtility.getErrorMessage(request) %>
	        <button type="button" class="close" data-dismiss="alert">&times;</button>
       </div>
<%} %>
</div>

<form action="<%=ORSView.MARKSHEET_LIST_CTL %>" method="post" >
<!-- style="margin-left: 17px; -->

<%

List sslist = (List)request.getAttribute("slist");

   int pageNo= ServletUtility.getPageNo(request);
   int pageSize= ServletUtility.getPageSize(request);
   int index= (pageNo-1)*pageSize+1;
   
   List list = ServletUtility.getList(request);
	List nextlist =(List) request.getAttribute("nextlist");
	/* Iterator next=nextlist.iterator();
	MarksheetDTO mdto=null;
	while(next.hasNext()){
		mdto=(MarksheetDTO)next.next();
		System.out.println(mdto.getRollNo()+" "+mdto.getStudentname());
		
	} */
	
   Iterator it = list.iterator();
	
   if(list.size() != 0){ %>

<div class="container">


<div class="row">

<div  class="col-sm-3 col-xl-3">
  <div style="padding-bottom: 10px" class=" form-group has-search active-purple-2">
<%=HTMLUtility.getList("studentname", String.valueOf(dto.getStudentId()), sslist) %>
  </div>
 </div>
  
  <div class="col-sm-3 col-xl-3">
  <div style="padding-bottom: 10px"  class=" form-group has-search active-purple-2">
   <!--  <span class=" form-control-feedback"></span> -->
    <input type="text" style="height: 39px"   class="form-control form-control-sm ml-0 w-12" name="rollNo" placeholder="Enter Roll Number">
  </div>
  </div>
  
  <div  class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button class="btn btn-success" type="submit" name="operation" value="<%=MarksheetListCtl.OP_SEARCH%>">Search</button>
  </div>
  </div>
  
  <div class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button class="btn btn-warning" type="submit" name="operation" value="<%=MarksheetListCtl.OP_RESET%>">Reset</button>
  </div>
  </div>
  
<div  class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button style="background-color: green ; color: white" align="right" class="btn" name="operation" value="<%=MarksheetCtl.OP_NEW %>">
  Add <i class="fa fa-plus-circle "></i></button>
  </div>
  </div>
  
 </div>
</div>


<!-- table-responsive -->
<div class="table-responsive">
<table class="table table-condensed table-hover  table-light table-sm  table-bordered" align="center" border="1" width="100%" >
  <thead class="table-active">
    <tr align="center">
    <th scope="col"><input type="checkbox" id="select_all" name="Select">Select All</th>
      <th scope="col">S.No</th>
      <th scope="col">Roll No.</th>
      <th scope="col">Name</th>
      <th scope="col">Maths</th>
      <th scope="col">Physics</th>
      <th scope="col">Chemistry</th>
      <th scope="col">Total</th>
      <th scope="col">Percentage(%)</th>
      <th scope="col">Edit</th>
      <th scope="col">Delete</th>
    </tr>
  </thead>
  
<%
long id1 = 0;
while(it.hasNext()){
	   	
	    dto= (MarksheetDTO)it.next();
	  	   int total=dto.getMaths()+dto.getChemistry()+dto.getPhysics();
		  float percentage=(float)total/3;
		  percentage=Float.parseFloat(new DecimalFormat("##.##").format(percentage));
//	   System.out.println("id in list is"+dto.getId());
	   
/* 	   StudentModelInt model = ModelFactory.getInstance().getStudentModel();
	   StudentDTO dtoo = model.findByPK(dto.getId());  */
 %>
  <tbody align="center">
    <tr  class="table-active">
    <td><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
      <th scope="row"><%=index++ %></th>
      <td><%=dto.getRollNo() %></td>
     <td><%=dto.getStudentname() %></td>
      <td><%=dto.getMaths() %></td>
      <td><%=dto.getPhysics() %></td>
      <td><%=dto.getChemistry() %></td>
      <td><%=total %></td>
      <td><%=percentage %></td>
      <td><a style="color: #FF5733" href="MarksheetCtl?id=<%=dto.getId() %>">
      <i class="fa fa-pencil-square-o" style="color:blue" aria-hidden="true"></i>
      </a>
      </td>
      <td>
      <button type="submit"  class="btn btn-link text-primary" name="operation" value="<%=MarksheetCtl.OP_DELETE%>"><i class="fa fa-trash fa1"></i></button>
      </td>
    </tr>   
  </tbody>
 <%} %> 
</table>
</div>

 <div class="row">
 <div  class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button style="background-color: blue; color: white" class="btn blue-gradient btn-rounded btn-sm my-0" type="submit" name="operation" value="<%=MarksheetListCtl.OP_PREVIOUS%>"
  <%=(pageNo==1)?"disabled":"" %>><i class="fa fa-backward"></i> Previous</button>
  </div>
  </div>

 <div  class="col" align="right">
  <div  class="form-group" style="padding-bottom: 10px">
  <button style="background-color: blue; color: white" class="btn blue-gradient btn-rounded btn-sm my-0" type="submit" name="operation" value="<%=MarksheetListCtl.OP_NEXT%>"
  <%=((nextlist.size() == 0)?"disabled": "") %>>NEXT <i class="fa fa-forward"></i></button>
  </div>
  </div>  
 
</div> 
<input type="hidden" name="pageNo" value="<%=pageNo%>">
<input type="hidden" name="pageSize" value="<%=pageSize%>">
<%} %>

<%if(list.size()==0){ %>
<div align="center">
<button class="btn btn-primary " name="operation" value="<%=MarksheetListCtl.OP_BACK%>">Back</button>
</div>
<%} %>

</form>
</div>
<br>
<%@ include file = "Footer.jsp" %>
</div>
</body>
</html>