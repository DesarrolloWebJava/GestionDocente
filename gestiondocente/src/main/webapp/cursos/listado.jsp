<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ipartek.formacion.dbms.pojo.CursoDuracionComparator"%>
<%@page import="java.util.Collections"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<main>
<%
// Escribimos el scriptlet de la pagina

List<Curso> cursos = (List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
%>
<a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION%>=<%=Constantes.OP_CREATE%>">Crear Curso</a>
<%
cursos.sort (new CursoDuracionComparator());

for (Curso curso : cursos)
{
	String btn_delete = "<a href='"+ Constantes.SERVLET_CURSO + "?" + Constantes.PAR_OPERACION +"="+ Constantes.OP_DELETE +"&" + Constantes.PAR_CODIGO+"="+ curso.getCodigo()+"'>Borrar</a>";
	String btn_update = "<a href='"+ Constantes.SERVLET_CURSO + "?" + Constantes.PAR_OPERACION +"="+ Constantes.OP_UPDATE +"&" + Constantes.PAR_CODIGO+"="+ curso.getCodigo()+"'>Editar</a>";
	out.print("<p>" + curso.toString() + "  " + btn_update +  "  " + btn_delete + "</p>" );
}
%>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>