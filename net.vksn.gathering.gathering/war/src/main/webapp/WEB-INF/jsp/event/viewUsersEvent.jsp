<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tags.tld" prefix="instanceHelper" %>

<h3>Tapahtuman <c:out value="${event.name}"/> tiedot</h3>
<fmt:setLocale value="${pageContext.request.locale}"/>
<table id="event">
	<thead></thead>
	<tbody>
		<tr>
			<th>Nimi:</th>
			<td><c:out value="${event.name}"/></td>
		</tr>
		<tr>
			<th>Tiedot:</th>
			<td><c:out value="${event.description}"/></td>
		</tr>
		<c:set var="instance" value="${instanceHelper:nextInstance(event)}"/>			
		<tr>
			<th colspan="2"><fmt:formatDate  pattern="EEEEEEE dd.MM.yyyy" value="${instance.date}"/></th>
		</tr>
		<tr>
			<%-- //TODO: Implement a tag for showing participants and participate form --%>
			<td colspan="2">
				<table class="participants">
					<thead></thead>
					<tbody>
						<c:forEach var="participant" items="${instance.participants}">						
							<tr>
								<form action="<c:url value="/remove.do"/>" method="post">
									<input type="hidden" value="${instance.id}" name="instanceId" />
									<input type="hidden" value="${participant.id}" name="participantId" />
									<input type="hidden" value="${event.id}" name="eventId" />
									<td class="name"><c:out value="${participant.name}"/></td>
									<td><input type="submit" value="Poista"/></td>
								</form>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<form action="<c:url value="/participate.do"/>" method="post">
									<input type="hidden" name="instanceId" value="${instance.id}" />
									<input type="text" value="" name="name"/>
									<input type="submit" value="Lisää"/>
								</form>
							</td>
						</tr>
					</tfoot>
				</table>
			</td>
		</tr>
		<c:set var="instance" value="${instanceHelper:nextInstanceFromDate(event, instance.date)}"/>
		<tr>
			<th colspan="2"><fmt:formatDate pattern="EEEEEEE dd.MM.yyyy" value="${instance.date}"/></th>
		</tr>
				<tr>
			<%-- //TODO: Implement a tag for showing participants and participate form --%>
			<td colspan="2">
				<table class="participants">
					<thead></thead>
					<tbody>
						<c:forEach var="participant" items="${instance.participants}">						
							<tr>
								<form action="<c:url value="/remove.do"/>" method="post">
									<input type="hidden" value="${instance.id}" name="instanceId" />
									<input type="hidden" value="${participant.id}" name="participantId" />
									<input type="hidden" value="${event.id}" name="eventId" />
									<td class="name"><c:out value="${participant.name}"/></td>
									<td><input type="submit" value="Poista"/></td>
								</form>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<form action="<c:url value="/participate.do"/>" method="post">
									<input type="hidden" name="instanceId" value="${instance.id}" />
									<input type="text" value="" name="name"/>
									<input type="submit" value="Lisää"/>
								</form>
							</td>
						</tr>
					</tfoot>
				</table>
			</td>
		</tr>
	</tbody>
	<tfoot></tfoot>
</table>
