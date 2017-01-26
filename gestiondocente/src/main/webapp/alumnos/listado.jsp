<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"/>

	<div class="container">
		<h1>Página Listado Alumnos</h1>

		<br>
		<br>
		<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE %>" class="btn btn-primary btn-lg active" role="button"> Crear Alumno </a>
		<br>
		<br>
		<div class="row">
	    	<table class="table">
		    	<tbody>
		    	<c:forEach items="${listadoAlumno}" var="alumno">
			    	  <tr>
						<td>${alumno.nombre}</td>
						<td>${alumno.apellidos}</td>
						<td>${alumno.dni}<td>
						<td>
							<a href="${Constantes.SERVLET_ALUMNO}?op=<c:out value="${1}"/>&codigo=<c:out value="${alumno.codigo}" />" class='btn btn-warning btn-xs' role='button' >UPDATE</a>
							<a href="${Constantes.SERVLET_ALUMNO}?op=<c:out value="${4}"/>&codigo=<c:out value="${alumno.codigo}" />" class='btn btn-danger btn-xs' role='button' >BORRAR</a>
						</td> 
					 </tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

<%@ include file="../includes/footer.html" %>
</body>
</html>