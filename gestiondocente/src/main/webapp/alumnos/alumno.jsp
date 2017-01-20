<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<main>
Crear Alumno
<body>
<form action="<%=Constantes.SERVLET_ALUMNO %>" method="post">

<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="-1"/>
<div>
<label for="<%=Constantes.PAR_NOMBRE %>">Nombre:</label>
<input type="text" placeholder="Introduzca el nombre..." name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_APELLIDOS %>">Apellidos:</label>
<input type="text" placeholder="Introduzca los apellidos..." name="<%=Constantes.PAR_APELLIDOS %>" id="<%=Constantes.PAR_APELLIDOS %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_DNI %>">DNI:</label>
<input type="text" placeholder="Introduzca el DNI..." name="<%=Constantes.PAR_DNI %>" id="<%=Constantes.PAR_DNI %>"/>
</div>

<div>
<label for="<%=Constantes.PAR_FNACIMIENTO%>">Fecha Nacimiento:</label>
<input type="text" placeholder="Introduzca la fecha nacimiento..." name="<%=Constantes.PAR_FNACIMIENTO%>" id="<%=Constantes.PAR_FNACIMIENTO%>"/>
</div>
<div>
<label for="<%=Constantes.PAR_DIRECCION%>">Dirección:</label>
<input type="text" placeholder="Introduzca la dirección" name="<%=Constantes.PAR_DIRECCION%>" id="<%=Constantes.PAR_DIRECCION%>"/>
</div>
<div>
<label for="<%=Constantes.PAR_EMAIL%>">Email:</label>
<input type="email" placeholder="Introduzca el email" name="<%=Constantes.PAR_EMAIL%>" id="<%=Constantes.PAR_EMAIL%>"/>
</div>
<div>
<label for="<%=Constantes.PAR_NHERMANOS%>">N. Hermanos:</label>
<input type="number" placeholder="Introduzca el número de hermanos..." name="<%=Constantes.PAR_NHERMANOS%>" id="<%=Constantes.PAR_NHERMANOS%>"/>
</div>
<div>
<label for="<%=Constantes.PAR_ACTIVO%>">Activo</label>
<select name="<%=Constantes.PAR_ACTIVO%>">
<option value="1">Activo</option>
<option value="0">Desactivado</option>
</select>
</div>
<div>
<input type="submit" value="Enviar"/>
</div>
</form>
</main>
<%@ include file="../includes/footer.html"%>
</body>
</html>