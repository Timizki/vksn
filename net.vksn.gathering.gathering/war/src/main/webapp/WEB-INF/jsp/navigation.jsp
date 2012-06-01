<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<ul>
	<li>		
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
				<a href="<c:url value="/event/browse.do"/>">Tapahtumat</a>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ADMIN">
				<a href="<c:url value="/browse.do"/>">Tapahtumat</a>
		</sec:authorize>
	</li>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<li>
			<a href="<c:url value="/event/edit.do"/>">Uusi tapahtuma</a>
		</li>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<li>
			<a href="<c:url value="/user/edit.do"/>">Uusi käyttäjä</a>
		</li>
	</sec:authorize>
	<li>
		<a href="<c:url value="/logout.html"/>" />Kirjaudu ulos</a>
	</li>
</ul>
