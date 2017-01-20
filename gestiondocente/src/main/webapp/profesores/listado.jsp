<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"/>
<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Profesor</a>

	<%
		Map<Integer,Profesor> profesores = (Map<Integer,Profesor>) request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	
	for(Map.Entry<Integer,Profesor> entry: profesores.entrySet()){
		out.write("<p>"+entry.getValue().toString()+"</p>");
	}
	%>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>