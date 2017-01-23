<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
String titulo;
  Alumno alumno = (Alumno) request.getAttribute(Constantes.ATT_ALUMNO);
  if (alumno==null){ // Operacion Create
   titulo = "Crear Alumno";
   alumno = new Alumno();
  }
  else
  {
   titulo = "Actualizar Alumno";
  }
 %>
<main>
<header><h2><%=titulo%></h2></header>

	<form action="<%=Constantes.SERVLET_ALUMNO%>" method="post">
     <input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>" value="<%=alumno.getCodigo()%>">
     <div>
       <label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
       <input type="text" placeholder="Introduzca aqui su nombre.." id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE%>" value="<%=alumno.getNombre()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
      <input type="text" placeholder="Introduzca aqui el apellido.." id="<%=Constantes.PAR_APELLIDOS %>" name="<%=Constantes.PAR_APELLIDOS%>" value="<%=alumno.getApellidos()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_DNI %>">DNI:</label>
      <input type="text" placeholder="Introduzca aqui el DNI.." id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI%>" value="<%=alumno.getDni()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
      <input type="email" placeholder="Introduzca aqui el email.." id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL%>" value="<%=alumno.getEmail()%>">
    </div>    
    <div>
      <label for="<%=Constantes.PAR_DIRECCION %>">Direccion:</label>
      <input type="text" placeholder="Introduzca aqui la direccion.." id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION%>" value="<%=alumno.getDireccion() %>">
    </div>    
    <div>
     <%
      String  date = "";
      GregorianCalendar gc = new GregorianCalendar();
      gc.setTime(alumno.getfNacimiento());
      date = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + gc.get(GregorianCalendar.MONTH) + "/" + gc.get(GregorianCalendar.YEAR);
     %>
      <label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha Nacimiento:</label>
      <input type="text" placeholder="Introduzca aqui la fecha de nacimiento.." id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO%>" value="<%=date%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_NHERMANOS %>">Numero Hermanos:</label>
      <input type="number" placeholder="Introduzca aqui el numero de hermanos.." id="<%=Constantes.PAR_NHERMANOS %>" name="<%=Constantes.PAR_NHERMANOS%>" value="<%=alumno.getnHermanos()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_ACTIVO %>">Activo:</label>
      <select id="<%=Constantes.PAR_ACTIVO %>" name="<%=Constantes.PAR_ACTIVO %>">
      <%
       if (alumno.isActivo()){
        %>
        <option selected value="1">Activo</option>
        <option value="0">Desactivo</option>
       <%
       }else{
       %>
        <option value="1">Activo</option>
        <option selected value="0">Desactivo</option>
       <%
       }
       %>
       
      </select>
    </div>
    <input type="submit" value="Enviar"/>
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>