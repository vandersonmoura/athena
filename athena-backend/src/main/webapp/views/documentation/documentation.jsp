<body>
	<!-- Jumbotron -->
	<div class="jumbotron"></div>

	<div class="body-content">
		<div class="row">
			<div class="col-lg-4">
				<div class="list-group">
					<a id="linkIntro" href="#" class="list-group-item active">Introdução</a> 
					<a id="linkOverview" href="#" class="list-group-item">Visão Geral</a> 
					<a id="linkArchitecture" href="#" class="list-group-item">Arquitetura</a> 
					<a id="linkRelatedwork" href="#" class="list-group-item">Trabalhos Relacionados</a> 
					<a id="linkEvaluation" href="#" class="list-group-item">Avaliação Inicial</a>
					<a id="linkConclusion" href="#" class="list-group-item">Considerações Finais</a>
				</div>
			</div>
			<div class="col-lg-8">
				<div id="introContent" class="panel panel-default">
					<div class="panel-heading">Introdução</div>
					<div class="panel-body">
						<jsp:include page="intro.jsp"></jsp:include>
					</div>
				</div>
				<div id="overviewContent" class="panel panel-default hidden">
					<div class="panel-heading">Visão Geral</div>
					<div class="panel-body">
						<jsp:include page="overview.jsp"></jsp:include>
					</div>
				</div>
				<div id="architectureContent" class="panel panel-default hidden">
					<div class="panel-heading">Arquitetura</div>
					<div class="panel-body">
						<jsp:include page="architecture.jsp"></jsp:include>
					</div>
				</div>
				<div id="relatedWorkContent" class="panel panel-default hidden">
					<div class="panel-heading">Trabalhos Relacionados</div>
					<div class="panel-body">
						<jsp:include page="relatedWork.jsp"></jsp:include>
					</div>
				</div>
				<div id="evaluationContent" class="panel panel-default hidden">
					<div class="panel-heading">Avaliação Inicial</div>
					<div class="panel-body">
						<jsp:include page="evaluation.jsp"></jsp:include>
					</div>
				</div>
				<div id="conclusionContent" class="panel panel-default hidden">
					<div class="panel-heading">Considerações Finais</div>
					<div class="panel-body">
						<jsp:include page="conclusion.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>