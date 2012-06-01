<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h3>Käyttäjän <c:out value="${empty user.id ? 'luonti' : 'muokkaus'}"/></h3>
<table>
	<form:form commandName="user" >		
		<form:hidden path="id"/>
		<tr>
			<td>Nimi:</td>
			<td><form:input path="username"/></td>
		</tr>
		<tr>
			<td>Salasana:</td>
			<td><form:password path="password" /></td>
		</tr>
<%--
		<tr>
			<td>Käytössä:</td>
			<td><form:checkbox path="enabled" /></td>
		</tr>
--%>
		<tr>
			<td>Roolit:</td>
			<td>
				<form:checkboxes items="${roles}" path="roles" itemLabel="authority" itemValue="authority" />
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Tallenna"/></td>
		</tr>
	</form:form>
</table>