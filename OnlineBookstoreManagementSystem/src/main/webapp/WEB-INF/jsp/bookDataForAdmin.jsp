<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="com.example.demo.model.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>All Books in Store</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

h1 {
	color: #007bff;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: left;
}

th {
	background-color: #007bff;
	color: #fff;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
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

form label {
	font-weight: bold;
}

form input[type="text"], form input[type="number"], form input[type="email"]
	{
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

form button[type="submit"] {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 20px;
	cursor: pointer;
}

form button[type="submit"]:hover {
	background-color: #0056b3;
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
</style>
</head>
<body>

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
		<form class="form-container" action="/adminDashboard" method="get">
			<button type="submit">Dashboard</button>
		</form>

	</div>
	<h1>All Books in Store</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Book ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Genre</th>
				<th>Price</th>
				<th>Availability</th>
				<th>Remove Book From Store</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.bookId}</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.genre}</td>
					<td>${book.price}</td>
					<td>${book.availability}</td>
					<td>
						<!-- Add a button/form to add the book to the cart -->
						<form action="/removeBookFromStore" method="post">
							<input type="hidden" name="bookId" value="${book.bookId}" />
							<button type="submit">Remove Book</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="message-container">
    <p>${message}</p>
	</div>
	<h1>Add New Book In Store</h1>
	<form method="Post" action="/addBookInStore">
		<label for="title">Book Title</label> <input type="text" id="title"
			name="title" required><br> <label for="author">Author</label>
		<input type="text" id="author" name="author" required><br>
		<label for="genre">Genre</label> <input type="text" id="genre"
			name="genre" required><br> <label for="price">Price</label>
		<input type="number" id="price" name="price" step="0.01" required><br>
		<label>Availability:</label>
    <input type="radio" id="available" name="availability" value="1" required>
    <label for="available">Available</label>
    
    <input type="radio" id="notAvailable" name="availability" value="0" required>
    <label for="notAvailable">Not Available</label><br>
		<button type="submit">Add Book in Store</button>
	</form>
</body>
</html>
