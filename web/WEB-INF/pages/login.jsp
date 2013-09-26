<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
.login {
    padding:150px 0 0 0;
    margin:0 auto;
    width:265px;
}

</style>
</head>
<body onload='document.f.j_username.focus();'>
	<c:if test="${not empty error}">
		<div class="errorblock">
			<s:message code="login.error"/>
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
    <div class="login">

    <form name='f' action="<c:url value='/j_spring_security_check' />"
		method='POST'>

		<table>
			<tr>
				<td><s:message code="login.user"/>:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td><s:message code="login.password"/>:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr align="right">
				<td colspan='2'>
                    <input name="submit" type="submit" value="<s:message code='login.enter' />" />
                    <input name="reset" type="reset" value="<s:message code='login.reset' />" />
				</td>
			</tr>
		</table>
	</form>
        </div>
</body>
</html>