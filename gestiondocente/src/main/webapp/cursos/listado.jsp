<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="../includes/header.jsp"/>
<main>
	<ul>
		<%
		List<Curso>cursos = (List<Curso>)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);			
		%>
		
		<a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Curso</a>
		<%
		
		for (Curso curso: cursos){
			
			String btn_delete="<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"'>Borrar Curso</a>";
			out.println("<li>"+curso.toString()+"<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"'>Editar</a></li>"+btn_delete);
		}
		
		%>

	</ul>

</main>

<%@include file="../includes/footer.html" %>


</body>
</html>