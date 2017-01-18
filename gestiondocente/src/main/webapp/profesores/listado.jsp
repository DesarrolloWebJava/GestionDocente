<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Listado Profesores</title>
</head>
<body>
	<h1>Pagina listado de profesores</h1>
	<main>
		<%
		/*Esto es un Scriptlet*/
		/*
		 * Recogemos el atributo de la request
		 * Hacemos el casting porque es un objeto que queremos meter en una lista
		 */
		 Map<Integer,Profesor> profesores = (Map<Integer,Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
		//Recorremos la lista con un Entry porque es un Map
		for (Map.Entry<Integer,Profesor> entry: profesores.entrySet()){
			//mostrar en la web
			out.println("<p>"+entry.getValue().toString()+"</p>");
		}
		%>
	</main>
</body>
</html>