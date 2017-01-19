<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión Docente - Listado Alumnos</title>
<img src="images/ipartek.png"/>
</head>
<body>
	<header>
		<h1 style="color: blue;""font-size: 15">Página Listado de Alumnos</h1>
	</header>
	<main>
	<%
	/* Esto es un scriplet*/
	
	//recogemos el atributo de la request
	
	List<Alumno> alumnos=(List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	
	//CRUD
	%>
	<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Alumno</a>
	<% 
	for(Alumno alumno: alumnos){
		out.print("<li>"+alumno.toString()+"</li>");
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