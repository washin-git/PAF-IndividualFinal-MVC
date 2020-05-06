<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Appointment" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment page</title>
<link rel ="stylesheet" href ="View/boostrap.min.css">
<script src="Component/jquery-3.2.1.min.js"></script>
<script src="Component/appointment.js"></script>

</head>
<body>

			<h1>Appointment Details</h1>
			<form name = "formAppointment" method="post" action ="Appointment.jsp" > 
	
		<br>
	
	<!-- Name -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">Name :</span>
		</div>
		<input type="text" id="txtName" name="txtName" placeholder="Enter the name">
	</div>
	<!-- Address -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">Address :</span>
		</div>
		<input type="text" id="txtName" name="txtName" placeholder="Address">
	</div>
	<!-- Gender -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">Gender :</span>
		</div>
		&nbsp;&nbsp;Male
		<input type="Radio" id="rdoGenderMale" value="Male" name ="rdoGender">
		&nbsp;&nbsp;Female
		<input type="Radio" id="rdoGenderFemale" value="Female" name ="rdoGender">
	</div>
	<!-- Doctor -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">Doctor :</span>
		</div>
		<select name="ddlDoctor" id="ddlDoctor">
			<option value="0">--Select Doctor--</option>
			<option value="1">Dr.Kalara</option>
			<option value="2">Dr.Sunil Perera</option>
			<option value="3">Dr.Malliga</option>
			<option value="4">Dr.Vasanthy</option>
		</select>
	</div>
	<!-- Phone -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">Phone :</span>
		</div>
		<input type="text" id="txtPhone" name="txtPhone" placeholder="077-0822484">
	</div>
	<!-- Date -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">desired date of meeting :</span>
		</div>		
	<input type="date" id="birthday" name="birthday">
	</div>
	<!-- Email -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">Email :</span>
		</div>
		<input type="email" id="txtEmail" name="txtEmail" placeholder="example@gmail.com">
	</div>
	<!-- Command -->
	<div class ="input-group-sm mb-3">
		<div class ="input-group-prepend">
			<span class="input-group-text" id="lblName">Comments :</span>
		</div>
		<input type="text" id="txtName" name="txtCmd" placeholder="Describe Patient">
	</div>
	


<input type ="button" id="btnsubmit" value ="Save" class ="btn btn-primary">
<input type="hidden" id="hidappIDSave" name="hidappIDSave" value="">



</form>
<div id="alertSuccess" class ="alert alert-success"></div>
<div id="alertError" class ="alert alert-danger"></div>
	<br> 
	<div id ="divAppointmentGrid">
<%
 Appointment appObj = new Appointment();
 out.print(appObj.readAppointment());
%>

</body>
</html>

