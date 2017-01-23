<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
	String titulo= ""; 
	Alumno alumno= (Alumno)request.getAttribute(Constantes.ATT_ALUMNO);

	if (alumno==null){//create
		titulo = "Crear Alumno";
		alumno = new Alumno();
	}else{//update
		titulo= "Actualizar Alumno";
	}
%>
<main>
	<header><h2><%=titulo %></h2></header> 
	<form action="<%=Constantes.SERVLET_ALUMNO %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="<%=alumno.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input value="<%=alumno.getNombre() %>" type="text" placeholder="Indroduzca el nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input value="<%=alumno.getApellidos() %>" type="text" placeholder="Introduzca el apellido.." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DNI %>">DNI:</label>
			<input value="<%=alumno.getDni() %>" type="text" placeholder="Introduzca el dni.." name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
			<input value="<%=alumno.getEmail() %>"type="email" placeholder="Introduzca el email.." name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION %>">Dirección:</label>
			<input value="<%=alumno.getDireccion() %>" type="text" placeholder="Introduzca el direccion.." name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>">
		</div>
		<div>
			<%
				String date = "";
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(alumno.getfNacimiento());
				date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+gc.get(GregorianCalendar.MONTH)+"/"+gc.get(GregorianCalendar.YEAR);
				
			%>
			<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha Nacimiento:</label>
			<input value="<%=alumno.getfNacimiento() %>" type="text" placeholder="Introduzca la fecha de nacimiento" name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_NHERMANOS %>">N. Hermanos:</label>
			<input value="<%=alumno.getnHermanos()%>" type="number" placeholder="Introduzca el numero de hermanos" name="<%=Constantes.PAR_NHERMANOS %>" id="<%=Constantes.PAR_NHERMANOS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_ACTIVO%>">Activo: </label>
			<select name="<%=Constantes.PAR_ACTIVO%>">
			<%
				if(alumno.isActivo()){
			%>
					<option selected value="1">Activo</option>
					<option value="0">Desactivado</option>
			<%
				}else{
			%>
				<option value="1">Activo</option>
				<option selected value="0">Desactivado</option>
			<%
				}
			%>
			</select>
		</div>
		<input type="submit" value="Enviar">
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>