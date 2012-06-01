<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>	
		<title>
			<tiles:insertAttribute name="title" />
		</title>
	</head>
	<body>
		<div id="page">		
			<div id="header">
				<tiles:insertAttribute name="header"/>
			</div>
			<div id="menu">
				<tiles:insertAttribute name="menu"/>
			</div>
			<div id="body">
				<tiles:insertAttribute name="body"/>
			</div>
			<div id="footer">
				<tiles:insertAttribute name="footer"/>
			</div>		
		</div>
	</body>
</html