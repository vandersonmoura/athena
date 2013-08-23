<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Pedro Almir">
	<meta name="description" content="Athena Back-end">
	
	<title>Athena Back-end</title>
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/bootstrap-glyphicons.css" rel="stylesheet" media="screen">
    <link rel="shortcut icon" href="img/2640692.png" type="image/png" />
    <!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/css/justified-nav.css" rel="stylesheet">
	
    <decorator:head />
    
</head>
<body>
	
	<div id="wrap">
		<div class="container">

			<div class="masthead">
				<h3 class="text-muted">Athena Service (Back-end)</h3>
				<ul class="nav navbar-nav nav-justified">
					<li id="homeLI" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li id="quickStartLI"><a href="${pageContext.request.contextPath}/quick-start">Quick-Start</a></li>
					<li id="documentationLI"><a href="${pageContext.request.contextPath}/documentation">Documentation</a></li>
					<li id="downloadsLI"><a href="${pageContext.request.contextPath}/downloads">Downloads</a></li>
					<li id="aboutLI"><a href="${pageContext.request.contextPath}/about">About</a></li>
					<li id="contactLI"><a href="mailto:contato@pedroalmir.com">Contact</a></li>
				</ul>
			</div>
			
			<decorator:body />

		</div>
		<!-- /container -->
	</div>
	<!-- Site footer -->
	<div id="footer">
		<div class="container">
        	<p class="text-muted credit">Developers: <a href="http://pedroalmir.com">Pedro Almir</a> and <a href="mailto:matheusmmcs@gmail.com">Matheus Campanhã</a>.</p>
      	</div>
	</div>
	
	
	<!-- JavaScript plugins (requires jQuery) -->
    <script src="http://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/athena-backend.js"></script>
</body>
</html>