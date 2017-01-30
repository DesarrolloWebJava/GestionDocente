<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages"/>
    <jsp:include page="../includes/header.jsp" />

	
	
		<main>
			
			<%
			/* esto es un scriplet */
				Map<Integer, Profesor> profesor = (HashMap<Integer, Profesor>)request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
			%>
			<c:import url="/includes/mensajes.jsp"/>
			<a href="<%=Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear profesor</a>
			<ul>
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