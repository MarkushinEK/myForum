<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        <c:forEach items="${treads}" var="treads">
            <div>
                <span>
                	${treads.subject}
                </span>
            	<span>${treads.user.login}</span>
            	<span>
            		<span>${treads.dateOfCreate}</span>
            	</span>
            	<span> [<a href="/au/res/4216051.html">Ответ</a>]</span>
            </div>
            <blockquote>
            		${treads.comment}
            </blockquote>
        </c:forEach>

    </body>
</html>