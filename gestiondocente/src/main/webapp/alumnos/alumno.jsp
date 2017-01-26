<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="../includes/header.jsp"/>
<!-- comprobar si recibimos un objeto alumno -->
<%
Alumno alumno=(Alumno)request.getAttribute(Constantes.ATT_ALUMNO);
String titulo="";
String titulo_boton="";
if(alumno == null){
	titulo ="Crear Alumno";
	titulo_boton = "Guardar Alumno";
	
	//asi no me da nulo y no casca, instancio un nuevo objeto alumno con value = -1
	alumno = new Alumno();
}
else{
	titulo ="Actualizar Alumno";
	titulo_boton="Actualizar Alumno";
}
%>

<div class="container">
	
	<h1><%=titulo %></h1>
	
		<form role="form" action="<%=Constantes.SERVLET_ALUMNO%>" method="post">
			<div class="form-group">
		    <input type="hidden" class="form-control" id="<%=Constantes.PAR_CODIGO %>"  name="<%=Constantes.PAR_CODIGO %>" value="<%=alumno.getCodigo()%>">
		  </div>
			<div class="form-group">
		    <label for="<%=Constantes.PAR_NOMBRE %>">Nombre</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE %>"
		         value="<%=alumno.getNombre()%>"  placeholder="Introduce tu nombre" >
		  </div>
		  <div class="form-group">
		    <label for="<%=Constantes.PAR_APELLIDO %>">Apellido</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_APELLIDO %>" name="<%=Constantes.PAR_APELLIDO %>"
		           value="<%=alumno.getApellidos()%>"  placeholder="Introduce tu apellido">
		  </div>
		  
		  <div class="form-group">
		    <label for="<%=Constantes.PAR_DNI %>">DNI</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI %>" 
		    	 value="<%=alumno.getDni()%>" placeholder="Introduce tu DNI">
		  </div>
		  <%
		  	String date="";
	    	GregorianCalendar gc = new GregorianCalendar();
	    	gc.setTime(alumno.getfNacimiento());
	    	date = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
	    	
		  %>
		  <div class="form-group">
		    <label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha de Nacimento</label>
		    <input type="text" class="form-control" id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO  %>" 
		    	 value="<%=date%>" placeholder="Introduce tu fecha de nacimiento">
		  </div>
		  <div class="form-group">
		   <label for="<%=Constantes.PAR_DIRECCION %>">Dirección</label>
		   <input type="text" class="form-control" id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION  %>" 
		  	 value="<%=alumno.getDireccion()%>" placeholder="Introduce tu dirección">
		  </div>
		    <div class="form-group">
		   <label for="<%=Constantes.PAR_EMAIL%>">Email</label>
		   <input type="text" class="form-control" id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL  %>" 
		   value="<%=alumno.getEmail()%>" 	placeholder="Introduce tu email">
		  </div>
		    <div class="form-group">
		   <label for="<%=Constantes.PAR_NHERMANOS%>">Nº de hermanos</label>
		   <input type="number" class="form-control" id="<%=Constantes.PAR_NHERMANOS %>" name="<%=Constantes.PAR_NHERMANOS  %>" 
		  	 value="<%=alumno.getnHermanos()%>" placeholder="Introduce tu nº de hermanos">
		  </div>
		   <div class="form-group">
		  
		   <label for="<%=Constantes.PAR_ACTIVO%>">Activo</label>
		   <select class="form-control" name"<%=Constantes.PAR_ACTIVO  %>" id="<%=Constantes.PAR_ACTIVO %>" >
	 		
	 		 <%
		   	if(alumno.isActivo()){
		   	%>
		   	<option selected value="1">Activo</option>
	 		 <option value="0">Desactivado</option>
		   	<% }else{
		   	%>
		   		<option  value="1">Activo</option>
		 		<option selected value="0">Desactivado</option>
		 	<% 
		   	}
		   %> 
			</select>
		  </div>
		 
		  <button type="submit" class="btn btn-primary btn-lg"><%=titulo_boton %></button>
		</form>
	</div>

<%@ include file="../includes/footer.html" %>
</body>
</html>
