<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
	<main>
	<% /* Esto es un scriplet */
	// recogemos el atributo de la request
	Map<Integer, Profesor> profesores = (Map<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	
	for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
		out.write("<p>"+entry.getValue().toString()+"</p>");	
	}
	%>
</body>
<%@ include file="../includes/footer.html" %>
</html>