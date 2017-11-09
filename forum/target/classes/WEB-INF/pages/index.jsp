<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <title>Forum</title>
    </head>
    <body>
        <header class="header">
            <center>
                <h1>Гра.ч</h1>
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
        </center>
        <%
            }
        %>
        <h2> Тематика: </h2>
        <a href="/au"><span>Автомобили</span></a>
        <a href="/bi"><span>Велосипеды</span></a>
        <a href="/biz"><span>Бизнес</span></a>
        <a href="/dg"><span>Другие страны</span></a>
        <a href="/hi"><span>История</span></a>
    </body>
</html>