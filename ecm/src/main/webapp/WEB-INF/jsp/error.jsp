<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>	
		<title>
			Hupsis, joku meni vikaan!
		</title>
		<link rel="stylesheet" type="text/css" href="/resources/errorScreen.css" />
	</head>
	<body>
		<div id="page">		
			<h1>Hups, joku meni vikaan</h1>
			<p>Virhejälki</p>
			<pre>
				${error}
			</pre>
		</div>
	</body>
</html>