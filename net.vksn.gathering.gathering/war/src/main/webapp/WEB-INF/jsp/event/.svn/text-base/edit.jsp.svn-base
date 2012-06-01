<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tags.tld" prefix="instanceHelper" %>
<h3>Tapahtuman <c:out value="${empty event.id ? 'luonti' : 'muokkaus'}"/></h3>
<table>
	<form:form commandName="form" >		
		<form:hidden path="event.id"/>
		<tr>
			<td>Nimi:</td>
			<td>
				<form:errors path="event.name" /><br/>
				<form:input path="event.name"/>
			</td>
		</tr>
		<tr>
			<td>Kuvaus:</td>
			<td><form:textarea path="event.description" cols="38" rows="10"/></td>
		</tr>
		<tr>
			<td>Aikaväli:</td>
			<td>
				<form:errors path="beginDate"/><br />
				<form:errors path="endDate"/><br />
				<form:input path="beginDate"/> - <form:input path="endDate"/>
			</td>
		</tr>
		<tr>
			<td>Omistaja:</td>
			<td>
				<form:select path="event.owner">
			        <c:forEach var="user" items="${users}">
			        	<form:option value="${user.id}"><c:out value="${user.username}"/></form:option>
			        </c:forEach>
			    </form:select>
			</td>
		</tr>
		<tr>
			<td>Toistuvuus</td>
			<td>
				<form:select path="interval">
			        <form:option value="ONCE">Kerran</form:option>
			        <form:option value="DAILY">Päivittäin</form:option>
			        <form:option value="WEEKLY">Viikoittain</form:option>
			        <form:option value="MONTHLY">Kuukausittain</form:option>
			        <form:option value="ONCE_IN_YEAR">Kerran vuodessa</form:option>
			    </form:select>
		    </td>
		</tr>
	<%--<form:input path="instances"/>  --%>
	<tr>
		<td colspan="2"><input type="submit" value="Tallenna"/></td>
	</tr>
	</form:form>
</table>