<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(request.getAttribute(Constantes.ATT_MENSAJE)!=null){
	String mensaje = (String)request.getAttribute(Constantes.ATT_MENSAJE);
%>
<div>
	<%=mensaje %>
</div>

<%}%>
