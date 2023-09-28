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

        /* Style for forms and buttons */
        form {
            display: inline-block; /* Display forms inline */
            margin: 0 10px; /* Add spacing between forms */
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

    <!-- Form for User Login button -->
    <form action="/login" method="get">
        <button type="submit">User Login</button>
    </form>

    <!-- Form for User Register button -->
    <form action="/register" method="get">
        <button type="submit">User Register</button>
    </form>
    
    <!-- Form for Admin Login button -->
    <form action="/adminLogin" method="get">
        <button type="submit">Admin Login</button>
    </form>

    <!-- Form for Admin Register button -->
    <form action="/adminRegister" method="get">
        <button type="submit">Admin Register</button>
    </form>
</body>
</html>
