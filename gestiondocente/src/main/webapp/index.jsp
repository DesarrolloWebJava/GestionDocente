<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" />

<jsp:include page="includes/header.jsp" />
<main>
	
	<div class="myproperties">
		<p><fmt:message key="index.mensaje"/></p>
		<p><fmt:message key="index.nombreApp"/></p>
	</div>
	
<!--solo puede haber un main;  marca que no es un contenido estático -->
<!-- Bienvenidos a Gestión Alumnos -->
</main>
<%@  include file="includes/footer.html" %>
</body>
</html>