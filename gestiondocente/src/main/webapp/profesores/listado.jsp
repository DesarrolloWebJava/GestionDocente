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
		Profesor profesor = entry.getValue();
		String btn_update =  "<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+entry.getValue().getCodigo()+"'>Editar</a>";
		String btn_borrar =  "<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+entry.getValue().getCodigo()+"'>Borrar</a>";
		out.write("<p>"+entry.getValue().toString()+btn_update+" "+btn_borrar+"</p>");
	}
	%>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>