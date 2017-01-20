<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"/>

<main>
Crear Profesor
	<form action="<%=Constantes.SERVLET_PROFESOR%>" method="post">
     <input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>" value="-1">
     <div>
       <label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
       <input type="text" placeholder="Introduzca aqui su nombre.." id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE%>" value="">
    </div>
    <div>
      <label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
      <input type="text" placeholder="Introduzca aqui el apellido.." id="<%=Constantes.PAR_APELLIDOS %>" name="<%=Constantes.PAR_APELLIDOS%>" value="">
    </div>
    <div>
      <label for="<%=Constantes.PAR_DNI %>">DNI:</label>
      <input type="text" placeholder="Introduzca aqui el DNI.." id="<%=Constantes.PAR_DNI %>" name="<%=Constantes.PAR_DNI%>" value="">
    </div>
    <div>
      <label for="<%=Constantes.PAR_EMAIL %>">Email:</label>
      <input type="email" placeholder="Introduzca aqui el email.." id="<%=Constantes.PAR_EMAIL %>" name="<%=Constantes.PAR_EMAIL%>" value="">
    </div>    
    <div>
      <label for="<%=Constantes.PAR_DIRECCION %>">Direccion:</label>
      <input type="text" placeholder="Introduzca aqui la direccion.." id="<%=Constantes.PAR_DIRECCION %>" name="<%=Constantes.PAR_DIRECCION%>" value="">
    </div>    
    <div>
      <label for="<%=Constantes.PAR_FNACIMIENTO %>">Fecha Nacimiento:</label>
      <input type="text" placeholder="Introduzca aqui la fecha de nacimiento.." id="<%=Constantes.PAR_FNACIMIENTO %>" name="<%=Constantes.PAR_FNACIMIENTO%>" value="">
    </div>
    <div>
      <label for="<%=Constantes.PAR_NSS %>">Número Seguridad Social:</label>
      <input type="number" placeholder="Introduzca aqui el número de la SS.." id="<%=Constantes.PAR_NSS %>" name="<%=Constantes.PAR_NSS%>" value="">
    </div>

    <input type="submit" value="Enviar"/>
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>