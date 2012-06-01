<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${param['errors'] == true}">
		<p style="color:red; text-align:center;">Kirjautuminen epäonnistui, joko käyttäjätunnus tai salasana on väärin.</p>
</c:if>
<form action="/j_spring_security_check" id="login" method="POST">
	<label for="j_username">Käyttäjätunnus</label><br />
	<input type="text" id="j_username" name="j_username"/><br />
	<label for="j_password">Salasana</label><br />
	<input type="password" id="j_password" name="j_password"><br />	
	<input type="submit" value="kirjaudu"/>
</form>