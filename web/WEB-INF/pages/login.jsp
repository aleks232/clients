<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir Ugay
  Date: 13.09.13
  Time: 19:31
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<fm:form action="login" commandName="user">
    <table>
        <tr>
            <td>LOGIN</td>
            <td><fm:input path="login"/></td>
        </tr>
        <tr>
            <td>PASSWORD</td>
            <td><fm:input path="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Войти"></td>
        </tr>
    </table>
</fm:form>
</body>
</html>