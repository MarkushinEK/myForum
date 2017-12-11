<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <title>${tag}</title>
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
        <center>
            <h1>${tag_transcript}</h1>
        </center>
        <%
            if (request.getSession().getAttribute("login") == null) {
        %>
        <h2>&nbspЧтобы создать тред, пройдите регистрацию. </h2>
        <%
            } else {
        %>
        <a href="/${tag}/create"><span>Создать тред</span></a>
        <%
            }
        %>
        <c:forEach items="${treads}" var="treads">
            <hr>
            <div class="maincomment">
                <div>
                    <span>
                    	<H3>&nbsp;${treads.subject}</H3>
                    </span>
                </div>
                <div>
                    <span>
                        <a href="/profile/${treads.user.login}"><span>&nbsp;${treads.user.login}</span></a>
                    </span>
                    <span>
                    	<span>${treads.dateOfCreate}</span>
                    </span>
                    <span> [<a href="/${tag}/${treads.id}">Ответ</a>]</span>
                </div>
                <img src="http://localhost:8080/image/${treads.user.imageProfileUser.id}.${treads.user.imageProfileUser.extension}" width="100" height="125" align="left" vspace="5" hspace="5"/>
                <blockquote style="overflow: auto;">
                    ${treads.mainComment}
                </blockquote>
                <div style="clear: left"></div>
            </div>
            <br>
        </c:forEach>
        <hr>
    </body>
</html>