<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"> 
<script src="js/bootstrap.min.js"></script>
<title>Gestión Docente-Listado Profesores</title>
</head>
<body>
	<header>
		<h1>Listado Profesores</h1>
	</header>
	<main>
		<%
			/*esto es un criplet*/
			//recogemos el atributo de la request y lo gardamos en un objeto lista
			//hay q hacer un casting de request y decirle q es tipo list
			Map<Integer, Profesor > profesores =(Map<Integer, Profesor>) request.getAttribute(Constantes.ATT_LISTADO_PROFESORES); 
			//sacar la lista por pantalla	
		%>
		<ul>
		<% 
		for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
			 int codigo = entry.getKey();
			 Profesor profesor = entry.getValue();
			// out.println("<li>" + entry.getKey() + "  email=" + profesor.getEmail() + "</li>");
		    out.println("<li>"+ profesor.toString() +"</li>");
		  //  out.write(entry.getValue().)
		}
		
		%>
		</ul>
	</main>
</body>
</html>