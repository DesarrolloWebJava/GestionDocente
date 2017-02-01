<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Persona"%>
<%@page import="java.util.List"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<main>
<header>
	<h1>Usuarios conectados</h1>
</header>
<!-- Recorrer la lista de personas (usuarios conectados) -->

<% List<Persona> usuarios =
	(List<Persona>) request.getAttribute(Constantes.ATT_LISTADO_USUARIOS); %>

	<br>
<div>
		<%	
			for(Persona usuario : usuarios){%>
			<%=	usuario.getNombre() %>
			<a href="<%=Constantes.SERVLET_ADMIN%>?sessionid=<%=usuario.getSessionId()%>">Expulsar</a>
		
		<%}%>
</div>
</main>
	<div>
		<%@ include file="../includes/footer.html" %>
	</div>
	<!-- Plus: añadir a cada usuario un kick - botón de delete -->
</body>
</html>