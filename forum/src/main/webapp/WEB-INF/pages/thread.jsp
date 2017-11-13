<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <title>Registration</title>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("login") == null) {
        %>
        <h2> Чтобы создать тред, пройдите регистрацию. </h2>
        <%
            } else {
        %>
        <a href="/${tag}/create"><span>Создать тред</span></a>
        <%
            }
        %>

    </body>
</html>