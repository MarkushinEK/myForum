<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <title>${login}</title>
    </head>
    <body>
        <p>Логин: ${login}</p>
        <p>Email: ${email}</p>
        <img src="http://localhost:8080/image/${imageName}" width="100" height="125" align="left" vspace="5" hspace="5"/>
        <p><a href="/profile/${login}/find/messages"><span>Найти сообщения</span></a></p>
        <p><a href="/profile/${login}/find/threads"><span>Найти темы</span></a></p>
        <div style="clear: left"></div>
    </body>
</html>