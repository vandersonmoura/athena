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
		<h2>Fuzzy System</h2>
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
					  
					  <p><strong>Summary</strong>: This system presents a hybrid approach based on Mamdani Fuzzy Inference Systems 
					  to solve the agile team allocation problem.</p>
					</div>
				</div>
				
				<div class="panel panel-primary">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">System Description</h3>
					</div>
					<div class="panel-body text-justified">
					  
					  <p>The success of the team allocation in a agile software development project is essential. 
					  The agile team allocation is a NP-hard problem, since it comprises the allocation of selforganizing 
					  and cross-functional teams. Many researchers have driven efforts to apply Computational Intelligence 
					  techniques to solve this problem. This system presents a hybrid approach based on Mamdani Fuzzy Inference Systems 
					  to solve the agile team allocation problem.</p>
					  
					</div>
				</div>
			
			</div>
			<div class="col-lg-7">
				<div class="panel panel-primary" style="height: 425px;">
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
				<div class="panel panel-primary" style="height: 410px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">Execute Fuzzy System</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="${pageContext.request.contextPath}/editor/executeFuzzySystem" method="post" enctype="multipart/form-data">
						  
						  <div class="panel panel-default" style="padding: 8px; background-color: #E7E7E7; border-color: #A4C6E4;">
						  	<div class="panel-body text-center" style="font-weight: bold;">FromCSV Data</div>
						  </div>
						  
						  <div class="form-group">
						  	<div>
						    	<label for="exampleInputFile">CSV Input File</label>
						    </div>
						    <input type="file" id="exampleInputFile" name="csvFile" class="btn btn-default" title="Search for a file to add">
						    
						    <p class="help-block">Choose a CSV File with developers informations.</p>
						  </div>
						  
						  <div class="panel panel-default" style="padding: 8px; background-color: #E7E7E7; border-color: #A4C6E4;">
						  	<div class="panel-body text-center" style="font-weight: bold;">Fuzzy: Estimation of developers productivity</div>
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
				<div class="panel panel-primary" style="height: 410px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">Results of Execution</h3>
					</div>
					<div class="panel-body">
						<c:if test="${execution}">
							<div class="row">
								<div class="col-lg-2"></div>
								<div class="col-lg-8">
									<dl class="dl-horizontal">
										<p>
										<dt>Executed at:</dt>
										<dd>${executionDate}</dd>
										</p><p>
										<dt>Number of Iterations:</dt>
										<dd>${iterations}</dd>
										</p><p>
										<dt>Execution Time :</dt>
										<dd>${executionTime}</dd>
										</p><p>
										<dt>Result File:</dt>
										<dd><a href="${linkToFile}" class="">Download Result File</a></dd>
										</p>
									</dl>
								</div>
								<div class="col-lg-2"></div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>