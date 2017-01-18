<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion Docente</title>
</head>
<body>
	<header>
		<h1>Página Listado Alumnos</h1>
	</header>
	<%
		/*esto es un criplet*/
		//recogemos el atributo de la request y lo gardamos en un objeto lista
		//hay q hacer un casting de request y decirle q es tipo list
		List<Alumno> alumnos =(List<Alumno>) request.getAttribute("listado-alunmos"); 
		//sacar la lista por pantalla	
	%>
	<ul>
	<% 
		for(Alumno alumno : alumnos){
			out.println("<li>"+alumno.toString()+"</li>");	
		}
	
	%>
	</ul>
</body>
</html>