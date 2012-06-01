<%@ page     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tags.tld" prefix="instanceHelper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="eventsContainer">
	<table>
		<thead>
			<th>Nimi</th>
			<th>Kuvaus</th>
			<th>Ensimmäinen kerta</th>
			<th>Viimeinen kerta</th>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(events) gt 0}">
					<c:forEach var="event" items="${events}">				
						<tr>
							<td><a href="<c:url value="/event/view.do?eventId=${event.id}"/>"><c:out value="${event.name}"/></a></td>
							<td><a href="<c:url value="/event/view.do?eventId=${event.id}"/>"><c:out value="${event.description}"/></a></td>
							<td><a href="<c:url value="/event/view.do?eventId=${event.id}"/>"><fmt:formatDate pattern="dd.MM.yyyy" value="${instanceHelper:firstInstance(event).date}"/></a></td>
							<td><a href="<c:url value="/event/view.do?eventId=${event.id}"/>"><fmt:formatDate pattern="dd.MM.yyyy" value="${instanceHelper:lastInstance(event).date}"/></a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">Ei tapahtumia</td>
					</tr>
				</c:otherwise>
			</c:choose>			
		</tbody>
		<tfoot>
			<th>Nimi</th>
			<th>Kuvaus</th>
			<th>Ensimmäinen kerta</th>
			<th>Viimeinen kerta</th>
		</tfoot>
	</table>
</div>