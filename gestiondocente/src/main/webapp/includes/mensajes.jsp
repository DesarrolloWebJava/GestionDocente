<%@page import="com.ipartek.formacion.controler.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//si recibo o no un mensaje
if (request.getAttribute(Constantes.ATT_MENSAJE) != null){
	//declarare la variable si recibo mensaje
	String mensaje= (String)request.getAttribute(Constantes.ATT_MENSAJE);

	
%>
<div class="alert alert-success">
<%=mensaje%>
</div>
<%} %>

