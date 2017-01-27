<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp" ></jsp:include>
<%
Curso curso=(Curso)request.getAttribute(Constantes.ATT_CURSO);
String titulo="";
if(curso==null){
titulo="Crear Curso";
curso=new Curso();
}
else
	titulo="Actualizar Curso";
	
%>
<main>
<header><h2><%=titulo %></h2></header>
<form action="<%=Constantes.SERVLET_CURSO %>" method=post>
<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo() %>" />
<div>
<label for=<%=Constantes.PAR_NOMBRE %>>Nombre:</label>
<input type="text" name="<%=Constantes.PAR_NOMBRE %>" id="<%=Constantes.PAR_NOMBRE %>" value="<%=curso.getNombre() %>" placeholder="Introduce el nombre..."/>
</div>
<div>
<label for="<%=Constantes.PAR_DURACION %>">Duración:</label>
<input type="number" name="<%=Constantes.PAR_DURACION %>" id="<%=Constantes.PAR_DURACION %>" value="<%=curso.getDuracion() %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_FINICIO %>">F.Inicio:</label>
<%
String date="";
GregorianCalendar gc=new GregorianCalendar();
gc.setTime(curso.getfInicio());
date=gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
%>
<input type="text" name="<%=Constantes.PAR_FINICIO %>" id="<%=Constantes.PAR_FINICIO %>" value="<%=date %>"/>
</div>
<div>
<label for="<%=Constantes.PAR_FFIN %>">F.Fin:</label>
<%
gc.setTime(curso.getfFin());
date=gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);

%>
<input type="text" name="<%=Constantes.PAR_FFIN %>" id="<%=Constantes.PAR_FFIN %>" value=<%=date%> %/>
</div>
<input type="submit" value="Enviar"/>
</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>