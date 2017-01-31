<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
	<main>
	<%
	/*Esto es un scriplet */
	
	//recogemos el atributo de la request
	List<Persona> personas = (List<Persona>)request.getAttribute(Constantes.JSP_LISTADO_USUARIO_CONECTADOS);
	
	

	 %>
	 
	 
	<h1> MISMO MENÚ con JSTL</h1>
	
	<c:forEach items="${listadoUsuarios}" var="persona">
    	<div> ${persona.nombre} </div>
    	<div> ${persona.codigo} </div>
	</c:forEach>
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>