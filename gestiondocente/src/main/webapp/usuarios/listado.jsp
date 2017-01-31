<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
<main>
	<header><h2>Usuarios Conectados</h2> </header>
	<!-- Recorrer la lista de personas (usuarios conectados) -->
	<%
	List<Persona> personas =(List<Persona>) request.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
	for(Persona persona: personas){
	%>
	
	<%=persona.getNombre() %>
	
	<%} %>
	<!--  Plus: imple -->
</main>

</html>