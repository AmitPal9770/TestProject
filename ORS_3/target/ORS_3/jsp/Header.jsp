
<%@page import="in.co.rays.ors.dto.RoleDTO"%>
<%@page import="in.co.rays.ors.controller.LoginCtl"%>
<%@page import="in.co.rays.ors.dto.UserDTO"%>
<%@page import="in.co.rays.ors.controller.ORSView"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CHECK BOX LINKS  -->
<script  src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>	
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>


<!-- FONT AWESOME -->
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<!--4 FILE FOR BOOTSTRAP  -->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- FOR DATE PICKER -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $("#datepicker1").datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange:'1980:2025',
	  dateFormat:'dd-mm-yy',
    });
  });  
  </script>
  

<style>
.header {	
background-size:cover;
font-size: 15px; 
/*   background-color: #ff3300;  */ 
 background-image: url("<%=ORSView.APP_CONTEXT%>/image/img1.jpg");
}



</style>
</head>
<body>

	<%
		UserDTO dto1 = (UserDTO) session.getAttribute("user");
		boolean userLoggedin = dto1 != null;
		String WelcomeMsg = "Hii ";
		if (userLoggedin) {
			String role = (String) session.getAttribute("role");
			
			WelcomeMsg += dto1.getFirstName()+" "+"["+role+"]";
		} else {
			WelcomeMsg += " Guest";
		}
	%>
<!-- <div class="container-fluid header" > -->
<div class="header">	
	<nav class="navbar navbar-expand-sm bg-red navbar-dark">
		<a class="navbar-brand" >
		<img alt="" src="<%=ORSView.APP_CONTEXT%>/image/Rays.jpg" width="150px" height="40px"></a>

		
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
    </button>	
 	<div class="collapse navbar-collapse " id="collapsibleNavbar">
		<!-- Links -->
		<ul class="navbar-nav ml-auto" >
		<li class="nav-item">
			<a class="nav-link" href="<%=ORSView.WELCOME_CTL%>"><i class="fa fa-home" style="font-size: 22px"></i></a></li>

