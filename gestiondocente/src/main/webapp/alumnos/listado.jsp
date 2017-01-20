<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
		<%
		/*Esto es un Scriptlet*/
		/*
		 * Recogemos el atributo de la request
		 * Hacemos el casting porque es un objeto que queremos meter en una lista
		 */
		List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
		//Vamos a hacer el resto de operaciones de CRUD
		%>
		<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Alumno</a>
		<%
		//Recorremos la lista
		for(Alumno alumno: alumnos){
			//mostrar en la web
			out.println("<p>"+alumno.toString()+"</p>");
		}
		%>
	</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>