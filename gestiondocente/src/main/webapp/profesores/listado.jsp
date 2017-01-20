<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../includes/header.jsp"/>

	<header><h1>Pagina listado de Profesores</h1></header>
	
	<main>
	<%
	//esto es un script de java
	Map<Integer, Profesor> profesores= (Map<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	
	%>
	<a href = "<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Profesor</a>
	
	<%
	
	for (Map.Entry<Integer, Profesor> profesor : profesores.entrySet()){
		
		Profesor p = profesor.getValue();
		//out.println("<li>"+p.getCodigo()+" "+p.getEmail()+"</li>");	
		out.write("<li>"+p.toString()+"</li>");
	}
	
	
	%>
	
	</main>
	<%@ include file = "../includes/footer.html" %>
</body>
</html>