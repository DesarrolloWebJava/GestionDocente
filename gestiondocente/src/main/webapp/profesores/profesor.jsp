<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include  page="../includes/header.jsp"/>
<main>
Crear Profesor
<form action="<%=Constantes.SERVLET_PROFESOR%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>" method="post">
<input type="hidden" name=<%=Constantes.PAR_CODIGO%> id=<%=Constantes.PAR_CODIGO%> value="-1"/>
<div>
<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
<input type="text" placeholder="Introduzca el nombre..." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
<input type="text" placeholder="Introduzca los apellidos..." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_EMAIL%>">Email:</label>
<input type="email" placeholder="Introduzca el email" name="<%=Constantes.PAR_EMAIL%>" id="<%=Constantes.PAR_EMAIL%>"/>
</div>
<div>
<input type="submit" value="Enviar"/>
</div>
</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>