<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
	<div id="l">
		<h2>Projektit</h2><br/>
		<c:forEach var="project" items="${results}" >
			<div id="project">
		 		<a href="showProject.do?id=${project.id}"><c:out value="${project.name}"/></a> <a href="/deleteProject.do?projectId=${project.id}"><span class="delete">[Poista]</span></a><br/>
				<div id="projectDescription">
					${project.description}
				 </div>
			</div>
		</c:forEach>
	</div>
	<div id="r">
		<h2><a href="/editProject.do">Uusi projekti</a></h2>
		<h2><a href="/editPerson.do">Uusi käyttäjä</a></h2>
	</div>
<%@ include file="footer.jsp" %>