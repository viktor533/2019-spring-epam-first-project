<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/common.css'/>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/login.css'/>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #9C57FF;
        }
    </style>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="centralCard">
            <div class="container">
                <h1>Login</h1>
                <p>Please fill in this form to login an account.</p>
                <hr>
                <label><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required></input>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required></input>

                <label class="badMessage"><b>${message}</b></label>

                <button type="submit" class="loginButton">Login</button>
            </div>

            <div class="container signin">
                <p>Do not have an account? <a href="${pageContext.request.contextPath}/registration">Sign up</a>.</p>
            </div>
        </div>
    </form>
</body>
</html>

