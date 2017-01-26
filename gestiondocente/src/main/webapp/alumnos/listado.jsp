<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<jsp:include page="../includes/header.jsp"/>
	
	<main>
	<ul>
	<%
	/*Esto es un scriplet*/
	//Recogemos el atributo de la request
	//List<Alumno>alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	%>
	<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Alumno</a>
	
	<c:forEach items="${listadoAlumnos}" var="alumno">
	<div>${alumno}</div>
	
	</c:forEach>

	</ul>
	</main>
	<%@include file="../includes/footer.html" %>
	
</body>
</html>