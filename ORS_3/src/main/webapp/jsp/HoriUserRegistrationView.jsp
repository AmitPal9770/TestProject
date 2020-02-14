<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

.body{
/* background-color: pink;
 */ 	width: 100%;
/* 	height:100vh; */
}
.centered-form{
	margin-top: 40px;
}
.container{

}
.centered-form .panel{
	margin-top:10px;
/* 	opacity:0.3%; */
	background:#ffffb3;
	box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
}

.btn-outline-danger{
    background: #f44c89;
	border:2px solid #f44c89;
	border-radius: 0px;
	width: 200px;
	margin-top: 15px;
	font-size: 20px;
	background:  transparent;
	margin-left: 170px; 
}

.btn-outline-success{
    background: #f44c89;
	border:2px solid #f44c89;
	border-radius: 0px;
	width: 200px;
	margin-top: 15px;
	font-size: 20px;
	background:  transparent;
    margin-left: 70px;
}

</style>

</head>
<body>
<%@include file="Header.jsp" %>

<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-6 col-md-6 col-sm-offset-2 col-md-offset-2">
        	<div class="panel panel-default">
        	<h1 style="color: red ; text-align: center ;font-variant: small-caps;">User Registration</h1>

				
			 			<div class="panel-body">
			    		<form role="form">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">First Name :<span style="color: red"> *</span> </label>
			                <input type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">Email Id :<span style="color: red">*</span> </label>
			    						<input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">
			    					</div>
			    				</div>
			    			</div>

							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">Last Name :<span style="color: red">*</span> </label>
			                <input type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">Password :<span style="color: red">*</span> </label>
			    						<input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">
			    					</div>
			    				</div>
			    			</div>

							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">Date of Birth :<span style="color: red">*</span> </label>
			                <input type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">Confirm Password. :<span style="color: red">*</span> </label>
			    						<input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">
			    					</div>
			    				</div>
			    			</div>


							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">Mobile No :<span style="color: red">*</span> </label>
			                <input type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<label style="color: blue">Gender :<span style="color: red">*</span> </label>
			    						<input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">
			    					</div>
			    				</div>
			    			</div>
							<button type="submit" class="btn btn-outline-success ">Submit</button>
					<button type="submit" class="btn btn-outline-danger ">Reset</button>
		
		<!-- 	    			<input type="submit" value="Register" class="btn btn-info btn-block">
		 -->	    		
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
<%@ include file= "Footer.jsp" %>

</body>
</html>