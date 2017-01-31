<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp" />
<main>
<header><h2>Usuarios conectados</h2></header>
<ul>
<%
List<Persona> personas=(List<Persona>)request.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
for(Persona entrada:personas ){
	String btn_expulsar="<a href='"+Constantes.SERVLET_USUARIO+"?sessionid="+entrada.getSessionId()+"'>Expulsar</a>";
	out.println("<li>"+entrada.getNombre()+" "+entrada.getSessionId()+" "+btn_expulsar+"</li>");
}
%>
</ul>
</main>
<%@ include file="../includes/footer.html"%>
</body>
</html>