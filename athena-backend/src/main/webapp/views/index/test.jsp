<body>
	<form action="${pageContext.request.contextPath}/editor/uploadFile" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadedFile"/>
		<input type="submit" value="Enviar">
	</form>	
</body>