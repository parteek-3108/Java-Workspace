<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Enter the Student Data</h1><br>
<form action="Authenticator" method="post">
<label>Name</label><input type="text" name="name" required><br>
<label>RollNo</label><input type="number" name="rollno" required><br>
<label>Section</label><input type="text" name="section" required><br>
<label>Phone No.</label><input type="text" name="phoneno" required><br>
<label>Branch Name</label><input type="text" name="branch" required><br>
<label>Email</label><input type="text" name="gmail" required><br>
<input type="hidden" name="mentorid" value='<%= request.getParameter("id") %>'>

<input type="submit" name="studentform" value="Add"><br>
<input type="submit"  name="studentform" value="View All">
</form>
</body>
</html>