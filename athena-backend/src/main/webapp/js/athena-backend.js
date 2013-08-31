$(document).ready(function() {
	
	$('input[type=file]').bootstrapFileInput();
	$('.file-inputs').bootstrapFileInput();
	
	if(document.location.toString().indexOf("quick-start") != -1){
		$('#homeLI').attr("class", "");
		$('#quickStartLI').attr("class", "active");
		$('#documentationLI').attr("class", "");
		$('#downloadsLI').attr("class", "");
		$('#aboutLI').attr("class", "");
	}else if(document.location.toString().indexOf("documentation") != -1){
		$('#homeLI').attr("class", "");
		$('#quickStartLI').attr("class", "");
		$('#documentationLI').attr("class", "active");
		$('#downloadsLI').attr("class", "");
		$('#aboutLI').attr("class", "");
	}else if(document.location.toString().indexOf("downloads") != -1){
		$('#homeLI').attr("class", "");
		$('#quickStartLI').attr("class", "");
		$('#documentationLI').attr("class", "");
		$('#downloadsLI').attr("class", "active");
		$('#aboutLI').attr("class", "");
	}else if(document.location.toString().indexOf("about") != -1){
		$('#homeLI').attr("class", "");
		$('#quickStartLI').attr("class", "");
		$('#documentationLI').attr("class", "");
		$('#downloadsLI').attr("class", "");
		$('#aboutLI').attr("class", "active");
	}
	
});