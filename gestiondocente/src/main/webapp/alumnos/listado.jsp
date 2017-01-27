<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages"/>
<jsp:include page="../includes/header.jsp" />

		<main>
			
			<%
			/* esto es un scriplet */
			/*	List<Alumno> alumnos = (List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
				alumnos.sort(null);*/
				
			%>
			<c:import url="/includes/mensajes.jsp"/>
			<a href="<%=Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Alumno</a>
			<ul>
			<c:forEach items="${requestScope.listadoAlumnos}" var="alumno">
			
				<div><li>${alumno}</li></div>
			</c:forEach>
			
			
			<%
				/* for(Alumno alumno: alumnos){
				
					String btn_delete="<a href='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+ "'> Borrar </a>";
					out.println("<li>"+alumno.toString()+"<a href='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+ "'> Editar</a>"+btn_delete+"</li>");
				
				} */
			%>
			</ul>
		</main>
			<%@ include file="../includes/footer.html" %>
		
	</body>
</html>