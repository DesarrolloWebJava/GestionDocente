<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
	<main>
	<% /* Esto es un scriplet */
	// recogemos el atributo de la request
	%>
	<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Profesor</a> <!-- petición get  -->
	<%
	Map<Integer, Profesor> profesores = (Map<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	
	for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
		String btn_delate = "<a href='"Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO"="+entry.getValue().getCodigo()'Editar</a>";
		String btn_borrar = "<a href='"Constantes.SERVLET_PROFESOR+"'>Borrar</a>";
		out.write("<p>"+entry.getValue().toString()+btn_update+btn_borrar"</p>");	
	}
	%>
</body>
<%@ include file="../includes/footer.html" %>
</html>