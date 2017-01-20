<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<jsp:include page="../includes/header.jsp"/>
	
<main>
	<div  class="container">
	<h1>Crear Profesor</h1>
	
		<form role="form" action="<%=Constantes.SERVLET_PROFESOR%>" method="post">
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
		   <label for="<%=Constantes.PAR_nSS%>">Nº Seguridad Social</label>
		   <input type="text" class="form-control" id="<%=Constantes.PAR_nSS %>" name="<%=Constantes.PAR_nSS  %>" 
		  	placeholder="Introduce tu nº de ss">
		  </div>
		  
		  
		  <button type="submit" class="btn btn-primary btn-lg">Insertar Profesor</button>
		</form>
	</div>
</main>

<%@ include file="../includes/footer.html" %>

</body>
</html>