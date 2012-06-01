<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h3>Tapahtuman <c:out value="${event.name}"/> tiedot</h3>
<table>
	<thead></thead>
	<tbody>
		<tr>
			<td>Nimi:</td>
			<td><c:out value="${event.name}"/> <a href="<c:url value="/event/edit.do?eventId=${event.id}"/>">[Muokkaa]</a></td>
		</tr>
		<tr>
			<td>Kuvaus:</td>
			<td><c:out value="${event.description}"/></td>
		</tr>
		<tr>
			<td colspan="2">Kerrat:</td>
		</tr>
			<c:forEach var="instance" items="${event.instances}">
				<tr>
					<td colspan="2">
						<form action="/instance/delete.do" method="post">
							<fmt:formatDate pattern="dd.MM.yyyy" value="${instance.date}"/>
							<input type="hidden" value="${event.id}" name="eventId" />
							<input type="hidden" value="${instance.id}" name="instanceId" />
							<input type="submit" value="Poista" />
						</form>
					</td>
				</tr>
			</c:forEach>
	</tbody>
	<tfoot>
	</tfoot>
</table>
