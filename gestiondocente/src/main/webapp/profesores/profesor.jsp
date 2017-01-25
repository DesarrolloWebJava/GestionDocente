<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%><%@page import="com.ipartek.formacion.controller.Constantes"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../includes/header.jsp"/>
<main> 
<%
	String titulo = "";
	Profesor profesor = (Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
	if(profesor==null){ //create
		titulo = "Crear Profesor";
		profesor = new Profesor();
	}else{//update
		titulo = "Actualizar Profesor";
	}
%>
	<header><h2><%=titulo %></h2> </header>	
		<form action="<%=Constantes.SERVLET_PROFESOR %>" method="post">
		<input type="hidden" name="<%=Constantes.PAR_CODIGO%>" id="<%=Constantes.PAR_CODIGO%>" value="<%=profesor.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
			<input value="<%=profesor.getNombre() %>" type="text" placeholder="Introduzca su nombre.." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>">
		</div>
				<div>
			<label for="<%=Constantes.PAR_NSS %>">NSS:</label>
			<input type="<%=profesor.getnSS() %>" placeholder="Introduzca su numero de la S.S.." name="<%=Constantes.PAR_NSS %>" id="<%=Constantes.PAR_NSS %>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
			<input type="<%=profesor.getEmail() %>" placeholder="Introduzca su email.." name="<%=Constantes.PAR_EMAIL %>" id="<%=Constantes.PAR_EMAIL %>">
		</div>
		<input type="submit" value="enviar">
		</form>
	</main> 
</body>
	<%@ include file="../includes/footer.html" %>
</html>