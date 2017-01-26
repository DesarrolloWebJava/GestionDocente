<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"/>
<%
 String titulo="";
String titulo_boton;
String  fechaInicio="";
String  fechaFin="";
 Curso curso = (Curso)request.getAttribute(Constantes.ATT_CURSO);
 if (curso == null)
 {
	 titulo = "Crear Curso";
	 titulo_boton="Crear Curso";
	 curso = new Curso();
 }
 else
 {
	 titulo = "Actualizar Curso";
	 titulo_boton="Actualizar Curso";
	 
 }
 
 GregorianCalendar gc = new GregorianCalendar();
 gc.setTime(curso.getfInicio());
 fechaInicio = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (gc.get(GregorianCalendar.MONTH)+1) + "/" + gc.get(GregorianCalendar.YEAR);
 gc.setTime(curso.getfFin());
 fechaFin = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (gc.get(GregorianCalendar.MONTH)+1) + "/" + gc.get(GregorianCalendar.YEAR);
%>
<main>
<header><h2><%=titulo%></h2></header>

<form action="<%=Constantes.SERVLET_CURSO %>" method="post">
     <input type="hidden" id="<%=Constantes.PAR_CODIGO%>" name="<%=Constantes.PAR_CODIGO%>" value="<%=curso.getCodigo()%>">
     <div>
       <label for="<%=Constantes.PAR_NOMBRE%>">Nombre:</label>
       <input type="text" placeholder="Introduzca aqui el nombre del curso.." id="<%=Constantes.PAR_NOMBRE %>" name="<%=Constantes.PAR_NOMBRE%>" value="<%=curso.getNombre()%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_FECHA_INI %>">Fecha Inicio Curso:</label>
      <input type="text" placeholder="Introduzca aqui la fecha de inicio.." id="<%=Constantes.PAR_FECHA_INI %>" name="<%=Constantes.PAR_FECHA_INI%>" value="<%=fechaInicio%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_FECHA_FIN %>">Fecha Fin Curso:</label>
      <input type="text" placeholder="Introduzca aqui la fecha de fin.." id="<%=Constantes.PAR_FECHA_FIN %>" name="<%=Constantes.PAR_FECHA_FIN%>" value="<%=fechaFin%>">
    </div>
    <div>
      <label for="<%=Constantes.PAR_DURACION %>">Duracion Curso:</label>
      <input type="number" placeholder="Introduzca aqui la duracion en horas.." id="<%=Constantes.PAR_DURACION %>" name="<%=Constantes.PAR_DURACION%>" value="<%=curso.getDuracion()%>">
    </div>

    <input type="submit" value="<%=titulo_boton%>"/>
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>