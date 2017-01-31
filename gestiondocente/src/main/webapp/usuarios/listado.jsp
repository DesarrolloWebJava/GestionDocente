<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<main>
<header><h2>Usuarios conectados</h2></header>
	<!-- Recorrer la lista de personas(usuarios conectados) -->
	<%
	List<Persona>personas = (List<Persona>)request.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
	for(Persona persona: personas){
	%>
	<%=persona.getNombre() %> <%=persona.getSessionId() %><a href="<%=Constantes.SERVLET_ADMIN%>?sessionId=<%=persona.getSessionId()%>">Kick</a>
	<!-- Plus: añada boton de  kick (expulsar) a un usuario conectado-->
	
<% }	%>
</main>

<%@ include file="../includes/footer.html" %>
</body>
</html>