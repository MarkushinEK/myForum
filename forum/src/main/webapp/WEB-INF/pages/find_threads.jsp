<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <title>Threads</title>
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
        <c:forEach items="${treads}" var="treads">
            <div class="userthreads">
                <div>
                    <span>
                        <a href="/${treads.tag}/${treads.id}"><span>&nbsp;${treads.subject}</span></a>
                    </span>
                    <span>
                    	<span>${treads.dateOfCreate}</span>
                    </span>
                </div>
                <blockquote style="overflow: auto;">
                    ${treads.mainComment}
                </blockquote>
            </div>
            <hr>
        </c:forEach>
    </body>
</html>