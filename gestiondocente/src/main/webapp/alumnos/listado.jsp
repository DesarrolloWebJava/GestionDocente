<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
		<main>
			<ul>
			<%
			/* esto es un scriplet */
				List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
			%>
			<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Alumno</a>
			<%
				for(Alumno alumno: alumnos){
					out.println("<li>"+alumno.toString()+"</li>");
				}
			%>
			</ul>
		</main>
		
			<%@ include file="../includes/footer.html" %>
		
	</body>
</html>