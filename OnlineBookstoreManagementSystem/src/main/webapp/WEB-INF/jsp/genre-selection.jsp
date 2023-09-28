<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Books</title>
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

        form {
            margin-top: 20px; /* Add some spacing between the form and the heading */
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        select, button {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        select {
            width: 100%;
            margin-bottom: 10px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
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
	padding: 5px 10px;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
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
    </style>
</head>
<body>

	<div class="container">

		<form class="form-container" action="/books" method="get">
			<button type="submit">View All Books</button>
		</form>
		<form class="form-container" action="/genre-selection" method="get">
			<button type="submit">View Books By Category</button>
		</form>
		<form class="form-container" action="/viewCart" method="get">
			<button type="submit">My Cart</button>
		</form>
		<form class="form-container" action="/userDashboard" method="get">
			<button type="submit">Dashboard</button>
		</form>

	</div>
	
    <h1>All Books</h1>
    <form action="/books/byGenre" method="get">
        <label for="genre">Choose a Genre:</label>
        <select name="genre" id="genre">
            <option value="">Select a genre...</option>
            <c:forEach items="${genreList}" var="genre">
                <option value="${genre}">${genre}</option>
            </c:forEach>
        </select>
        <button type="submit">Submit</button>
        <input type="hidden" name="selectedGenre" id="selectedGenre" value="" />
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Availability</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.bookId}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <td>${book.availability}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <script>
        const genreSelect = document.getElementById("genre");
        const selectedGenreInput = document.getElementById("selectedGenre");

        genreSelect.addEventListener("change", function () {
            selectedGenreInput.value = genreSelect.value;
        });
    </script>
    <div class="message-container">
		<p>${message}</p>
	</div>
    
</body>
</html>
