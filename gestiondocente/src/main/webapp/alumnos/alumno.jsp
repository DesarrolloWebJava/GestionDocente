<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion docente- Create Alumno</title>
<img src="images/ipartek.png"/>
</head>
<body>
<br>
	<h1 style="color: blue;""font-size: 15">Crear Alumno</h1>

	<form action="<%=Constantes.SERVLET_ALUMNO %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" value="-1" id="<%=Constantes.PAR_CODIGO %>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre: </label><input type="text" placeholder="Introduzca el nombre.." name="<%=Constantes.PAR_NOMBRE %>"  id="<%=Constantes.PAR_NOMBRE %>">
		</div>	
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos: </label><input type="text" placeholder="Introduzca los apellidos.." name="<%=Constantes.PAR_APELLIDOS %>"  id="<%=Constantes.PAR_APELLIDOS %>">
		</div>	
		<div>	
			<label for="<%=Constantes.PAR_DNI %>">DNI: </label><input type="text" placeholder="Introduzca el dni.." name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>">
		</div>
		<div>	
			<label for="<%=Constantes.PAR_DIRECCION %>">Direccion: </label><input type="text" placeholder="Introduzca la direccion.." name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>">
		</div>
		<div>	
			<label for="<%=Constantes.PAR_EMAIL %>">Email: </label><input type="email" placeholder="Introduzca el email.." name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>">
		</div>
		<div>	
			<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha Nacimiento: </label><input type="text" placeholder="Introduzca la fecha nacimiento.." name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>">
		</div>
		<div>	
			<label for="<%=Constantes.PAR_NHERMANOS %>">Num. Hermanos: </label><input type="text" placeholder="Introduzca el numero de hermanos.." name="<%=Constantes.PAR_NHERMANOS %>" id="<%=Constantes.PAR_NHERMANOS %>">
		</div>
		<div>	
			<label for="<%=Constantes.PAR_ACTIVO %>">Activo: </label>
			<select>
				<option value="1">Activo</option>
				<option value="0">Desactivo</option>
			</select>
		</div>
		<input type="submit" value="enviar">
		
		<div align="center">
  		<input type="button" value="VOLVER ATRÁS" name="Back2" onclick="history.back()" />
  		</div>
 	</form>
	</form>
</body>
</html>