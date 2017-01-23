<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<jsp:include page="../includes/header.jsp" />
		<main>
			<ul>
			<%
			/* esto es un scriplet */
				Map<Integer, Profesor> profesor = (HashMap<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
			%>
			<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear profesor</a>
			<%
				for (Map.Entry<Integer, Profesor> entry : profesor.entrySet()) {
					String btn_delete="<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+ entry.getValue().getCodigo() + "'> Borrar </a>";
					out.println("<li>"+ entry.getValue().toString()+"<a href='"+Constantes.SERVLET_PROFESOR+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+ entry.getKey() + "'> Editar </a>"+btn_delete + "</li>");
				}
			%>
			</ul>
		</main>
		<%@ include file="../includes/footer.html" %>
	</body>
</html>