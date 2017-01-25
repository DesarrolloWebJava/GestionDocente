<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />

<% 
	String titulo = "";
	Profesor profesor = (Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
	if(profesor==null){
		titulo = "Crear profesor.";
		profesor = new Profesor();
	}else{//update
		titulo = "Actualizar profesor.";
	}
%>

<p>Crear/Editar Profesor</p>
<form action="<%=Constantes.SERVLET_PROFESOR %>" method="post">
			<input	type="hidden"  
					name="<%=Constantes.PAR_CODIGO %>" 
					id="<%=Constantes.PAR_CODIGO %>"
					value="<%=profesor.getCodigo()%>"
					 />
		<div>
		<!-- VER LABEL // se usa el ID -->
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input type="text" placeholder="Introduzca el nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>" value="<%=profesor.getNombre()%>"/>
		</div>
		<div>
			<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
			<input type="text" placeholder="Introduzca los apellidos.." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>" value="<%=profesor.getApellidos()%>"/>
		</div>
		<div>
			<label for="<%=Constantes.PAR_DNI %>">DNI:</label>
			<input type="text" placeholder="Introduzca el DNI.." name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>" value="<%=profesor.getDni()%>"/>
		</div>
		<div>
			<label for="<%=Constantes.PAR_nSS %>">Número de la Seguridad Social:</label>
			<input type="text" placeholder="Introduzca el nº de la SS.." name="<%=Constantes.PAR_nSS %>" id="<%=Constantes.PAR_nSS %>" value="<%=profesor.getnSS()%>"/>
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
			<input type="text" placeholder="Introduzca el email.." name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>" value="<%=profesor.getEmail()%>"/>
		</div>
		<div>
			<label for="<%=Constantes.PAR_DIRECCION %>">Dirección:</label>
			<input type="text" placeholder="Introduzca su direccion.." name="<%=Constantes.PAR_DIRECCION %>" id="<%=Constantes.PAR_DIRECCION %>" value="<%=profesor.getDireccion()%>"/>
		</div>
		<div>
			<label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de nacimiento:</label>
			<input type="text" placeholder="Introduzca la fecha de nacimiento.." name="<%=Constantes.PAR_FNACIMIENTO %>" id="<%=Constantes.PAR_FNACIMIENTO %>" value="<%=profesor.getfNacimiento()%>"/>
		</div>		
		<input type="submit" value="Enviar"/>
	</form>
	<%@ include file="../includes/footer.html" %>
</body>
</html>