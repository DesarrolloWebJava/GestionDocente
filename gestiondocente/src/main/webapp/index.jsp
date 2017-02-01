<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 

<jsp:include page="includes/header.jsp"/>
<main>

<fmt:message key="index.mensaje"/> <fmt:message key="index.nombreApp"/>
<%-- <c:choose>
<c:when test="${not empty cookie.username}">
<c:set var="name" value="${cookie.username }"></c:set>
</c:when>
<c:otherwise>
<c:set var="name" value="''"></c:set>
</c:otherwise>
</c:choose> --%>
<c:set var="nombre" value="${not empty cookie.username ? cookie.username.value:'' }"/>
<c:set var="pass" value="${not empty cookie.password ? cookie.password.value:'' }"/>
<c:set var="lang" value="${not empty cookie.lang ? cookie.lang.value:'' }"/>


<form action="<%=Constantes.SERVLET_LOGIN %>" method="post">
<input type="text" placeholder="<fmt:message key="index.login.username"/>" name="<%=Constantes.PAR_USUARIO%>" value="${nombre}"/>
<input type="password" placeholder="<fmt:message key="index.login.password"/>" name="<%=Constantes.PAR_PASSWORD%>" value="${pass}"/>
<label for="<%=Constantes.PAR_IDIOMA %>"></label>
<select id="<%=Constantes.PAR_IDIOMA %>" name="<%=Constantes.PAR_IDIOMA %>" >

<option ${ 2==lang? 'selected':''} value="<%=Constantes.IDIOMA_ES %>"><fmt:message key="idioma.es"></fmt:message></option>
<option ${ 1==lang? 'selected':''} value="<%=Constantes.IDIOMA_EU %>"><fmt:message key="idioma.eu"></fmt:message></option>
<option ${ 3==lang? 'selected':''} value="<%=Constantes.IDIOMA_EN %>"><fmt:message key="idioma.en"></fmt:message></option>
</select>
<input type="checkbox" name="recuerdame" id="recuerdame"><label for="recuerdame">Recuérdame</label>
<input type="submit" value="<fmt:message key="index.login.enviar"></fmt:message>"/>
</form>
</main>
<%@ include file="includes/footer.html" %>
</body>
</html>