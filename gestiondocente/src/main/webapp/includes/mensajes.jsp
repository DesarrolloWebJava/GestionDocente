<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* Se comprueba si el atributo ATT_MENSAJES es distinto de nulo,
   en señal de haberse producido un error.*/
if(request.getAttribute(Constantes.ATT_MENSAJES) != null){
	/* Se guarda el mensaje de error.*/
	String mensaje = (String) request.getAttribute(Constantes.ATT_MENSAJES);
%>
	<div>
	<%=mensaje%>	
	</div>
	<%}%>