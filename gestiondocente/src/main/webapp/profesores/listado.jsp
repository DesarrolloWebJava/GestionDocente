<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
	<main>
	<%
		Map<Integer,Profesor> profesores = (Map<Integer,Profesor>) request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	%>
	<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Profeeeee</a>
	<%
	for(Map.Entry<Integer,Profesor> entry: profesores.entrySet()){

		String btn_delete ="<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+entry.getValue().getCodigo()+"'>Borrar</a>";

		out.println(entry.getValue().toString()+" <a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+entry.getValue().getCodigo()+"'>Editar</a>" +btn_delete);
	}
	%>
	</main>
	
	
<%@ include file="../includes/footer.html" %>
</body>
</html>