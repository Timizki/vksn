<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
	<div id="l">
		<form:form commandName="task" >
			<input type="hidden" value="${param['projectId']}" name="projectId" id="projectId" />
			<p>Tehtävän nimi: <form:errors path="name"/></p>
			<form:input path="name" />
			<p>Tehtävän kuvaus:</p>
			<form:textarea path="description"/>
			<p>Tehtävän tila: <form:errors path="state"/></p>
			<form:input path="state"/><br/>
			<p>Kesto: </p>
			<form:input path="hourDuration"/>h <form:input path="minDuration"/>min<br/>
			<p>Tekijä: </p>
		<!--	Tagi joka hakee kaikki person-oliot tietokannasta	-->
			<pm:getPersons var="persons" />
			<c:choose>
				<c:when test="${empty task.actor}">
					<form:select path="actor">
						<form:option value="null">Valitse tekijä</form:option> 
						<form:options items="${persons}" itemLabel="fullName" itemValue="id"/>
					</form:select>
				</c:when>
				<c:otherwise>
					<form:select path="actor.id">
						<form:option value="null">Valitse tekijä</form:option> 
						<form:options items="${persons}" itemLabel="fullName" itemValue="id"/>
					</form:select>
				</c:otherwise>
			</c:choose>
		
			<input type="hidden" name="projectId" value="${param['projectId']}" /><br/><br/>
			<input type="submit" value="Tallenna" />	
				<form>
					 <input type="button" value="Peruuta" onClick="parent.location='/showProject.do?id=${param['projectId']}'">
				</form>
		</form:form>

	</div>
	<div id="r">
		<pm:getProject var="project" id="${param['projectId']}"/>
			<h2><a href="editProject.do?id=${project.id}"><c:out value="${project.name}" /></a></h2>
				<p><c:out value="${project.description}" /></p>
			<h4>Tila:</h4>		
			<p><img src="/images/states/state_<c:out value="${project.stateMedian}"/>.png" alt="${project.stateMedian}"/></p>
			<p>Kokonaiskesto: ${project.totalDuration}</p>
			<p>Deadline:  <fmt:formatDate pattern="dd.MM.yyyy" value="${project.deadline}"/></p>
			<h4>Projektin jäsenet:</h4>
			<c:forEach var="actor" items="${project.actors}">
				<c:out value="${actor.fullName}" /><br/>
			</c:forEach>
	</div>
<%@ include file="footer.jsp" %> 