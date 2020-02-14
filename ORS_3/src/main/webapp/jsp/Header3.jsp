

<%@page import="in.co.rays.ors.controller.LoginCtl"%>
<%@page import="in.co.rays.ors.controller.ORSView"%>
<%@page import="in.co.rays.ors.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 
<head>
 
 <style>
.header {
	

font-size: 16px; 
 background-color:  #ff8000;  

}


</style>
 
<!-- Required meta tags -->
 
<meta charset="utf-8">
 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
 
 
 
<!-- Bootstrap CSS -->
 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
 
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 
 
<!--fontawesome-->
 
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js" integrity="sha384-xymdQtn1n3lH2wcu0qhcdaOpQwyoarkgLVxC/wZ5q7h9gHtxICrpcaSUfygqZGOe" crossorigin="anonymous"></script>
 
<!--This is used for search icon. Instead putting icon manually it is loaded from fontawesome-->
 
 
 
 
<title>Responsive nav bar with Bootstrap 4</title>
 
</head>
 
<body>

<%
		UserDTO dto1 = (UserDTO) session.getAttribute("user");
		boolean userLoggedin = dto1 != null;
		String WelcomeMsg = "Hii ";
		if (userLoggedin) {
			String role = (String) session.getAttribute("role");
			WelcomeMsg += dto1.getFirstName() + " " + dto1.getLastName();
		} else {
			WelcomeMsg += " Guest";
		}
	%>
 
<!-- navbar -->

<!-- <div class="container-fluid header" > -->
 
<nav class="navbar navbar-expand-md navbar-light bg-dark fixed-top">
 
<a class="navbar-brand">
<img alt="" src="<%=ORSView.APP_CONTEXT%>/img/sun.jpg" width="150px" height="40px">

</a>
 
<button type="button" class="navbar-toggler bg-light" data-toggle="collapse" data-target="#nav">
 
<span class="navbar-toggler-icon"></span>
 
</button>
 
<div class="collapse navbar-collapse justify-content-between" id="nav">
 
<ul class="navbar-nav">
 
<li class="nav-item" >
 
<a class="nav-link text-light font-weight-bold px-1" href="<%=ORSView.WELCOME_CTL%>"><i class="fa fa-home" style="font-size: 22px; color: white"></i></a></li>

 
<% if(userLoggedin){ %>

 <li class="nav-item dropdown">
			<a class="nav-link text-light font-weight-bold px-1 dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-clipboard" ></i> Marksheet </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" style="font-size: 16px;" href="<%=ORSView.MARKSHEET_CTL%>"><i class="fas fa-notes-medical"></i> Add Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fas fa-file"></i> Get Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet List</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet Merit List</a>
			    </div>
			</li>
 
 
<%-- <li class="nav-item dropdown" data-toggle="dropdown">
<a class="nav-link text-light font-weight-bold px-3 dropdown-toggle" ><i class="fas fa-clipboard"></i> Marksheet </a>
<div class="dropdown-menu">
<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>"><i class="fas fa-notes-medical"></i> Add Marksheet</a>
<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fas fa-file"></i> Get Marksheet</a> 
<a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet List</a> 
<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet Merit List</a>
</div>
</li> --%>

<li class="nav-item dropdown">
			<a class="nav-link text-light font-weight-bold px-3 dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-user-tie"></i> User </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.USER_CTL%>"><i class="fas fa-user-plus"></i> Add User</a> 
					<a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class="fa fa-list"></i> User List</a>
				</div>
		 	</li>
 
 
<li class="nav-item dropdown">
			<a class="nav-link text-light font-weight-bold px-3 dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-shield"></i> Role </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>"><i class="fas fa-user-plus"></i> Add Role</a> 
					<a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>"><i class="fa fa-list"></i> Role List</a>
				</div>
			</li>


<li class="nav-item dropdown"><a
				class="nav-link text-light font-weight-bold px-3 dropdown-toggle"  id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-graduate"></i> Student </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class="fa fa-graduation-cap "></i> Add Student</a> <a
						class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-list "></i> Student List</a>
				</div></li>

<li class="nav-item dropdown"><a
				class="nav-link text-light font-weight-bold px-3 dropdown-toggle" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book-open "></i> Subject </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>"><i class="fa fa-book "></i> Add Subject</a> <a
						class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class="fa fa-list "></i> Subject List</a>
				</div></li>


<li class="nav-item dropdown"><a
				class="nav-link text-light font-weight-bold px-3 dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie "></i> Faculty </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>"><i class="fas fa-user-plus "></i> Add Faculty</a> <a
						class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class="fa fa-list "></i> Faculty List</a>
				</div></li>
				
				
			<li class="nav-item dropdown"><a
				class="nav-link text-light font-weight-bold px-3 dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-university "></i> College </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>"><i class="fas fa-plus "></i> Add College</a> <a
						class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-list "></i> College List</a>
				</div></li>

                <li class="nav-item dropdown"><a
				class="nav-link text-light font-weight-bold px-3 dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book"></i> Course </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>"><i class="fa fa-plus"></i> Add Course</a> <a
						class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-list "></i> Course List</a>

					</div></li>
					
					
			<li class="nav-item dropdown"><a
				class="nav-link text-light font-weight-bold px-3 dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book "></i> TimeTable </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>"><i class="fas fa-book-medical "></i> Add Timetable</a> <a
						class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class="fa fa-list"></i> Timetable List</a>
				</div></li>



<li class="nav-item dropdown">
				<a class="nav-link text-light font-weight-bold px-3 dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie"></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				
		
			 		<a  class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fas fa-sign-out-alt"> </i>Sign Out</a>
					<a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fas fa-unlock"> </i> Change Password</a> 
					<a class="dropdown-item" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class="fas fa-user-cog"> </i> My Profile</a>
					<a class="dropdown-item" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class="fa fa-clipboard"> </i> Java Doc</a>
					
				</div></li>

<%}else{ %>

<%-- <li class="nav-item dropdown" data-toggle="dropdown">
<a class="nav-link text-light font-weight-bold px-3 dropdown-toggle"  style="color: white"><i class="fa fa-user fa-lg" ></i><%=WelcomeMsg%> </a>
 <div class="dropdown-menu">
<a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class="fas fa-sign-in-alt"></i> Login</a> 
<a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fas fa-user-plus"></i> User Register</a> 
</div>
</li> --%>

<li class="nav-item dropdown ss">
			<a class="nav-link text-light font-weight-bold px-3 dropdown-toggle" id="navbardrop"
				data-toggle="dropdown" style="color: white"><i class="fa fa-user fa-lg" ></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				
					<a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class="fas fa-sign-in-alt"></i> Login</a> 
					<a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fas fa-user-plus"></i> User Register</a> 
				<%-- 	<a class="dropdown-item" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class="fa fa-unlock-alt"> </i> Forget Password</a> --%>
					
				</div></li>			
			
<%} %>	

</ul>

</div>
 
</nav>
<!-- </div>  -->
</body>
 
</html>
