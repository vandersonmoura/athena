$(document).ready(function() {
	
	$('#linkIntro').on("click", function(event){
		$('#linkIntro').attr("class", "list-group-item active");
		$('#linkOverview').attr("class", "list-group-item");
		$('#linkArchitecture').attr("class", "list-group-item");
		$('#linkRelatedwork').attr("class", "list-group-item");
		$('#linkEvaluation').attr("class", "list-group-item");
		$('#linkConclusion').attr("class", "list-group-item");
		
		$('#introContent').attr("class", "panel panel-default");
		$('#overviewContent').attr("class", "panel panel-default hidden");
		$('#architectureContent').attr("class", "panel panel-default hidden");
		$('#relatedWorkContent').attr("class", "panel panel-default hidden");
		$('#evaluationContent').attr("class", "panel panel-default hidden");
		$('#conclusionContent').attr("class", "panel panel-default hidden");
	});
	
	$('#linkOverview').on("click", function(event){
		$('#linkIntro').attr("class", "list-group-item");
		$('#linkOverview').attr("class", "list-group-item active");
		$('#linkArchitecture').attr("class", "list-group-item");
		$('#linkRelatedwork').attr("class", "list-group-item");
		$('#linkEvaluation').attr("class", "list-group-item");
		$('#linkConclusion').attr("class", "list-group-item");
		
		$('#introContent').attr("class", "panel panel-default hidden");
		$('#overviewContent').attr("class", "panel panel-default");
		$('#architectureContent').attr("class", "panel panel-default hidden");
		$('#relatedWorkContent').attr("class", "panel panel-default hidden");
		$('#evaluationContent').attr("class", "panel panel-default hidden");
		$('#conclusionContent').attr("class", "panel panel-default hidden");
	});
	
	$('#linkArchitecture').on("click", function(event){
		$('#linkIntro').attr("class", "list-group-item");
		$('#linkOverview').attr("class", "list-group-item");
		$('#linkArchitecture').attr("class", "list-group-item active");
		$('#linkRelatedwork').attr("class", "list-group-item");
		$('#linkEvaluation').attr("class", "list-group-item");
		$('#linkConclusion').attr("class", "list-group-item");
		
		$('#introContent').attr("class", "panel panel-default hidden");
		$('#overviewContent').attr("class", "panel panel-default hidden");
		$('#architectureContent').attr("class", "panel panel-default");
		$('#relatedWorkContent').attr("class", "panel panel-default hidden");
		$('#evaluationContent').attr("class", "panel panel-default hidden");
		$('#conclusionContent').attr("class", "panel panel-default hidden");
	});
	
	$('#linkRelatedwork').on("click", function(event){
		$('#linkIntro').attr("class", "list-group-item");
		$('#linkOverview').attr("class", "list-group-item");
		$('#linkArchitecture').attr("class", "list-group-item");
		$('#linkRelatedwork').attr("class", "list-group-item active");
		$('#linkEvaluation').attr("class", "list-group-item");
		$('#linkConclusion').attr("class", "list-group-item");
		
		$('#introContent').attr("class", "panel panel-default hidden");
		$('#overviewContent').attr("class", "panel panel-default hidden");
		$('#architectureContent').attr("class", "panel panel-default hidden");
		$('#relatedWorkContent').attr("class", "panel panel-default");
		$('#evaluationContent').attr("class", "panel panel-default hidden");
		$('#conclusionContent').attr("class", "panel panel-default hidden");
	});
	
	$('#linkEvaluation').on("click", function(event){
		$('#linkIntro').attr("class", "list-group-item");
		$('#linkOverview').attr("class", "list-group-item");
		$('#linkArchitecture').attr("class", "list-group-item");
		$('#linkRelatedwork').attr("class", "list-group-item");
		$('#linkEvaluation').attr("class", "list-group-item active");
		$('#linkConclusion').attr("class", "list-group-item");
		
		$('#introContent').attr("class", "panel panel-default hidden");
		$('#overviewContent').attr("class", "panel panel-default hidden");
		$('#architectureContent').attr("class", "panel panel-default hidden");
		$('#relatedWorkContent').attr("class", "panel panel-default hidden");
		$('#evaluationContent').attr("class", "panel panel-default");
		$('#conclusionContent').attr("class", "panel panel-default hidden");
	});
	
	$('#linkConclusion').on("click", function(event){
		$('#linkIntro').attr("class", "list-group-item");
		$('#linkOverview').attr("class", "list-group-item");
		$('#linkArchitecture').attr("class", "list-group-item");
		$('#linkRelatedwork').attr("class", "list-group-item");
		$('#linkEvaluation').attr("class", "list-group-item");
		$('#linkConclusion').attr("class", "list-group-item active");
		
		$('#introContent').attr("class", "panel panel-default hidden");
		$('#overviewContent').attr("class", "panel panel-default hidden");
		$('#architectureContent').attr("class", "panel panel-default hidden");
		$('#relatedWorkContent').attr("class", "panel panel-default hidden");
		$('#evaluationContent').attr("class", "panel panel-default hidden");
		$('#conclusionContent').attr("class", "panel panel-default");
	});
	
	
});