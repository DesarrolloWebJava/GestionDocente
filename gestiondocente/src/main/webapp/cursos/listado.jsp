<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<main>

<%
	List<Curso> cursos = (List<Curso>)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
%>

<a href="<%=Constantes.SERVLET_CURSO%>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Curso</a>
<%

	for(Curso curso:cursos){
		//para el borrado
		String btn_delete="<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="
		+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"'>Borrar</a>";
		//para el update
		 out.println("<p>"+curso.toString()+"</p>"+"<a href='"+Constantes.SERVLET_CURSO+"?"+
		Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+
		curso.getCodigo()+"'>Editar</a>"+btn_delete); 
	}
%>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>