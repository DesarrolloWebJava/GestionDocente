<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
	if(request.getAttribute(Constantes.ATT_MENSAJE)!=null){
		String mensaje = (String)request.getAttribute(Constantes.ATT_MENSAJE);
	
	%>
<div>
<!-- el = se usa cuando va a una view -->
	<%=mensaje%>
</div>
<%}%>