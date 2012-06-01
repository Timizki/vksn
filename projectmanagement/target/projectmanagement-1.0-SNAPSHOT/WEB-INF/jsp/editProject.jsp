<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
		<form:form commandName="project">
		<div id="l">	
				<h4>Tehtävät:</h4>
				<c:choose>
					<c:when test="${not empty project.tasks}">
						<c:forEach var="task" items="${project.tasks}">
							<h4><a href="editTask.do?id=${task.id}&projectId=${project.id}"><c:out value="${task.name}" /></a></a></h4>
							<c:out value="${task.description}" /><br/>
							<c:out value="${task.state}" />&nbsp; Kesto: <c:out value="${task.stringDuration}" />
							<h5>Tekijä: </h5>
							<c:out value="${task.actor.fullName}" /><br/>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p>Projektissa ei ole tehtäviä <br/>	
						Projekti täytyy tallentaa ennen kuin siihen voi lisätä tehtäviä.</p>
					</c:otherwise>
				</c:choose>
				<input type="submit" value="Tallenna" />
				<form>
					 <input type="button" value="Peruuta" onClick="parent.location='/showProject.do?id=${project.id}'">
				</form>
	
		</div>
		<div id="r">
				<h2>Projektin nimi:</h2>
				<form:errors path="name" />
				<form:input path="name" />
				<h4>Projektin kuvaus:</h4>
				<form:errors path="description" />
				<form:textarea cssStyle="projectDescriptionArea" path="description" />
				<h4>projektin tila:</h4>
				<p><img src="/images/states/state_<c:out value="${project.stateMedian}"/>.png" alt="${project.stateMedian}"/></p>
				<h4>deadline:</h4>
				<form:errors path="deadline" />
				<form:input path="deadline" />
				<h4>Projektin jäsenet</h4>
				<pm:getPersons var="persons" />
				<c:choose>
					<c:when test="${not empty persons}" >
						<form:checkboxes path="actors" items="${persons}" itemLabel="fullName" delimiter="<br />"/>
					</c:when>
					<c:otherwise>
						Järjestelmässä ei ole käyttäjiä.
					</c:otherwise>
				</c:choose>
				<p>Arvoitu tila: <c:out value="${project.stateMedian}"/></p>
				<p>Arvioitu kokonaiskesto: <c:out value="${project.totalDuration}"/></p>
	
		</div>
		</form:form>
<%@ include file="footer.jsp" %>