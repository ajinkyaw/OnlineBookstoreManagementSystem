<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="com.example.demo.model.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
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

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 18px;
            margin: 5px;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
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
    </style>
    <title> Available in Store</title>
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
    <h1>Categories available in Store</h1>
    <table border="1">
        <thead>
            <tr>
                <th>CategoryId</th>
                <th>Category</th>
                <th>Remove Category From Store</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.categoryId}</td>
                    <td>${category.category}</td>
                    <td>
                        <!-- Add a button/form to add the book to the cart -->
                        <form action="/removeCategoryFromStore" method="post">
                            <input type="hidden" name="categoryId" value="${category.categoryId}" />
                            <button type="submit">Remove Category</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="message-container">
    <p>${message}</p>
	</div>
    <h1>Add New Category In Store</h1>
    <form method="Post" action="/addCategoryInStore">
        <label for="title">Category</label>
        <input type="text" id="category" name="category" required><br>
        <button type="submit">Add Category in Store</button>
    </form>
</body>
</html>
