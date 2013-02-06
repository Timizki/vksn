<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>	
		<title>
			Ulkoasun lisäys
		</title>
	</head>
	<body>
		<div id="definitionForm">		
			<form:form commandName="definition">
				<form:input path="name"/>
				<form:input path="extends" />
				<form:select path="templateAttribute">
					<form:option value="">No template</form:option>
					<form:option value="16">Template</form:option>					
				</form:select>
			</form:form>
		</div>
	</body>
</html>