package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {
	
			//Connecting DB
		private Connection connect() 
		{
			Connection con = null;
			
			try
			 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthcare_app", "root", "");
			
			 }
			 catch(Exception e)
			 {
			 e.printStackTrace();
			 }

			 return con;
			}
	
		public String readAppointment() {
			String output = "";
			
			try {
				Connection con = connect();
				if(con == null) {
					return "Error while connecting to the database for reading.";
				}
				
				//prepare the html table 
				output = "<table border='1'><tr><th>Name</th><th>Address</th><th>Gender</th><th>Doctor</th><th>Phone</th><th>Date</th><th>Email</th><th>Comments</th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from appointment";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				//duplicate the rows
				while(rs.next()) {
					String appointmentID = Integer.toString(rs.getInt("appointmentID"));
					String Name = rs.getString("Name");
					String Address = rs.getString("Address");
					String Gender = rs.getString("Gender");
					String Doctor = rs.getString("Doctor");
					String Phone = rs.getString("Phone");
					String Date = rs.getString("Date");
					String Email = rs.getString("Email");
					String Comments = rs.getString("Comments");
					
				
				//add into the html table
					output += "<tr><td><input id='hidappointmentIDUpdate' name='hidappointmentIDUpdate' type='hidden' value='"+ appointmentID +"'>" + Name + "</td>";
					output += "<td>" + Address + "</td>";
					output += "<td>" + Gender + "</td>";
					output += "<td>" + Doctor + "</td>";
					output += "<td>" + Phone + "</td>";
					output += "<td>" + Date + "</td>";
					output += "<td>" + Email + "</td>";
					output += "<td>" + Comments + "</td>";
					
				//buttons
					output += "<td><input name='btnUpdate' type='button'value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button'value='Remove'class='btnRemove btn btn-danger' data-appointmentid='"
							+ appointmentID + "'>" + "</td></tr>";
				}
				
				con.close();
				output += "</table>";
			}catch(Exception e) {
				output = "Error while reading the Appointment";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		
		public String insertAppointment(String name,String address,String gender,String doctor,String phone,String date,String email,String comments) {
			String output = "";
			try {
				Connection con = connect();
				if(con == null) {
					return "Error while connecting to the database for inserting.";
				}
				
				//creating a prepared statement
				String query = "INSERT INTO `appointment`(`appointmentID`, `Name`, `Address`, `Gender`, `Doctor`, `Phone`, `Date`, `Email`, `Comments`)" + "values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, gender);
				preparedStmt.setString(6, doctor);
				preparedStmt.setString(6, phone);
				preparedStmt.setString(4, date);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, comments);
				
				preparedStmt.execute();
				con.close();
				
				
				String newAppointment = readAppointment();
				output = "{\"status\":\"success\", \"data\": \"" +
						 newAppointment + "\"}";
				
			}catch(Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the appointment.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String updateAppointment(String appointmentid,String name,String address,String gender,String doctor,String phone,String date,String email,String comments) {
			String output = "";
			try {
				Connection con = connect();
				if(con == null) {
					return "Error while connecting to the database for updating";
				}
				
				//create a prepared statement
				String query = "update appointment set Name=?,Address=?,Gender=?,Doctor=?,Phone=?,Date=?,Email=?,Comments=? where appointmentID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(3, address);
				preparedStmt.setString(4, gender);
				preparedStmt.setString(5, doctor);
				preparedStmt.setString(1, phone);
				preparedStmt.setString(3, date);
				preparedStmt.setString(4, email);
				preparedStmt.setString(5, comments);
				preparedStmt.setInt(6, Integer.parseInt(appointmentid));
				
				preparedStmt.execute();
				con.close();
				
				
				String newAppointment = readAppointment();
				output = "{\"status\":\"success\", \"data\": \"" +
						newAppointment + "\"}";
			}catch(Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		
		public String deleteAppointment(String id) {
			String output = "";
			try {
				Connection con = connect();
				if(con == null) {
					return "Error while connecting to the database for deleting.";
				}
				
				String query = "delete from appointment where appointmentID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.execute();
				con.close();
				
				String newAppointment = readAppointment();
				output = "{\"status\":\"success\", \"data\": \"" +
						newAppointment + "\"}";
			}catch(Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the Appointment.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
}
