<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include  page="../includes/header.jsp"/>
<%
String titulo="";
Profesor profesor=(Profesor)request.getAttribute(Constantes.ATT_PROFESOR);
if(profesor==null){
	titulo="Crear Profesor";
	profesor=new Profesor();
}
else{
	titulo="Actualizar Profesor";
}

%>
<main>
<header><h2><%=titulo %></h2></header>
<form action="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>" method="post">
<input type="hidden" name=<%=Constantes.PAR_CODIGO%> id=<%=Constantes.PAR_CODIGO%> value="<%=profesor.getCodigo() %>"/>
<div>
<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
<input type="text" placeholder="Introduzca el nombre..." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>" value="<%=profesor.getNombre() %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
<input type="text" placeholder="Introduzca los apellidos..." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>" value="<%=profesor.getApellidos() %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_EMAIL%>">Email:</label>
<input type="email" placeholder="Introduzca el email" name="<%=Constantes.PAR_EMAIL%>" id="<%=Constantes.PAR_EMAIL%>" value="<%=profesor.getEmail() %>"/>
</div>
<div>
<input type="submit" value="Enviar"/>
</div>
</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>