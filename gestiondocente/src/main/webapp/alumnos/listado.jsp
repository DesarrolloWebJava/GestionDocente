<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Listado Alumnos</title>
</head>
<body>
	<h1>Pagina listado de alumnos</h1>
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
</body>
</html>