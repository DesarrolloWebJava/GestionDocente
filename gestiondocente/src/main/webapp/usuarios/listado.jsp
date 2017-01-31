<%@page import="com.ipartek.formacion.controller.UsuarioServlet"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="java.util.List"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<main>
<header><h2>Usuarios Conectados</h2></header>
<!-- Recorrer la lista de personas(usuarios) en el contexto de la aplicacion hecer servlet nuevo-->
<!-- Plus:añadir boton de kick(expulsar) -->
<%
	List<Persona> usuario = (List<Persona>)request.getAttribute(Constantes.ATT_LISTADO_USUARIOS);
for(Persona usuario1: usuario){
	%>
 <div><%=usuario1.getNombre()%> <a href="<%=Constantes.SERVLET_USUARIO %>?sessionid=<%=usuario1.getSessionId() %>">Expulsar</a>
 </div>
 <%
}
%>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>