<%@page import="model.CompleteProject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Add Complete Projects</h1>
<form id="formItem" name="formItem">
 RID: 
 <input id="rid" name="rid" type="text" class="form-control form-control-sm">
 <br> Project Code: 
 <input id="proj_code" name="proj_code" type="text" class="form-control form-control-sm">
 <br> Project Name: 
 <input id="proj_name" name="proj_name" type="text" class="form-control form-control-sm">
 <br> Project Description: 
 <input id="proj_desc" name="proj_desc" type="text" class="form-control form-control-sm">
 <br> Skills Required: 
 <input id="skills_required" name="skills_required" type="text" class="form-control form-control-sm">
 <br> Payment Method: 
 <input id="payment_method" name="payment_method" type="text" class="form-control form-control-sm">
 <br> Estimate Budget: 
 <input id="estimate_budget" name="estimate_budget" type="text" class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 CompleteProject project = new CompleteProject(); 
 out.print(project.readCompleteProjects()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>