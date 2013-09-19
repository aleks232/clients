<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/main.css" type="text/css"/>
</head>
<body>
<div align="center">
    <div class="vmiddle" style="height:500px; ">
        <c:if test="${message!=null}">
            <h3 style="color: red"><c:out value="${message}"/></h3>
        </c:if>
        <fm:form action="login" commandName="user">
            <table>
                <tr>
                    <td>Логин</td>
                    <td><fm:input path="login"/></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><fm:input path="password"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Войти"></td>
                </tr>
            </table>
        </fm:form>
    </div>
</div>
</body>
</html>