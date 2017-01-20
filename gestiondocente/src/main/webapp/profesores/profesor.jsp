<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
		CREAR PROFESOR
		<form action = "<%=Constantes.SERVLET_PROFESOR %>" method= "post">
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id ="<%=Constantes.PAR_CODIGO %>" value = "-1" />
			<div>
				<label for= "<%=Constantes.PAR_NOMBRE %>">Nombre: </label>
				<input type="text" placeholder="Introduzca un nombre..." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>"> 
			</div>
			<div>
				<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos: </label>
				<input type="text" placeholder="Introduce el apellido..." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_DNI %>">DNI: </label>
				<input type="text" placeholder="Introduzca el DNI..." name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_EMAIL %>">Email: </label>
				<input type="email" placeholder="Introduzca el email..." name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_DIRECCION %>">Dirección: </label>
				<input type="text" placeholder="Introduce una dirección..." name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de nacimiento: </label>
				<input type="text" placeholder="Introduzca una fecha de nacimiento..." name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>">
			</div>
			<div>
				<label for="<%=Constantes.PAR_NSS %>">Número de la SS: </label>
				<input type="text" placeholder="Introduzca un número de la SS..." name="<%=Constantes.PAR_NSS %>" id="<%=Constantes.PAR_NSS %>">
			</div>
			<input type="submit" value="Enviar"/>
		</form>
	</main>
	<%@ include file="../includes/footer.html" %>
</body>
</html>