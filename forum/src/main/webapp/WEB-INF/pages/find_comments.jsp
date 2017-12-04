<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <title>Comments</title>
    </head>
    <body>
        <c:forEach items="${comments}" var="comments">
            <div>
                <span>
                    <a href="/${comments.tread.tag}/${comments.tread.id}"><span>К сообщению</span></a>
                </span>
                <span>
                	<span>${comments.tread.dateOfCreate}</span>
                </span>
            </div>
            <blockquote style="overflow: auto;">
                ${comments.message}
            </blockquote>
            <br>
            <hr>
        </c:forEach>
    </body>
</html>