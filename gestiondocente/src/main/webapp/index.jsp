<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="language" value="${'en_EN'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controler.i18nmesages" /> 

<jsp:include page="includes/header.jsp"/>

<main>
	<div class="container">
		<h1><fmt:message key="index.mensaje" /></h1>
		<br>
		<h2><fmt:message key="index.nombreApp" /></h2>
	</div>
</main>

<%@ include file="includes/footer.html" %>
</body>
</html>