<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladimir Ugay
  Date: 13.09.13
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/jquery-ui-1.10.3.custom.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/jquery.formstyler.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.0.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.formstyler.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            pageInit();
        });
    </script>
</head>

<body>

<div id="dialog" title="Добавить нового пользователя">
    <fm:form action="create" commandName="criteria" id="createClient" method="POST">
        <table>
            <tr>
                <td colspan="3"><label><input type="radio" name="type" value="IP" checked>Физические лица</label></td>
                <td colspan="3"><label><input type="radio" name="type" value="UL">Юридические лица</label></td>
            </tr>
            <tr id="trIP">
                <td>Имя</td>
                <td><fm:input path="name"/></td>
                <td><fm:errors path="name"/></td>
                <td>Фамилия</td>
                <td><fm:input path="surname"/></td>
                <td>Номер паспорта</td>
                <td><fm:input path="passportNumber"/></td>
            </tr>
            <tr id="trUL" style="display: none;">
                <td>ИНН</td>
                <td><fm:input path="inn"/></td>
                <td>Название оргинизации</td>
                <td><fm:input path="orgName"/></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </fm:form>
</div>
<div id="dialogFind" title="Найти пользователя">
    <fm:form action="find" commandName="criteria" id="findClient" method="POST">
        <table>
            <tr>
                <td>ID</td>
                <td><fm:input path="clientId"/></td>
                <td>Дата создания</td>
                <td>от: <fm:input path="dateStart" cssClass="dateBlock" readonly="true"/></td>
                <td>до: <fm:input path="dateEnd" cssClass="dateBlock" readonly="true"/></td>
            </tr>
        </table>
    </fm:form>
</div>
<table>
    <tr valign="top">
        <td><button id="opener" class="styler">Добавить</button></td>
        <td><button id="finder" class="styler">Найти</button></td>
        <td><button class="styler" onclick="window.location='<c:url value="/forms/listClients"/>'">Все клиенты</button></td>
        <td><button class="styler" onclick="window.location='<c:url value="/j_spring_security_logout" />'">Выход</button></td>
    </tr>
</table>



<table border="1" class="ui-widget">
    <thead class="ui-widget-header">
    <tr align="center">
        <th>ID</th>
        <th>Дата создания</th>
        <th>Статус</th>
        <%--<th>IP</th>--%>
        <%--<th>UL</th>--%>
        <th>---</th>
    </tr>
    </thead>
    <tbody class="ui-widget-content">

    <c:forEach items="${clients}" var="client">
        <tr>
            <td><c:out value="${client.id}"/></td>
            <td><c:out value="${client.date_created}"/></td>
            <td><c:out value="${client.status}"/></td>
                <%--<td><c:out value="${client.clientIps}"/></td>--%>
                <%--<td><c:out value="${client.clientUls}"/></td>--%>
            <c:set var="ipList" value="${client.clientIps}"/>
            <c:set var="ulList" value="${client.clientUls}"/>
            <td>
                <c:choose>
                    <c:when test="${fn:length(ipList)>0}">
                        <table border="1" width="100%">
                            <thead align="center">Физическое лицо</thead>
                            <tbody>
                            <tr align="center">
                                <td>Имя</td>
                                <td>Фамилия</td>
                                <td>Номер паспорта</td>
                            </tr>
                            <tr align="left">
                                <c:forEach items="${ipList}" var="entry">
                                    <td>${entry.name}</td>
                                    <td>${entry.surname}</td>
                                    <td>${entry.passport_number}</td>
                                </c:forEach>
                            </tr>
                            </tbody>
                        </table>
                    </c:when>
                    <c:when test="${fn:length(ulList)>0}">
                        <table border="1" width="100%">
                            <thead align="center">Юридическое лицо</thead>
                            <tbody>
                            <tr align="center">
                                <td>ИНН</td>
                                <td>Название оргинизации</td>
                            </tr>
                            <tr align="left">
                                <c:forEach items="${ulList}" var="entry">
                                    <td><c:out value="${entry.inn}"/></td>
                                    <td><c:out value="${entry.org_name}"/></td>
                                </c:forEach>
                            </tr>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        ---
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>