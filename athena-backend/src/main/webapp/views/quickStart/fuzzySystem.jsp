<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<body>
	<!-- Jumbotron -->
	<div class="jumbotron">
	
	</div>
	
	<div class="body-content">
		<h2>Fuzzy Inference System: Service and Food x Tip</h2>
		<hr/>
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-lg-5">
				<div class="panel panel-primary">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">General Infomations</h3>
					</div>
					<div class="panel-body">
					  <p><strong>Create By</strong>: Pedro Almir</p>
					  
					  <p><strong>Created</strong>: August 22, 2013</p>
					  
					  <p><strong>Summary</strong>: This is a simple java code used to load a fuzzy inference system (FIS), 
					  this code available at JFuzzyLogic.</p>
					</div>
				</div>
				
				<div class="panel panel-primary" style="height: 208px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">System Description</h3>
					</div>
					<div class="panel-body text-justified">
					  
					  <p></p>
					  
					</div>
				</div>
			
			</div>
			<div class="col-lg-7">
				<div class="panel panel-primary" style="height: 400px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">System Design</h3>
					</div>
					<div class="panel-body">
						<p><a href="#">
							<img src="${pageContext.request.contextPath}/img/systems/1.jpg" alt="" class="fun_image" style="width:94%;">
						</a></p>
						
						<p style="margin: 10px 0px 0px 0px; text-align: right;">
					  	<a href="#" class="btn btn-primary">
					  		<span class="glyphicon glyphicon-wrench"></span>
					  		Open in Editor
					  	</a>
					  	<a href="#" class="btn btn-default">
					  		<span class="glyphicon glyphicon-print"></span>
					  		Print/Export
					  	</a>
					  	</p>
					</div>
				</div>
			</div>
		</div>
		<hr style="border-bottom: 1px solid #aaa; margin: 15px 0 35px 0px;">
		<div class="row">
			<div class="col-lg-5">
				<div class="panel panel-primary" style="height: 420px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">Execute Fuzzy System</h3>
					</div>
					<div class="panel-body">
						<form action="${pageContext.request.contextPath}/editor/tipperFuzzySystem" method="post" enctype="multipart/form-data">
						  
						  <div class="panel panel-default" style="padding: 8px; background-color: #E7E7E7; border-color: #A4C6E4;">
						  	<div class="panel-body text-center" style="font-weight: bold;">Fuzzy: Calculate how much to tip a waiter</div>
						  </div>
						  
						   <div class="form-group">
						    <label for="exampleInputEmail1">Quality of service</label>
						    <input type="text" class="form-control" id="service" name="service" placeholder="Enter the quality of service">
						  </div>
						  
						   <div class="form-group">
						    <label for="exampleInputEmail1">Quality of food</label>
						    <input type="text" class="form-control" id="food" name="food" placeholder="Enter the quality of food">
						  </div>
						  
						  <div class="form-group">
						  	<div>
						    	<label for="exampleInputFile">FCL Setting File</label>
						    </div>
						    
						    <input type="file" id="exampleInputFile" name="fclFile" class="btn btn-default" title="Search for a file to add">
						    <p class="help-block">Choose a FCL File to configure the Fuzzy Algorithm.</p>
						  </div>
						  <button type="submit" class="btn btn-default">Submit</button>
						</form>
					</div>
				</div>
			</div>
			
			<div class="col-lg-7">
				<div class="panel panel-primary" style="height: 420px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">Results of Execution</h3>
					</div>
					<div class="panel-body">
						<c:if test="${execution}">
							<div class="row">
								<div class="col-lg-6">
									<p><strong>Executed at: </strong> ${executionDate}</p>
									<p><strong>Execution Time: </strong>${executionTime}</p>
									<p><strong>Result: </strong>${tip}</p>
								</div>
								<div class="col-lg-6">
									<p class="text-center">
										<img alt="" src="${pageContext.request.contextPath}/img/food.png" style="width: 100%">
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<p class="text-center">
										<img alt="" src="${pageContext.request.contextPath}/img/service.png" style="width: 100%">
									</p>
								</div>
								<div class="col-lg-6">
									<p class="text-center">
										<img alt="" src="${pageContext.request.contextPath}/img/tip.png" style="width: 100%">
									</p>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>