<!DOCTYPE html>
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <title>${login}</title>
    </head>
    <body>
        <img src="http://localhost:8080/image/${imageName}"/>
        <form action="http://localhost:8080/image/add" method="post" enctype="multipart/form-data">
        	<input name="data" type="file"><br>
        	<input type="submit"><br>
        </form>
        <p>Логин: ${login}</p>
    </body>
</html>