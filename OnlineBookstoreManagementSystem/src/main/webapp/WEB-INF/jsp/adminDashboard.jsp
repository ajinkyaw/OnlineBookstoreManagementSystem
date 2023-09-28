<!DOCTYPE html>
<html>
<head>
<title>Online Bookstore Management System</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
	text-align: center; /* Center align the content */
}

h1 {
	color: #007bff; /* Blue color for the heading */
}

/* Style for the container div */
.container {
	margin-top: 20px;
	/* Add some spacing between the container and the heading */
}

/* Style for the form and buttons */
.form-container {
	display: inline-block; /* Display form elements inline */
	margin: 0 10px; /* Add spacing between form elements */
}

button {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 20px;
	cursor: pointer;
	font-size: 18px; /* Increase font size for buttons */
	margin: 5px; /* Add spacing between buttons */
}

button:hover {
	background-color: #0056b3; /* Darker blue color on hover */
}
</style>
</head>
<body>
	<h1>Online Bookstore Management System</h1>

	<!-- Container div to hold form elements and buttons -->
	<div class="container">
		
		<form class="form-container" action="/getBookData" method="get">
			<button type="submit">Manage Books</button>
		</form>
		<form class="form-container" action="/getCategoryData" method="get">
			<button type="submit">Manage Categories</button>
		</form>
		<form class="form-container" action="/viewUsers" method="get">
			<button type="submit">Manage User Accounts</button>
		</form>

		
	</div>
	
	<div>
	<form class="form-container" action="/logout" method="get">
			<button type="submit">Log Out</button>
		</form>
	</div>
</body>
</html>
