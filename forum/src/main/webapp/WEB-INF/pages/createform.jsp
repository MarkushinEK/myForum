<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8"/>
        <%@ page contentType="text/html;charset=utf-8" %>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <title>New</title>
    </head>
    <body>
        <center>
            <form id="postform" class="postform tmp_postform" action="/${tag}/create" method="POST">
                <table>
                    <tbody>
                        <tr class="post-subject">
                            <td>
                                <input type="text" maxlength="150" id="subject" name="subject" placeholder="тема">
                            </td>
                        </tr>
                        <tr class="comment-area">
                            <td class="symbol-counter">
                                <textarea name="comment" id="shampoo" rows="10" placeholder="Сообщение. Макс. длина 15000" style="margin: 0px; width: 400px; height: 200px;"></textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="g-recaptcha" data-sitekey="6LdxGzsUAAAAAHnMD8Bbv38zTuiKPcu2_dvs_0Xe"></div>
                <input type="submit" id="submit" name="submit" value="Создать">
                <H2>${errorMessage}</H2>
            </form>
        </center>
    </body>
</html>