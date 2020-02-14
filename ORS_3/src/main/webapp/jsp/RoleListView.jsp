<%@page import="in.co.rays.ors.dto.RoleDTO"%>
<%@page import="in.co.rays.ors.controller.RoleCtl"%>
<%@page import="in.co.rays.ors.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Role List</title>
<style type="text/css">
.input-group-text{
  width:40px;
  height:40px;
}
.fa-check{
color: green;
}
.content{
height: 650px;
/* width: 1327px; */
}

</style>

</head>
<body>
<div class="wrapper">
<%@ include file="Header.jsp" %>
<div class="content">
<jsp:useBean id="dto" class="in.co.rays.ors.dto.RoleDTO" scope="request"></jsp:useBean>
	  <h2 align="center" style="color: red">Role List</h2>
 
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

<form action="<%=ORSView.ROLE_LIST_CTL %>" method="post">
<!-- style="margin-left: 17px; -->
<%

List sslist = (List)request.getAttribute("slist");

   int pageNo= ServletUtility.getPageNo(request);
   int pageSize= ServletUtility.getPageSize(request);
   int index= (pageNo-1)*pageSize+1;
   
   List list = ServletUtility.getList(request);
   int count = list.size();
   Iterator it = list.iterator();
	
   if(list.size() != 0){ %>

<div class="container">


<div class="row">

<div  class="col-sm-3 col-xl-3">
  <div style="padding-bottom: 10px" class=" form-group has-search active-purple-2">
<!--     <span class=" form-control-feedback"></span>

 --><!--     <input type="text" class="form-control form-control-sm sm ml-0 w-15" name="studentId" placeholder="Enter Student Name"> -->
	<%=HTMLUtility.getList("rolename", String.valueOf(dto.getId()), sslist) %>
  </div>
 </div>
  
  <div  class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button class="btn btn-success" type="submit" name="operation" value="<%=RoleCtl.OP_SEARCH%>">Search</button>
  </div>
  </div>
  
  <div class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button class="btn btn-warning" type="submit" name="operation" value="<%=RoleCtl.OP_RESET%>">Reset</button>
  </div>
  </div>
  
<div  class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button style="background-color: green ; color: white" align="right" class="btn" name="operation" value="<%=RoleCtl.OP_NEW %>">
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
      <th scope="col">Role Name</th>
      <th scope="col">Description</th>
      <th scope="col">Edit</th>
      <th scope="col">Delete</th>
    </tr>
  </thead>
  
<%
long id1 = 0;
while(it.hasNext()){
	   	
	    dto= (RoleDTO)it.next();
 %>
  <tbody align="center">
    <tr  class="table-active">
    <td><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
      <th scope="row"><%=index++ %></th>
      <td><%=dto.getName() %></td>
     <td><%=dto.getDescription() %></td>
      <td><a style="color: #FF5733" href="RoleCtl?id=<%=dto.getId() %>">
      <i class="fa fa-pencil-square-o" style="color:blue" aria-hidden="true"></i>
      </a>
      </td>
      <td>
      <button type="submit"  class="btn btn-link text-primary" name="operation" value="<%=RoleCtl.OP_DELETE%>"><i class="fa fa-trash fa1"></i></button>
      </td>
    </tr>   
  </tbody>
 <%} %> 
</table>
</div>

 <div class="row">
 <div  class="col">
  <div  class="form-group" style="padding-bottom: 10px">
  <button style="background-color: blue; color: white" class="btn blue-gradient btn-rounded btn-sm my-0" type="submit" name="operation" value="<%=RoleCtl.OP_PREVIOUS%>"
  <%=(pageNo==1)?"disabled":"" %>><i class="fa fa-backward"></i> Previous</button>
  </div>
  </div>

 <div  class="col" align="right">
  <div  class="form-group" style="padding-bottom: 10px">
  <button style="background-color: blue; color: white" class="btn blue-gradient btn-rounded btn-sm my-0" type="submit" name="operation" value="<%=RoleCtl.OP_NEXT%>"
  <%=((list.size()<pageSize || index==count)?"disabled":"") %>>NEXT <i class="fa fa-forward"></i></button>
  </div>
  </div>  
 
</div> 
<input type="hidden" name="pageNo" value="<%=pageNo%>">
<input type="hidden" name="pageSize" value="<%=pageSize%>">
<%} %>

<%if(list.size()==0){ %>
<div align="center">
<button class="btn btn-primary " name="operation" value="<%=RoleCtl.OP_BACK%>">Back</button>
</div>
<%} %>
</form>
</div>
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>