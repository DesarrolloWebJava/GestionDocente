<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.time.chrono.ChronoPeriod"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages"/>
<jsp:include page="../includes/header.jsp" />

<main>
		
			<h2>Lista de Usuarios</h2>
			<ul>
			<c:forEach items="${requestScope.listadoUsuarios}" var="persona">
			
				<div><li>${persona.nombre} <a href="<%=Constantes.SERVLET_USUARIO %>?sessionid=${persona.id}" >Expulsar</a> </li></div>
			</c:forEach>
			</ul>
			
</main>

		<%@ include file="../includes/footer.html" %>
	</body>
</html>