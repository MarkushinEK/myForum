<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <%@ page contentType="text/html;charset=utf-8" %>
    <title>Registration</title>
</head>
<body>

<form action="/registration" method="POST">
    Логин: <input type="text" name="login"/>
    Пароль: <input type="password" name="pass"/>
    <input type="submit" value="Регистрация">
</form>
<p>${message}</p>
</body>
</html>