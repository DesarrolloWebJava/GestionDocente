<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<jsp:include page="../includes/header.jsp"/>

	<main>
	<%
	
	Map<Integer,Profesor> profesor = (Map<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	%>
	<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Profesor</a>
	<ul>
	<%
	for(Map.Entry<Integer,Profesor> entry:profesor.entrySet()){
		out.println("<li>clave="+entry.getKey()+",valor"+entry.getValue()+"</li>");
	}
	
	/*Esto es un scriplet*/
	//Recogemos el atributo de la request
	
	%>
	</ul>
	</main>
	<%@include file="../includes/footer.html" %>

</body>
</html>