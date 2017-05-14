<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table width="100%">
	<s:iterator var="oi" value="list">
	<tr>
		<td><img width="40" height="45" src="${ pageContext.request.contextPath }/<s:property value="#oi.product.image"/>"></td>
		<td><s:property value="#oi.product.pname"/></td>
		<td><s:property value="#oi.count"/></td>
		<td><s:property value="#oi.subtotal"/></td>
	</tr>
	</s:iterator>
</table>