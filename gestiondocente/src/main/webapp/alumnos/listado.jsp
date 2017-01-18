<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Docente - Listas Alumnos</title>
</head>
<body>
	<header><h1>Página Listado de Alumnos</h1></header>
	<main>
	<% /* Esto es un scriplet */
	// recogemos el atributo de la request
	List<Alumno> alumnos = (List<Alumno>)request.getAttribute("listado-alumnos");
	for(Alumno alumno: alumnos){ //for each para recorrer la lista
	// no sera un syso. syso es para sacar por consolo y esto es un html
		out.println(alumno.toString());
	}
	%>
</body>
</html>