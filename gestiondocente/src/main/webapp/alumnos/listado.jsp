<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"> 
<script src="js/bootstrap.min.js"></script>
<title>Gestion Docente</title>
</head>
<body>
<header>
	
	
</header>
<div class="container">
	<h1>Página Listado Alumnos</h1>
		<%
			/*esto es un criplet*/
			//recogemos el atributo de la request y lo gardamos en un objeto lista
			//hay q hacer un casting de request y decirle q es tipo list
			List<Alumno> alumnos =(List<Alumno>) request.getAttribute("listado-alunmos"); 
			//sacar la lista por pantalla	
		%>
	<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE %>" class="btn btn-primary btn-lg active" role="button"> Crear Alumno </a>
	
	<div class="row">
		<% 
			for(Alumno alumno : alumnos){
				out.println("<p>"+alumno.toString()+"</p>");	
			}
		
		%>
	</div>
</div>
</body>
</html>