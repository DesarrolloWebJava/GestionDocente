<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<body>
	<main>
	<%
	/* Esto es un scriplet*/
	
	//recogemos el atributo de la request
	
	Map<Integer, Profesor> profesores=(Map <Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	%>
	<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Profesor</a>
	<%
	for(Map.Entry<Integer, Profesor> entry: profesores.entrySet()){
		int codigo = entry.getKey();
		Profesor profesor = entry.getValue();
		out.write("<p>"+entry.getValue().toString()+"</p>");
	}
	%>
	</main>
 	<%@ include file="../includes/footer.html" %>
</body>
</html>