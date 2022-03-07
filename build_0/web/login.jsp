<%-- 
    Document   : login.jsp
    Created on : Jun 17, 2021, 8:35:58 PM
    Author     : CND
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div>Login</div>
        <form action="MainServlet" method="post">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" value="" /> </td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="pwd" value="" /> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnAction" value="login" /> </td>
                    <td><input type="reset" value="Reset" /> </td>
                </tr>
            </table>
            <font color="red">${requestScope.msgLoginFail}</font>
        </form>
    </body>
</html>
