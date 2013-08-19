<head>
<!-- Custom styles for this template -->
<link href="css/justified-nav.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
	<div class="container">

		<div class="masthead">
			<h3 class="text-muted">Athena Service (Back-end)</h3>
			<ul class="nav navbar-nav nav-justified">
				<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/services">Services</a></li>
				<li><a href="${pageContext.request.contextPath}/documentation">Documentation</a></li>
				<li><a href="${pageContext.request.contextPath}/downloads">Downloads</a></li>
				<li><a href="${pageContext.request.contextPath}/about">About</a></li>
				<li><a href="mailto:contato@pedroalmir.com">Contact</a></li>
			</ul>
		</div>

		<!-- Jumbotron -->
		<div class="jumbotron">
			<h2 style="font-size: 50px; margin-top: 50px;">
				Build and test computational systems <br> right in your
				browser.
			</h2>
			<p class="lead" style="margin: 25px 0;">Athena Service is a new computational system simulation and analysis tool that provides 
			valuable information to users who design and operate hybrid systems for computational intelligence. It 
			incorporates the most advanced modeling techniques, with high-performance algorithms to deliver the best 
			in end-use modeling.</p>
			<p>
				<a class="btn btn-large btn-success" href="${pageContext.request.contextPath}/documentation">Get started now</a>
			</p>
		</div>


		<div class="body-content">

			<!-- Example row of columns -->
			<div class="row">
				<div class="col-lg-4 text-center">
					<a href="${pageContext.request.contextPath}/about">
						<img class="img-rounded" data-src="holder.js/140x140" alt="140x140" src="img/HiRes.jpg" style="height: 142px;">
					</a>
					<h3>Computational Intelligence</h3>
					<p>Athena Service will allow the creation of hybrid computational intelligence systems, 
					in order to unite the different areas of Artificial Intelligence.</p>
					<p>
						<a class="btn btn-default" href="${pageContext.request.contextPath}/about">View details &raquo;</a>
					</p>
				</div>
				<div class="col-lg-4 text-center">
					<a href="${pageContext.request.contextPath}/about">
						<img class="img-rounded" data-src="holder.js/140x140" alt="140x140" src="img/free-icon-sets-in-psd-format48.jpg" style="width: 200px;">
					</a>
					<h3>Cloud Computing</h3>
					<p>Athena Service was designed as a service to be made available on the Web, 
					using the benefits of Cloud Computing.</p>
					<p>
						<a class="btn btn-default" href="${pageContext.request.contextPath}/about">View details &raquo;</a>
					</p>
				</div>
				<div class="col-lg-4 text-center">
					<a href="${pageContext.request.contextPath}/about">
						<img class="img-rounded" data-src="holder.js/140x140" alt="140x140" src="img/modular.jpg" style="height: 142px;">
					</a>
					<h3>Modularization</h3>
					<p>Users can create their own modules (algorithms) <br>to be incorporated 
					into the tool by <br>simple and practical way.</p>
					<p>
						<a class="btn btn-default" href="${pageContext.request.contextPath}/about">View details &raquo;</a>
					</p>
				</div>
			</div>

		</div>
		<!-- /.body-content -->
		</div>
		<!-- /container -->
	</div>
	<!-- Site footer -->
	<div id="footer">
		<div class="container">
        	<p class="text-muted credit">Developers: <a href="http://pedroalmir.com">Pedro Almir</a> and <a href="mailto:matheusmmcs@gmail.com">Matheus Campanhã</a>.</p>
      	</div>
<!-- 		<p>&copy; Company 2013</p> -->
	</div>
</body>