<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <title>Registration</title>
        <style>
           <%@include file='css/bootstrap.css' %>
           <%@include file='css/mystyle.css' %>
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <a href="/"><span>&nbsp;Главная</span></a>
            </div>
        </div>
        <br>
        <form action="/registration" method="POST">
            <div> Логин </div>
            <div> <input type="text" name="login"/></div>
            <hr>
            <div> Пароль </div>
            <div> <input type="password" name="pass"/></div>
            <hr>
            <div> Почта </div>
            <div> <input type="text" name="email"/></div>
            <hr>
            <input type="submit" value="Регистрация">
        </form>
        <p>${message}</p>
    </body>
</html>