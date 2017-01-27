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
	<fmt:message key="index.mensaje"/>  <fmt:message key="index.nombreApp"/>
	<form action="<%=Constantes.SERVLET_LOGIN %>" method="post">
	<input type="text" name="<%=Constantes.PAR_USUARIO %>" id=name="<%=Constantes.PAR_USUARIO %>" placeholder="<fmt:message key="index.login.username"/>" />
	<input type="text" name="<%=Constantes.PAR_PASSWORD %>" id="<%=Constantes.PAR_PASSWORD %>" placeholder="<fmt:message key="index.login.password"/>" />
	<label for="<%=Constantes.PAR_IDIOMA%>" ><fmt:message key="index.login.idioma"/></label>
	<select name="<%=Constantes.PAR_IDIOMA%>"  id="<%=Constantes.PAR_IDIOMA%>" >
		<option value="<%=Constantes.IDIOMA_CASTELLANO%>"><fmt:message key="idioma.castellano"/></option>
		<option value="<%=Constantes.IDIOMA_EUSKERA%>"><fmt:message key="idioma.euskera"/></option>
		<option value="<%=Constantes.IDIOMA_INGLES%>"><fmt:message key="idioma.ingles"/></option>
	</select>
	<input type="submit" value="<fmt:message key="index.login.enviar"/>"></input>
	</form>


</main>
<%@ include file="includes/footer.html" %>
</body>
</html>