<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<jsp:include page="../includes/header.jsp"/>
<!-- comprobar si recibimos un objeto profesor -->
<%
Profesor profesor=(Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
String titulo="";
String titulo_boton="";
if(profesor == null){
	titulo ="Crear Profesor";
	titulo_boton = "Guardar Profesor";
	
	//asi no me da nulo y no casca, instancio un nuevo objeto alumno con value = -1
	profesor = new Profesor();
}
else{
	titulo ="Editar Profesor";
	titulo_boton="Actualizar Profesor";
}
%>	
<div class="container">
	<h1><%=titulo %></h1>
	
		<form role="form" action="<%=Constantes.SERVLET_PROFESOR%>" method="post">
			<div class="form-group">
		    <input type="hidden" class="form-control" id="<%=Constantes.PAR_CODIGO %>"  name="<%=Constantes.PAR_CODIGO %>" value="<%=profesor.getCodigo()%>">
		  </div>
			<div class="form-group">
		    <label for="<%=Constantes.PAR_NOMBRE %>">Nombre</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE %>"
		         value="<%=profesor.getNombre() %>" placeholder="Introduce tu nombre">
		  </div>
		  <div class="form-group">
		    <label for="<%=Constantes.PAR_APELLIDO %>">Apellido</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_APELLIDO %>" name="<%=Constantes.PAR_APELLIDO %>"
		          value="<%=profesor.getApellidos()%>" placeholder="Introduce tu apellido">
		  </div>
		  
		  <div class="form-group">
		    <label for="<%=Constantes.PAR_DNI %>">DNI</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI %>" 
		    	value="<%=profesor.getDni() %>" placeholder="Introduce tu DNI">
		  </div>
		  <div class="form-group">
		   <%
		  	String date="";
	    	GregorianCalendar gc = new GregorianCalendar();
	    	gc.setTime(profesor.getfNacimiento());
	    	date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
	    	
		  %>
		    <label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de Nacimento</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO  %>" 
		    	value="<%=date %>" placeholder="Introduce tu fecha de nacimiento">
		  </div>
		  <div class="form-group">
		   <label for="<%=Constantes.PAR_DIRECCION %>">Dirección</label>
		   <input type="text" class="form-control" id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION  %>" 
		  		value="<%=profesor.getDireccion() %>" placeholder="Introduce tu dirección">
		  </div>
		    <div class="form-group">
		   <label for="<%=Constantes.PAR_EMAIL%>">Email</label>
		   <input type="text" class="form-control" id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL  %>" 
		  	 value="<%=profesor.getEmail() %>" placeholder="Introduce tu email">
		  </div>
		   <div class="form-group">
		   <label for="<%=Constantes.PAR_NSS%>">Nº Seguridad Social</label>
		   <input type="text" class="form-control" id="<%=Constantes.PAR_NSS %>" name="<%=Constantes.PAR_NSS  %>" 
		  	value="<%=profesor.getnSS() %>" placeholder="Introduce tu nº de ss">
		  </div>
		  
		  
		  <button type="submit" class="btn btn-primary btn-lg"><%=titulo_boton %></button>
		</form>
	</div>


<%@ include file="../includes/footer.html" %>

</body>
</html>