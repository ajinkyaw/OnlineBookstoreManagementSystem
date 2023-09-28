<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.service.JDBCService, com.example.demo.util.JDBCUtil"%>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        form {
            background-color: #fff;
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            z-index: 1000;
        }

        .close-popup {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
        }
    .message-container {
        background-color: #f2f2f2;
        border: 1px solid #ccc;
        padding: 10px;
        margin: 10px;
    }

    .message-container p {
        font-size: 16px;
        color: #333;
    }
</style>
    
</head>
<body>
    <h1>User Registration</h1>
    <form method="post" action="/register">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" required><br>

        <label for="email">Email</label>
        <input type="text" id="email" name="email" required><br>

        <label for="username">Username</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Register">
    </form>

    <div id="popup" class="popup">
        <span id="popup-message"></span>
        <span class="close-popup" onclick="closePopup()">&times;</span>
    </div>
    
    <div class="message-container">
    <p>${message}</p>
	</div>
</body>
</html>
