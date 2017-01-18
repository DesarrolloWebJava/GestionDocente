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
		List<Alumno> alumnos = (List<Alumno>)request.getAttribute("listado-alumnos");
		//Recorremos la lista
		for(Alumno alumno: alumnos){
			//mostrar en la web
			out.println(alumno.toString());
		}
		%>
	</main>
</body>
</html>