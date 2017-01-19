<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Getsion Docente - Crear Alumno</title>
</head>
<body>
Crear Alumno

		<form action="<%=Constantes.SERVLET_ALUMNO%>" method="post">
			<div>
				<input type="hidden" name ="<%=Constantes.PAR_CODIGO%>" id="<%=Constantes.PAR_CODIGO%>" value="-1">
				<label for ="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
				<input type="text" placeholder = "Introduzca el nombre ..." name ="<%=Constantes.PAR_NOMBRE%>" id="<%=Constantes.PAR_NOMBRE%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_APELLIDOS%>">Apellidos:</label>
				<input type ="text" placeholder = "Introduzac los apellidos ..." name = "<%=Constantes.PAR_APELLIDOS%>" id ="<%=Constantes.PAR_APELLIDOS%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_DNI%>">DNI:</label>
				<input type ="text" placeholder = "Introduzac el DNI ..." name = "<%=Constantes.PAR_DNI%>" id ="<%=Constantes.PAR_DNI%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_EMAIL%>">Email:</label>
				<input type ="text" placeholder = "Introduzac el E-mail ..." name = "<%=Constantes.PAR_EMAIL%>" id ="<%=Constantes.PAR_EMAIL%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_DIRECCION%>">Direccion:</label>
				<input type ="text" placeholder = "Introduzac la direccion ..." name = "<%=Constantes.PAR_DIRECCION%>" id ="<%=Constantes.PAR_DIRECCION%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_FNACIMIENTO%>">Fecha Nacimiento:</label>
				<input type ="text" placeholder = "Introduzac la fecha de nacimiento ..." name = "<%=Constantes.PAR_FNACIMIENTO%>" id ="<%=Constantes.PAR_FNACIMIENTO%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_NHERMANOS%>">Numerpo Hermanos:</label>
				<input type ="text" placeholder = "Introduzac el numero de hermanos ..." name = "<%=Constantes.PAR_FNACIMIENTO%>" id ="<%=Constantes.PAR_FNACIMIENTO%>">
			</div>
			<div>
				<label for ="<%=Constantes.PAR_ACTIVO%>">Activo:</label>
				<select>
					<option value="1">Activo</option>
					<option value ="0">Desactivo</option>
				
				</select>
			</div>
			<input type ="submit" value ="Enviar">
		</form>
</body>
</html>