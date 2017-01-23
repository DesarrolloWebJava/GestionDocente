<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"/>
<%
String titulo="";
String fechaNacimiento="";

Profesor profesor = (Profesor) request.getAttribute(Constantes.ATT_PROFESOR);

if (profesor == null){ // Crear Profesor
	titulo = "Crear Profesor";
   profesor = new Profesor();
}
else{ // Actualizar Profesor
	titulo = "Actualizar Profesor";
}
GregorianCalendar gc = new GregorianCalendar();
gc.setTime(profesor.getfNacimiento());
fechaNacimiento = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (gc.get(GregorianCalendar.MONTH)+1) + "/" + gc.get(GregorianCalendar.YEAR);

%>

<main>
<header><h2><%=titulo%></h2></header>

	<form action="<%=Constantes.SERVLET_PROFESOR%>" method="post">
     <input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>" value="<%=profesor.getCodigo()%>">
     <div>
       <label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
       <input type="text" placeholder="Introduzca aqui su nombre.." id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE%>" value="<%=profesor.getNombre()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
      <input type="text" placeholder="Introduzca aqui el apellido.." id="<%=Constantes.PAR_APELLIDOS %>" name="<%=Constantes.PAR_APELLIDOS%>" value="<%=profesor.getApellidos()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_DNI %>">DNI:</label>
      <input type="text" placeholder="Introduzca aqui el DNI.." id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI%>" value="<%=profesor.getDni()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
      <input type="email" placeholder="Introduzca aqui el email.." id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL%>" value="<%=profesor.getEmail()%>">
    </div>    
    <div>
      <label for="<%=Constantes.PAR_DIRECCION %>">Direccion:</label>
      <input type="text" placeholder="Introduzca aqui la direccion.." id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION%>" value="<%=profesor.getDireccion()%>">
    </div>    
    <div>
      <label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha Nacimiento:</label>
      <input type="text" placeholder="Introduzca aqui la fecha de nacimiento.." id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO%>" value="<%=fechaNacimiento%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_NSS %>">Número Seguridad Social:</label>
      <input type="number" placeholder="Introduzca aqui el número de la SS.." id="<%=Constantes.PAR_NSS %>" name="<%=Constantes.PAR_NSS%>" value="<%=profesor.getnSS()%>">
    </div>

    <input type="submit" value="Enviar"/>
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>