<% if(userLoggedin){ %>




<%if(dto1.getRoleId() == RoleDTO.ADMIN){ %>
			<!-- Dropdown -->
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-clipboard"></i> Marksheet </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL %>"><i class="fas fa-notes-medical"></i> Add Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fas fa-file"></i> Get Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet List</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet Merit List</a>
			    </div>
			</li>

			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-user-tie"></i> User </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.USER_CTL%>"><i class="fas fa-user-plus"></i> Add User</a> 
					<a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class="fa fa-list"></i> User List</a>
				</div>
		 	</li>

			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-shield"></i> Role </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>"><i class="fas fa-user-plus"></i> Add Role</a> 
					<a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>"><i class="fa fa-list"></i> Role List</a>
				</div>
			</li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle"  id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-graduate"></i> Student </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class="fa fa-graduation-cap "></i> Add Student</a> <a
						class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-list "></i> Student List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book-open "></i> Subject </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>"><i class="fa fa-book "></i> Add Subject</a> <a
						class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class="fa fa-list "></i> Subject List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie "></i> Faculty </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>"><i class="fas fa-user-plus "></i> Add Faculty</a> <a
						class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class="fa fa-list "></i> Faculty List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-university "></i> College </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>"><i class="fas fa-plus "></i> Add College</a> <a
						class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-list "></i> College List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book"></i> Course </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>"><i class="fa fa-plus"></i> Add Course</a> <a
						class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-list "></i> Course List</a>

					</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book "></i> TimeTable </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>"><i class="fas fa-book-medical "></i> Add Timetable</a> <a
						class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class="fa fa-list"></i> Timetable List</a>
				</div></li>


				
				<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie"></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown" style="margin: .125rem -10px;">
				
		
					<a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fas fa-unlock"> </i> Change Password</a> 
					<a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fas fa-user-cog"> </i> My Profile</a>
					<a class="dropdown-item" target="blank"   href="<%=ORSView.JAVA_DOC_VIEW%>"><i class="fa fa-clipboard"> </i> Java Doc</a>
			 	
			 		<a  class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fas fa-sign-out-alt"> </i> Sign Out</a>				
				</div></li>
<%} %>
<%if(dto1.getRoleId()== RoleDTO.STUDENT){ %>

			<!-- Dropdown -->
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-clipboard"></i> Marksheet </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fas fa-file"></i> Get Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet Merit List</a>
			    </div>
			</li>


			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle"  id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-graduate"></i> Student </a>
				<div class="dropdown-menu">
					<a	class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-list "></i> Student List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book-open "></i> Subject </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class="fa fa-list "></i> Subject List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie "></i> Faculty </a>
				<div class="dropdown-menu">
						<a class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class="fa fa-list "></i> Faculty List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-university "></i> College </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-list "></i> College List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book"></i> Course </a>
				<div class="dropdown-menu">
						<a class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-list "></i> Course List</a>

					</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book "></i> TimeTable </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class="fa fa-list"></i> Timetable List</a>
				</div></li>


				
				<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie"></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown" style="margin: .125rem -10px;">
				
			 		<a  class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fas fa-sign-out-alt"> </i> Sign Out</a>
					<a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fas fa-unlock"> </i> Change Password</a> 
					<a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fas fa-user-cog"> </i> My Profile</a>
					
				</div></li>
				
<%} %>				

<%if(dto1.getRoleId()== RoleDTO.KIOSK){ %>

			<!-- Dropdown -->
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-clipboard"></i> Marksheet </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fas fa-file"></i> Get Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet Merit List</a>
			    </div>
			</li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-university "></i> College </a>
				<div class="dropdown-menu">
						class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-list "></i> College List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book"></i> Course </a>
				<div class="dropdown-menu">
						class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-list "></i> Course List</a>

					</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book "></i> TimeTable </a>
				<div class="dropdown-menu">
					
						class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class="fa fa-list"></i> Timetable List</a>
				</div></li>


				
				<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie"></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				
			 		<a  class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fas fa-sign-out-alt"> </i> Sign Out</a>
					<a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fas fa-unlock"> </i> Change Password</a> 
					<a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fas fa-user-cog"> </i> My Profile</a>
					
				</div></li>


<%} %>				
<%if(dto1.getRoleId()== RoleDTO.FACULTY){ %>

			<!-- Dropdown -->
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-clipboard"></i> Marksheet </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL %>"><i class="fas fa-notes-medical"></i> Add Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fas fa-file"></i> Get Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet List</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet Merit List</a>
			    </div>
			</li>


			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle"  id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-graduate"></i> Student </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class="fa fa-graduation-cap "></i> Add Student</a> <a
						class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-list "></i> Student List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book-open "></i> Subject </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>"><i class="fa fa-book "></i> Add Subject</a> <a
						class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class="fa fa-list "></i> Subject List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-university "></i> College </a>
				<div class="dropdown-menu">
						class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-list "></i> College List</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book"></i> Course </a>
				<div class="dropdown-menu">
						class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-list "></i> Course List</a>

					</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book "></i> TimeTable </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>"><i class="fas fa-book-medical "></i> Add Timetable</a> <a
						class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class="fa fa-list"></i> Timetable List</a>
				</div></li>


				
				<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie"></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				
			 		<a  class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fas fa-sign-out-alt"> </i> Sign Out</a>
					<a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fas fa-unlock"> </i> Change Password</a> 
					<a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fas fa-user-cog"> </i> My Profile</a>
						
				</div></li>

<%} %>
<%if(dto1.getRoleId()== RoleDTO.COLLEGE){ %>				

			<!-- Dropdown -->
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown"><i class="fas fa-clipboard"></i> Marksheet </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL %>"><i class="fas fa-notes-medical"></i> Add Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fas fa-file"></i> Get Marksheet</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet List</a> 
					<a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list "></i> Marksheet Merit List</a>
			    </div>
			</li>

		
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle"  id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-graduate"></i> Student </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class="fa fa-graduation-cap "></i> Add Student</a> <a
						class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-list "></i> Student List</a>
				</div></li>

		
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie "></i> Faculty </a>
				<div class="dropdown-menu">
							class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class="fa fa-list "></i> Faculty List</a>
				</div></li>

		
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book"></i> Course </a>
				<div class="dropdown-menu">
						class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-list "></i> Course List</a>

					</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-book "></i> TimeTable </a>
				<div class="dropdown-menu">
						class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class="fa fa-list"></i> Timetable List</a>
				</div></li>


				
				<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie"></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown"  >
				
			 		<a  class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fas fa-sign-out-alt"> </i> Sign Out</a>
					<a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fas fa-unlock"> </i> Change Password</a> 
					<a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fas fa-user-cog"> </i> My Profile</a>
					
				</div></li>

<%} %>				
				
	<%}else{ %>			
			
			<%-- <li class="nav-item ss">
			<a class="nav-link" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fa fa-sign-in" style="font-size: 26px"></i><small>User Registration</small></a></li>
			<li class="nav-item">
			<a class="nav-link" href="<%=ORSView.LOGIN_CTL%>"><i class="fa fa-sign-in" style="font-size: 26px"></i><small>Login</small></a></li>
			 --%>
			<li class="nav-item dropdown" >
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"><i class="fas fa-user-tie"></i><%=WelcomeMsg%> </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown" style="margin: .125rem -47px 0">
				
					<a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class="fas fa-sign-in-alt"></i> Login</a> 
					<a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fas fa-user-plus"></i> User Register</a> 
				<%-- 	<a class="dropdown-item" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class="fa fa-unlock-alt"> </i> Forget Password</a> --%>
					
				</div></li>			
			
	<%} %>		

		</ul>

	</nav>

</div>


</body>
</html>

