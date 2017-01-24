<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../includes/header.jsp"/>
<%
	String titulo = "";
	Profesor profesor = (Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
	if(profesor==null){ //create
		titulo = "Crear Profesor";
		profesor = new Profesor();
	}else{//update
		titulo = "Editar Profesor";
	}
%>
	<main> 
	<%=titulo %>
		<form action="<%=Constantes.SERVLET_PROFESOR %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO%>" id="<%=Constantes.PAR_CODIGO%>" value="-1">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input type="text" placeholder="Introduzca su nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>">
		</div>
				<div>
			<label for="<%=Constantes.PAR_NSS %>">NSS:</label>
			<input type="text" placeholder="Introduzca su numero de la S.S.." name="<%=Constantes.PAR_NSS %>" id="<%=Constantes.PAR_NSS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input type="text" placeholder="Introduzca sus apellidos.." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DNI %>">DNI:</label>
			<input type="text" placeholder="Introduzca su DNI.." name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
			<input type="email" placeholder="Introduzca su email.." name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION %>">Dirección:</label>
			<input type="text" placeholder="Introduzca su email.." name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de nacimiento:</label>
			<input type="text" placeholder="Introduzca su fecha de nacimiento.." name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_NHERMANOS %>">Numero de hermanos:</label>
			<input type="number" placeholder="Introduzca el numero de hermanos.." name="<%=Constantes.PAR_NHERMANOS %>" id="<%=Constantes.PAR_NHERMANOS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_ACTIVO %>">Activo:</label>
			<select name="<%=Constantes.PAR_ACTIVO %>">
				<option value="1">Activo </option>
				<option value="0">Desctivado </option>
			</select>
		</div>
		
		<input type="submit" value="enviar">

		</form>
	</main> 
</body>
	<%@ include file="../includes/footer.html" %>
</html>