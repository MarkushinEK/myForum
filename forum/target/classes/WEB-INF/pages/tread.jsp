<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <title>${tread.id}</title>
    </head>
    <body>
        <div>
            <span>
            	<H3>Тема: ${tread.subject}</H3>
            </span>
        </div>
        <div>
            <span>
                <a href="/profile/${tread.user.login}"><span>${tread.user.login}</span></a>
            </span>
            <span>
            	<span>${tread.dateOfCreate}</span>
            </span>
        </div>
        <img src="http://localhost:8080/image/${tread.user.imageProfileUser.id}.${tread.user.imageProfileUser.extension}" width="100" height="125" align="left" vspace="5" hspace="5"/>
        <blockquote style="overflow: auto;">
            <H2>${tread.mainComment}</H2>
        </blockquote>
        <div style="clear: left"></div>
        <br>

        <c:forEach items="${comments}" var="comments">
            <hr>
            <div>
                <span>
                    <a href="/profile/${comments.user.login}"><span>${comments.user.login}</span></a>
                </span>
                <span>
                    <span>${comments.dateOfCreateMessage}</span>
                </span>
            </div>
            <img src="http://localhost:8080/image/${comments.user.imageProfileUser.id}.${comments.user.imageProfileUser.extension}" width="100" height="125" align="left" vspace="5" hspace="5"/>
            <blockquote style="overflow: auto;">
            		${comments.message}
            </blockquote>
            <div style="clear: left"></div>
            <br>
        </c:forEach>
        <hr>

         <%
             if (request.getSession().getAttribute("login") == null) {
         %>
         <h2> Чтобы написать сообщение, пройдите регистрацию. </h2>
         <%
             } else {
         %>
        <center>
            <form id="postform" class="postform tmp_postform" action="/${tag}/${tread.id}" method="POST">
                <table>
                    <tbody>
                        <tr class="comment-area">
                            <td class="symbol-counter">
                                <textarea name="message" id="shampoo" rows="10" placeholder="Сообщение. Макс. длина 15000" style="margin: 0px; width: 400px; height: 200px;"></textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
                    <div class="g-recaptcha" data-sitekey="6LdxGzsUAAAAAHnMD8Bbv38zTuiKPcu2_dvs_0Xe"></div>
                <input type="submit" id="submit" name="submit" value="Отправить комментарий">
            </form>
            <H2>${errorMessage}</H2>
        </center>
        <%
            }
        %>
    </body>
</html>