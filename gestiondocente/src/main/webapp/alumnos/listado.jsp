<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
Página Listado Alumnos
<main>
<%-- <%
/*Esto es un scriplet*/
List<Alumno> alumnos=(List<Alumno>)request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
%> --%>
<a href="<%=Constantes.SERVLET_ALUMNO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Alumno</a>
<c:if test="not empty ${listadoAlumnos} ">
	<c:forEach var="alumno" items="${listadoAlumnos}">
	<div>
	
	${alumno} <a href="">Editar</a><a href="">Eliminar</a>
	</div>
	</c:forEach>
</c:if>

<!--<ul>  -->
<%-- <%
for(Alumno alumno:alumnos){
	String btn_delete="<a href='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+"'>Borrar</a>";
	out.println("<li>"+alumno.toString()+"  <a href='"+Constantes.SERVLET_ALUMNO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+alumno.getCodigo()+"'>Editar </a>"+btn_delete+"</il>");
	
	
}
%> --%>
<!-- </ul> -->
</main>
<%@ include file="../includes/footer.html"%>
</body>
</html>