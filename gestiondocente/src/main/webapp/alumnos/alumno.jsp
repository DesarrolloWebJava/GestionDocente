<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion docente - Crear Alumno</title>
</head>
<body>
Crear Alumno

	<form action="<%=Constantes.SERVLET_ALUMNO %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>"  id="<%=Constantes.PAR_CODIGO %>" value="-1">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input type="text" placeholder="Introduzca el nombre" name="<%=Constantes.PAR_NOMBRE %>"  id="<%=Constantes.PAR_NOMBRE %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input type="text" placeholder="Introduzca el apellido" name="<%=Constantes.PAR_APELLIDOS %>"  id="<%=Constantes.PAR_APELLIDOS %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION %>">Dirección:</label>
			<input type="text" placeholder="Introduzca la direccion" name="<%=Constantes.PAR_DIRECCION %>"  id="<%=Constantes.PAR_DIRECCION %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_DNI %>">DNI:</label>
			<input type="text" placeholder="Introduzca el DNI" name="<%=Constantes.PAR_DNI %>"  id="<%=Constantes.PAR_DNI %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
			<input type="email" placeholder="Introduzca el email" name="<%=Constantes.PAR_EMAIL %>"  id="<%=Constantes.PAR_EMAIL %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_NHERMANOS %>">N. de hermanos:</label>
			<input type="number" placeholder="Introduzca el numero de hermanos" name="<%=Constantes.PAR_NHERMANOS %>"  id="<%=Constantes.PAR_NHERMANOS %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de nacimiento:</label>
			<input type="text" placeholder="Introduzca la fecha de nacimiento" name="<%=Constantes.PAR_FNACIMIENTO%>"  id="<%=Constantes.PAR_FNACIMIENTO %>" >
		</div>
		<div>
			<label for="<%=Constantes.PAR_ACTIVO %>">Activo:</label>
			<select>
				<option value="1">Activo</option>
				<option value="0">Desactivado</option>
			</select>
		</div>
		<input type="submit" value="Enviar"/>
	</form>
</body>
</html>