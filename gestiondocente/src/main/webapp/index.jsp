<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmesages" /> 
<jsp:include page="includes/header.jsp"/>
<main> 
	<fmt:message key="index.mensaje"/> <fmt:message key="index.nombreApp"/>
	
	<!-- USERNAME  -->
	<!--  ----------Estructura IF ELSE en JSP ------------ -->
	<c:choose>
		<c:when test="${not empty cookie.username }">
			<c:set var="name" value="${cookie.username.value}"/>
		</c:when>
		<c:otherwise>
			<c:set var="name" value=""/>
		</c:otherwise>
	</c:choose>
	
	<c:set var"pass" value="${not empty cookie.cusername ? cookie.cusername.value : '' }"/>
	
	<!-- PASSWORD  -->
	<!--  ----------Estructura IF ELSE en JSP ------------ -->
	<c:choose>
		<c:when test="${not empty cookie.password }">
			<c:set var="pass" value="${cookie.password.value}"/>
		</c:when>
		<c:otherwise>
			<c:set var="pass" value=""/>
		</c:otherwise>
	</c:choose>	
	
	<c:set var"pass" value="${not empty cookie.cpassword ? cookie.cpassword.value : '' }"/>
	
	<!-- IDIOMA  -->

	
	<form action="<%=Constantes.SERVLET_LOGIN %>" method="post">
		<input value="${name}" name="<%=Constantes.PAR_USUARIO %>" type="text" placeholder="<fmt:message key="index.login.username"/>" />
		<input value="${pass}" name="<%=Constantes.PAR_PASSWORD %>" type="password" placeholder="<fmt:message key="index.login.password"/>" />
		<label for="<%=Constantes.PAR_IDIOMA %>"> <fmt:message key="index.login.idioma"/></label>
		<select id="<%=Constantes.PAR_IDIOMA %>" name="<%=Constantes.PAR_IDIOMA %>">
			<option value="<%=Constantes.IDIOMA_CASTELLANO %>"><fmt:message key="idioma.castellano"/></option>
			<option value="<%=Constantes.IDIOMA_EUSKERA %>"><fmt:message key="idioma.euskera"/></option>
			<option value="<%=Constantes.IDIOMA_INGLES %>"><fmt:message key="idioma.ingles"/></option>		
		</select>	
		<input type="checkbox" value="si" name="recuerdame" id="recuerdame"><label for="recuerdame">Recuérdame</label>
		
		<input type="submit" value="<fmt:message key="index.login.enviar"/>"/>
	</form>
</main>
	<%@ include file="includes/footer.html" %>
</body>
</html>