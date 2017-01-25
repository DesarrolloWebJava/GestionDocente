<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"></jsp:include>

<main>
<h2>Listado de Cursos</h2>
<a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Curso</a>
<ul>
<%
List<Curso> cursos=(List<Curso>)request.getAttribute(Constantes.ATT_LISTADO_CURSOS);


for (Curso curso:cursos){
	String btn_update="<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_UPDATE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"'>Editar</a>";
	String btn_delete="<a href='"+Constantes.SERVLET_CURSO+"?"+Constantes.PAR_OPERACION+"="+Constantes.OP_DELETE+"&"+Constantes.PAR_CODIGO+"="+curso.getCodigo()+"'>Eliminar</a>";
	out.println("<li>"+curso.toString()+btn_update+btn_delete+"</li>");
}
%>

</ul>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>