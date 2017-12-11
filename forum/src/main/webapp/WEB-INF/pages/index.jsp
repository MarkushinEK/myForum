<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <title>Forum</title>
        <style>
           <%@include file='css/bootstrap.css' %>
           <%@include file='css/mystyle.css' %>
        </style>
    </head>
    <body>
        <header class="header">
            <center>
                <div><h1>Гра.ч</h1></div>
                <p>Добро пожаловать. Опять.</p>
            </center>
        </header>
        <center>
            <h2> ${message} </h2>
        </center>
        <%
            if (request.getSession().getAttribute("login") == null) {
        %>
        <center>
            <form action="/signin" method="POST">
                Логин: <input type="text" name="login"/>
                Пароль: <input type="password" name="pass"/>
                <input type="submit" value="Войти">
            </form>
            <a href="/registration"><span>Регистрация</span></a>
        </center>

        <%
            } else {
        %>
        <center>
            <form action="/exit" method="GET">
                Логин: ${login}
                <input type="submit" value="Выйти">
            </form>
            <a href="/profile/${login}"><span>Профиль</span></a>
        </center>
        <%
            }
        %>
        <div class="table">
            <h2>&nbsp;Тематика: </h2>
            <div><a href="/au"><span>&nbsp;Автомобили</span></a></div>
            <div><a href="/bi"><span>&nbsp;Велосипеды</span></a></div>
            <div><a href="/biz"><span>&nbsp;Бизнес</span></a></div>
            <div><a href="/dg"><span>&nbsp;Другие страны</span></a></div>
            <div><a href="/hi"><span>&nbsp;История</span></a></div>
        <div>
    </body>
</html>