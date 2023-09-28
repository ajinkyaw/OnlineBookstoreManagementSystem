<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="com.example.demo.model.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	background-color: #ff5555; /* Red color for the button */
	color: #fff;
	border: none;
	border-radius: 3px;
	padding: 10px 20px;
	cursor: pointer;
	font-size: 18px; /* Increase font size for buttons */
	margin: 5px; /* Add spacing between buttons */
}

button:hover {
	background-color: #ff0000; /* Darker red color on hover */
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
	<table border="1">
		<thead>
			<tr>
				<th>Book ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Genre</th>
				<th>Price</th>
				<th>Availability</th>
				<th>Remove From Cart</th>
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
						<!-- Add a button/form to remove the book from the cart -->
						<form action="/removeFromCart" method="post">
							<input type="hidden" name="bookId" value="${book.bookId}" />
							<button type="submit">Remove From Cart</button>
						</form>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<tbody>
		<tr>
			<td>
				<!-- Add a button/form to remove the book from the cart -->
				<form action="/proceedToCheckout" method="post"
					onsubmit="calculateTotal()">
					<!-- ... other form elements ... -->
					<input type="hidden" id="totalPrice" name="totalPrice" value="0.00">
					<button type="submit">Proceed To Checkout</button>
				</form>

			</td>
		</tr>
	</tbody>

<script>
    function calculateTotal() {
        console.log("Calculating total...");

        let total = 0.00;
        const cartItems = document.querySelectorAll('table tbody tr');

        cartItems.forEach((item) => {
            const priceCell = item.querySelector('td:nth-child(5)'); // Adjust the column index as needed (5 for the "Price" column)
            const price = parseFloat(priceCell.textContent);
            console.log("Price: " + price); // Add this line
            total += price;
        });

        // Update the hidden input with the calculated total
        document.getElementById('totalPrice').value = total.toFixed(2);
    }
</script>

	<div class="message-container">
		<p>${message}</p>
	</div>
</body>
</html>
