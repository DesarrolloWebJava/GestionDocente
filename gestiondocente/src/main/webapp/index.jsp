<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 
<jsp:include page="includes/header.jsp"/>
<main>
	<fmt:message key="index.mensaje"/> <fmt:message key="index.nombreapp"/>

	<c:set var="name" value="${not empty cookie.username ? cookie.username.value: '' }"/>
	<c:set var="pass" value="${not empty cookie.password ? cookie.password.value: '' }"/>
	<c:set var="lang" value="${not empty cookie.language ? cookie.language.value: '' }"/>
	
	<form action="<%=Constantes.SERVLET_LOGIN%>" method="post">
		<input value="${name}" name="<%=Constantes.PAR_USUARIO%>" type="text" placeholder="<fmt:message key="index.login.username"/>" />	
		<input value="${pass}"name="<%=Constantes.PAR_PASSWORD%>" type="password" placeholder="<fmt:message key="index.login.password"/>" />	
		<label for="<%=Constantes.PAR_IDIOMA%>"><fmt:message key="index.login.idioma"/></label>
		<select name="<%=Constantes.PAR_IDIOMA%>" name="<%=Constantes.PAR_IDIOMA%>">
			<option ${2==lang ? 'selected': ''} value="<%=Constantes.IDIOMA_CASTELLANO%>" ><fmt:message key="idioma.castellano"/>
			<option ${1==lang ? 'selected': ''} value="<%=Constantes.IDIOMA_EUSKERA%>" ><fmt:message key="idioma.euskera" />
			<option ${3==lang ? 'selected': ''} value="<%=Constantes.IDIOMA_INGLES%>"><fmt:message key="idioma.ingles"/>
		</select>
		<input type="checkbox" value="si" name="recuerdame" id="recuerdame"> <label for="recuerdame">Recuérdame</label>
		<input  type="submit"  value="<fmt:message key="index.login.enviar"/>">
	</form>

</main>
<%@ include file="includes/footer.html" %>
</body>
</html>