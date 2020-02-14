<%@page import="java.util.List"%>
<%@page import="in.co.rays.ors.controller.MarksheetMeritListCtl"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.rays.ors.dto.MarksheetDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.ors.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/image/logo.png" sizes="16x16" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>MarksheetMeritList</title>
<style type="text/css">
.input-group-text{
  width:40px;
  height:40px;
}
.fa-check{
color: green;
}


</style>

</head>
<body id="bodycontent">
<div class="content">
<%@ include file="Header.jsp" %>
<div class="content-inside">
<jsp:useBean id="dto" class="in.co.rays.ors.dto.MarksheetDTO" scope="request"></jsp:useBean>
	  <h2 align="center" style="color: red">MarksheetMerit List</h2>
 
<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL %>" method="post">

<%
   int pageNo= ServletUtility.getPageNo(request);
   int pageSize= ServletUtility.getPageSize(request);
  
   int index= (pageNo-1)*pageSize+1;
   List list = ServletUtility.getList(request);
   Iterator it = list.iterator();
	
   if(list.size() != 0){ %>


<!-- table-responsive -->
<div class="table-responsive">
<table class="table table-condensed table-hover  table-light table-sm  table-bordered" align="center" border="1" width="100%" >
  <thead class="table-active">
    <tr align="center">
      <th scope="col">S.No</th>
      <th scope="col">Roll No.</th>
      <th scope="col">Name</th>
      <th scope="col">Maths</th>
      <th scope="col">Physics</th>
      <th scope="col">Chemistry</th>
      <th scope="col">Total</th>
      <th scope="col">Percentage(%)</th>
    </tr>
  </thead>
  
<%
//long id1 = 0;
while(it.hasNext()){
	   	
	    dto= (MarksheetDTO)it.next();
	  	   int total=dto.getMaths()+dto.getChemistry()+dto.getPhysics();
		  float percentage=(float)total/3;
		  percentage=Float.parseFloat(new DecimalFormat("##.##").format(percentage));
//	   System.out.println("id in list is"+dto.getId());
	   

 %>
  <tbody align="center">
    <tr  class="table-active">
      <th scope="row"><%=index++ %></th>
      <td><%=dto.getRollNo() %></td>
     <td><%=dto.getStudentname() %></td>
      <td><%=dto.getMaths() %></td>
      <td><%=dto.getPhysics() %></td>
      <td><%=dto.getChemistry() %></td>
      <td><%=total %></td>
      <td><%=percentage %></td>
    </tr>   
  </tbody>
 <%} %> 
</table>
</div>


<input type="hidden" name="pageNo" value="<%=pageNo%>">
<input type="hidden" name="pageSize" value="<%=pageSize%>">
<%} %>


<div align="center">
<button class="btn btn-primary " name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>">Back</button>
<a href="<%= ORSView.JESPER_CTL%>"  button class="btn btn-primary"  target="blank" >Print</a>

</div>


</form>
</div>
</div>
</body>
</html>