<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <title>${tread.id}</title>
    </head>
    <body>
        <div>
            <span>
            	${tread.subject}
            </span>
        	<span>${tread.user.login}</span>
        	<span>
        		<span>${tread.dateOfCreate}</span>
        	</span>
        </div>
        <blockquote>
        		${tread.mainComment}
        </blockquote>

        <c:forEach items="${comments}" var="comments">
            <div>
            	<span>${comments.user.login}</span>
            	<span>
            		<span>${comments.dateOfCreateMessage}</span>
            	</span>
            </div>
            <blockquote>
            		${comments.message}
            </blockquote>
        </c:forEach>

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
                                <span class="message-sticker-btn" style="position: absolute;bottom: 3px;left: 3px;opacity: 0.6;cursor: pointer;">[S]</span>
                                <div class="message-sticker-preview"></div>
                                <textarea name="message" id="shampoo" rows="10" placeholder="Сообщение. Макс. длина 15000" style="margin: 0px; width: 400px; height: 200px;"></textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input type="submit" id="submit" name="submit" value="Отправить комментарий">
            </form>
        </center>
        <%
            }
        %>
    </body>
</html>