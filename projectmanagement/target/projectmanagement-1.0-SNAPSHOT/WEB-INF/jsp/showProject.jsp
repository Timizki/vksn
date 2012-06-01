<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<fmt:setBundle basename="class.path.to.your.resources.bundle"/>

	<div id="l">
		<h2>Tehtävät<br/></h2>
		<c:forEach var="task" items="${project.tasks}" >
			<c:if test="${task.deleted == null}">
				<h4><a href="editTask.do?id=${task.id}&projectId=${project.id}"><c:out value="${task.name}" /></a>&nbsp;<a href="/deleteTask.do?projectId=${project.id}&taskId=${task.id}"><span class="delete">[Poista]</span></a></h4>
				<c:out value="${task.description}" /><br/>
				Tila: <c:out value="${task.state}" />&nbsp; Kesto: <c:out value="${task.stringDuration}" />
				<h5>Tekijä: </h5>
				<c:out value="${task.actor.fullName}" /><br/>
			</c:if>
		</c:forEach>
 		<div id="rightCorner">
			<a href="editTask.do?projectId=${project.id }">[Lisää tehtävä]</a>
		</div>
	</div>
	<div id="r">
			<h2><a href="editProject.do?id=${project.id}"><c:out value="${project.name}" /></a></h2>
				<p><c:out value="${project.description}" /></p>
			<h4>Tila:</h4>		
			<p><img src="/images/states/state_<c:out value="${project.stateMedian}"/>.png" alt="${project.stateMedian}"/></p>
			<p>Kokonaiskesto: ${project.totalDuration}"</p>
			<p>Deadline:  <fmt:formatDate pattern="dd.MM.yyyy" value="${project.deadline}"/></p>
			<h4>Projektin jäsenet:</h4>
			<c:forEach var="actor" items="${project.actors}">
				<c:out value="${actor.fullName}" /><br/>
			</c:forEach>
	</div>
<%@ include file="footer.jsp"%>