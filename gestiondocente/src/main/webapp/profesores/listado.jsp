<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Docente - Listado Profesores</title>
<img src="images/ipartek.png"/>
</head>
<body>
	<header><h1>Página Listado de Profesores</h1></header>
	<main>
	<%
	/* Esto es un scriplet*/
	
	//recogemos el atributo de la request
	
	Map<Integer, Profesor> profesores=(Map <Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	
	for(Map.Entry<Integer, Profesor> entry: profesores.entrySet()){
		int codigo = entry.getKey();
		Profesor profesor = entry.getValue();
		out.print("<li>"+profesor.toString()+"</li>");
		out.write("<p>"+entry.getValue().toString()+"</p>");
	}
	%>
	</main>
	<form>
		<div align="center">
  		<input type="button" value="VOLVER ATRÁS" name="Back2" onclick="history.back()" />
  		</div>
 	</form>
</body>
</html>