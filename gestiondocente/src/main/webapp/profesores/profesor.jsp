<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
Crear Profesor
<main>
	<form action="<%=Constantes.SERVLET_PROFESOR %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="-1">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input type="text" required placeholder="Indroduzca el nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input type="text" placeholder="Introduzca el apellido.." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DNI %>">DNI:</label>
			<input type="text" required placeholder="Introduzca el dni.." name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_NSS %>">NSS:</label>
			<input type="text" required placeholder="Introduzca el nss.." name="<%=Constantes.PAR_NSS %>" id="<%=Constantes.PAR_NSS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
			<input type="email" placeholder="Introduzca el email.." name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION %>">Dirección:</label>
			<input type="text" placeholder="Introduzca el direccion.." name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha Nacimiento:</label>
			<input type="text" required placeholder="Introduzca la fecha de nacimiento" name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>">
		</div>
		<input type="submit" value="Enviar">
	</form>
</main>
<%@ include file="../includes/footer.html" %>