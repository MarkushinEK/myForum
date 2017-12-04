<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <title>Threads</title>
    </head>
    <body>
        <c:forEach items="${treads}" var="treads">
            <div>
                <span>
                    <a href="/${treads.tag}/${treads.id}"><span>${treads.subject}</span></a>
                </span>
                <span>
                	<span>${treads.dateOfCreate}</span>
                </span>
            </div>
            <blockquote style="overflow: auto;">
                ${treads.mainComment}
            </blockquote>
            <br>
            <hr>
        </c:forEach>
    </body>
</html>