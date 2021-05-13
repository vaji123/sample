package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class CompleteProject {
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher", "root", "");
		 }                              
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }

		public String insertCompleteProjects(String rid, String code, String name, String desc, String skills, String pay_method, String budget)
		 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting."; //if the connection is not correct
				}
				
				// create a prepared statement
				
				String query = " insert into complete_project(`proj_id`,`rid`,`proj_code`,`proj_name`,`proj_desc`,`skills_required`,`payment_method`,`estimate_budget`)"
						+ " values (?,?, ?, ?, ?, ?, ?, ?)";

		 
				PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				// binding values
				
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, rid);
				preparedStmt.setString(3, code);
				preparedStmt.setString(4, name);
				preparedStmt.setString(5, desc);
				preparedStmt.setString(6, skills);
				preparedStmt.setString(7, pay_method);
				preparedStmt.setDouble(8, Double.parseDouble(budget));
				
				// execute the statement

				preparedStmt.execute();
				con.close();
				//display output
				String newproject = readCompleteProjects(); 
				output = "{\"status\":\"success\", \"data\": \"" +  newproject + "\"}"; 
			}
			catch (Exception e)
			{
				output = "Error while inserting the complete project."; // if there is an issue in insert code
				System.err.println(e.getMessage());
			}
		 return output;
		 }
		
		public String readCompleteProjects()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; //if the connection is not correct
		 }
		 
		 // Prepare the html table to be displayed

		 
		 output = "<table border='1'><tr><th>Project Code</th><th>Researcher Code</th>" +
		 "<th>Project Name</th>" +
		 "<th>Project Description</th>" +
		 "<th> Skills Required</th>" +
		 "<th>Payment Method</th>" +
		 "<th>Estimate Budget</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from complete_project";
		 
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 
		 while (rs.next())
		 {
		 String proj_id = Integer.toString(rs.getInt("proj_id"));
		 String rid = rs.getString("rid");
		 String proj_code = rs.getString("proj_code");
		 String proj_name = rs.getString("proj_name");
		 String proj_desc = rs.getString("proj_desc");
		 String skills_required = rs.getString("skills_required");
		 String payment_method = rs.getString("payment_method");
		 String estimate_budget = Double.toString(rs.getDouble("estimate_budget"));
		
		 // Add into the html table

		 output += "<tr><td>" + proj_code + "</td>";
		 output += "<td>" + rid + "</td>";
		 output += "<td>" + proj_name + "</td>";
		 output += "<td>" + proj_desc + "</td>";
		 output += "<td>" + skills_required + "</td>";
		 output += "<td>" + payment_method + "</td>";
		 output += "<td>" + estimate_budget + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
		+ "class='btnUpdate btn btn-secondary' data-itemid='" + proj_id + "'></td>"
		+ "<td><input name='btnRemove' type='button' value='Remove' "
		+ "class='btnRemove btn btn-danger' data-itemid='" + proj_id + "'></td></tr>"; 
		 }
		 con.close();
		 
		 // Complete the html table
		 
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the complete projects."; //if an issue in reading part
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String updateCompleteProjects(String ID, String rid, String code, String name, String desc, String skills, String pay_method, String budget)

		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for updating."; // if there is a connection issue
		 }
		 // create a prepared statement
		 
		 String query = "UPDATE complete_project SET rid=?,proj_code=?,proj_name=?,proj_desc=?,skills_required=?,payment_method=?,estimate_budget=?WHERE proj_id=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 
		 preparedStmt.setString(1, rid);
		 preparedStmt.setString(2, code);
		 preparedStmt.setString(3, name);
		 preparedStmt.setString(4, desc);
		 preparedStmt.setString(5, skills);
		 preparedStmt.setString(6, pay_method);
		 preparedStmt.setDouble(7, Double.parseDouble(budget));
		 preparedStmt.setInt(8, Integer.parseInt(ID));
		 
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
			//display output
		 String newproject = readCompleteProjects(); 
			output = "{\"status\":\"success\", \"data\": \"" +  newproject + "\"}";
		 
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the complete project."; //if there is an issue in updating part
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

		public String deleteCompleteProjects(String proj_id)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; //if there is a connection issue
		  }
		 // create a prepared statement
		 
		 String query = "delete from complete_project where proj_id=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 
		 preparedStmt.setInt(1, Integer.parseInt(proj_id));
		 
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
			//display output
		 String newproject = readCompleteProjects(); 
			output = "{\"status\":\"success\", \"data\": \"" +  newproject + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the complete project."; //if there is an issue in deleting part
		 System.err.println(e.getMessage()); 
		 }
		 return output;
		 }

}
