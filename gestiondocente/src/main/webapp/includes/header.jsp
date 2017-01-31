<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestor Docente - Pág Inicio</title>
<link href="<c:url value="/css/styles.css"/>" type="text/css" rel="stylesheet"></link>
<script src="<c:url value="/js/codigo.js" />" ></script>


</head>
<body>
<header>

<h1>Gestor Docente</h1>
<nav>
<ul>

<li><a href="<%= Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>" >G. Alumnos</a></li>
<li><a href="<%= Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_READ %>" >G. Profesores</a></li>
<li><a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Cursos</a></li>
</ul>
</nav>
<%-- <a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_ES %>">ES</a>
<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_EU %>">EU</a>
<a href="<%=Constantes.SERVLET_IDIOMA %>?<%=Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_EN %>">EN</a> --%>
<%
if(session!=null&&!session.isNew()&&session.getAttribute(Constantes.SESSION_IDIOMA)!=null){

%>
<ul>
<li><a href="<%=Constantes.SERVLET_USUARIO %>">Ver lista de Usuarios</a></li>
<li><a href="<%=Constantes.SERVLET_LOGIN %>">Cerrar Sesión </a></li>
</ul>

<%
}
%>
<jsp:include page="mensajes.jsp"/>
</header>