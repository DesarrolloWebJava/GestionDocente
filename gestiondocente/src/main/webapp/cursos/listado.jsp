<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
	<main>
	<%
	/*Esto es un scriplet */
	
	//recogemos el atributo de la request
	List<Curso> listaCurso = (List<Curso>)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
	%>
	<a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Nuevo Curso</a>
	<%
	
	for(Curso curso: listaCurso){
		String btn_delete ="<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"'>Borrar</a>";

		out.println(curso.toString()+" <a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"'>Editar</a>" +btn_delete);
	}
	 %>
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>