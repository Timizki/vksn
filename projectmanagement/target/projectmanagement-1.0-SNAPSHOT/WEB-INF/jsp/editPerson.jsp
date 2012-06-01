<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div id="l">
	<form:form commandName="person">
		<table border="0">
		<tr>
			<td>Etunimi:<form:errors path="firstname"/>	</td>
			<td>Sukunimi: <form:errors path="lastname"/></td>
		</tr>
		<tr>
			<td>
			<form:input path="firstname" />
			</td>
			<td><form:input path="lastname" /></td>
		</tr>
		<tr>
			<td>
			Käyttäjätunnus:
			</td>
			<td>
			Salasana:
			</td>
		</tr>
		<tr>
			<td><form:input path="username" /></td>
			<td><form:password path="password" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Tallenna" /><form>
					 <input type="button" value="Peruuta" onClick="parent.location='/listProjects.do'">
				</form></td>
		</tr>
		</table>
	</form:form>
</div>
<%@ include file="footer.jsp"%>