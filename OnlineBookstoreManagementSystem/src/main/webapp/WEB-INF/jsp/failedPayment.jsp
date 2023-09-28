<!DOCTYPE html>
<html>
<head>
    <title>Checkout Confirmation</title>
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
            color: #007bff;
        }

        h2 {
            margin-top: 20px;
            color: #333;
        }

        p {
            font-size: 18px;
            text-align: center;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }
            /* Form container styling */
    #paymentForm {
        background-color: #fff;
        width: 300px;
        margin: 20px auto;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }

    /* Label styling */
    label {
        display: block;
        margin-bottom: 5px;
    }

    /* Input field styling */
    input[type="text"],
    input[type="number"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    /* Button styling */
    button[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 3px;
        padding: 10px 20px;
        cursor: pointer;
    }

    button[type="submit"]:hover {
        background-color: #0056b3;
    }
        
    </style>
</head>
<body>
    <h1>${message}</h1>

    <p>Thank you for your order!</p>

    <!-- Add a link back to the home page or any other relevant pages -->
    
    <form id="paymentForm" method="get" action="/userDashboard">
        <button type="submit">Back to Dashboard</button>
    </form>

    </body>
    <div class="message-container">
    <p>${message}</p>
	</div>
</html>
    