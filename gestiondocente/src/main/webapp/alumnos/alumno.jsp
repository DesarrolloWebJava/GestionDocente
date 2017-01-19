<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"> 
<script src="js/bootstrap.min.js"></script>
<title>Gestion Docente - Crear Alumno</title>
</head>
<body>
<div  class="container">
<h1>Crear Alumno</h1>

	<form role="form" action="<%=Constantes.SERVLET_ALUMNO%>" method="post">
		<div class="form-group">
	    <input type="hidden" class="form-control" id="<%=Constantes.PAR_CODIGO %>"  name="<%=Constantes.PAR_CODIGO %>" value="-1">
	  </div>
		<div class="form-group">
	    <label for="<%=Constantes.PAR_NOMBRE %>">Nombre</label>
	    <input type="text" class="form-control" id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE %>"
	           placeholder="Introduce tu nombre">
	  </div>
	  <div class="form-group">
	    <label for="<%=Constantes.PAR_APELLIDO %>">Apellido</label>
	    <input type="text" class="form-control" id="<%=Constantes.PAR_APELLIDO %>" name="<%=Constantes.PAR_APELLIDO %>"
	           placeholder="Introduce tu apellido">
	  </div>
	  
	  <div class="form-group">
	    <label for="<%=Constantes.PAR_DNI %>">DNI</label>
	    <input type="text" class="form-control" id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI %>" 
	    	placeholder="Introduce tu DNI">
	  </div>
	  <div class="form-group">
	    <label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de Nacimento</label>
	    <input type="text" class="form-control" id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO  %>" 
	    	placeholder="Introduce tu fecha de nacimiento">
	  </div>
	  <div class="form-group">
	   <label for="<%=Constantes.PAR_DIRECCION %>">Dirección</label>
	   <input type="text" class="form-control" id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION  %>" 
	  	placeholder="Introduce tu dirección">
	  </div>
	    <div class="form-group">
	   <label for="<%=Constantes.PAR_EMAIL%>">Email</label>
	   <input type="text" class="form-control" id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL  %>" 
	  	placeholder="Introduce tu email">
	  </div>
	    <div class="form-group">
	   <label for="<%=Constantes.PAR_NHERMANOS%>">Nº de hermanos</label>
	   <input type="number" class="form-control" id="<%=Constantes.PAR_NHERMANOS %>" name="<%=Constantes.PAR_NHERMANOS  %>" 
	  	placeholder="Introduce tu nº de hermanos">
	  </div>
	   <div class="form-group">
	   <label for="<%=Constantes.PAR_ACTIVO%>">Activo</label>
	   <select class="form-control" name"<%=Constantes.PAR_ACTIVO  %>" id="<%=Constantes.PAR_ACTIVO %>" >
 		 <option value="1">Activo</option>
 		 <option value="0">Desactivado</option>
		</select>
	  </div>
	  
	  <button type="submit" class="btn btn-primary btn-lg active">Crear Alumno</button>
	</form>
</div>
</body>
</html>