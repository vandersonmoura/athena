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
		<h2>Team Allocation System</h2>
		<hr/>
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-lg-5">
				<div class="panel panel-primary">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">General Infomations</h3>
					</div>
					<div class="panel-body">
					  <p><strong>Create By</strong>: Britto et al. <a href="http://ieeexplore.ieee.org/xpl/login.jsp?tp=&arnumber=6252999&url=http%3A%2F%2Fieeexplore.ieee.org%2Fxpls%2Fabs_all.jsp%3Farnumber%3D6252999" target="_blank">[4]</a></p>
					  
					  <p><strong>Created</strong>: August 24, 2013</p>
					  
					  <p><strong>Summary</strong>: This system presents a hybrid approach to solve the agile team allocation problem.</p>
					</div>
				</div>
				
				<div class="panel panel-primary">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">System Description</h3>
					</div>
					<div class="panel-body text-justified">
					  
					  <p>The success of the team allocation in a agile software
					  development project is essential. The agile team allocation is a NP-hard problem, 
					  since it comprises the allocation of selforganizing and cross-functional teams. Many researchers have
					  driven efforts to apply Computational Intelligence techniques to solve this problem. This work presents 
					  a hybrid approach based on NSGA-II multi-objective metaheuristic and Mamdani Fuzzy Inference Systems to 
					  solve the agile team allocation problem, together with an initial evaluation of its use in a real environment.
					  </p>
					  
					</div>
				</div>
			
			</div>
			<div class="col-lg-7">
				<div class="panel panel-primary" style="height: 445px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">System Design</h3>
					</div>
					<div class="panel-body">
						<p><a href="#">
							<img src="${pageContext.request.contextPath}/img/systems/teamAllocation.jpg" alt="" class="fun_image" style="width:94%;">
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
				<div class="panel panel-primary" style="">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">Execute Team Allocation System</h3>
					</div>
					<div class="panel-body">
						<form class="clear-pull" action="${pageContext.request.contextPath}/editor/executeTeamAllocationSystem" method="post" enctype="multipart/form-data">
						  <div class="panel panel-default" style="padding: 8px; background-color: #E7E7E7; border-color: #A4C6E4;">
						  	<div class="panel-body text-center" style="font-weight: bold;">FromCSV Data</div>
						  </div>
						  <div class="form-group">
						  	<div>
						    	<label for="exampleInputFile">CSV Input File</label>
						    </div>
						    
						    <input type="file" id="csvFile" name="csvFile" class="btn btn-default" title="Search for a file to add">
						    
						    <p class="help-block">Choose a CSV File with developers informations.</p>
						  </div>
						  
						  <div class="panel panel-default" style="padding: 8px; background-color: #E7E7E7; border-color: #A4C6E4;">
						  	<div class="panel-body text-center" style="font-weight: bold;">Fuzzy: Estimation of developers productivity</div>
						  </div>
						  
						  <div class="form-group">
						  	<div>
						    	<label for="exampleInputFile">FCL Setting File to First Fuzzy</label>
						    </div>
						    <input type="file" id="fclFileIn" name="fclFileIn" class="btn btn-default" title="Search for a file to add">
						    <p class="help-block">Choose a FCL File to configure the First Fuzzy Module.</p>
						  </div>
						  
						  <div class="panel panel-default" style="padding: 8px; background-color: #E7E7E7; border-color: #A4C6E4;">
						  	<div class="panel-body text-center" style="font-weight: bold;">NSGA-II: Generation of Teams</div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">Population Size</label>
						    <input type="text" class="form-control" id="populationSize" name="populationSize" placeholder="Enter the population size">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">Maximum Evaluations</label>
						    <input type="text" class="form-control" id="maxEvaluations" name="maxEvaluations" placeholder="Enter the maximum number of evaluations">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">Team size</label>
						    <input type="text" class="form-control" id="tamanhoDaEquipe" name="tamanhoDaEquipe" placeholder="Enter the team size">
						  </div>
						  
						  <!-- div class="form-group">
						    <label for="exampleInputEmail1">Crossover Operator</label>
						    <select class="form-control">
						    	<option>Single Point Crossover</option>
						    </select>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">Mutation Operator</label>
						    <select class="form-control">
						    	<option>Bit Flip Mutation</option>
						    </select>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">Selection Operator</label>
						    <select class="form-control">
						    	<option>Binary Tournament</option>
						    </select>
						  </div-->
						  
						  <div class="panel panel-default" style="padding: 8px; background-color: #E7E7E7; border-color: #A4C6E4;">
						  	<div class="panel-body text-center" style="font-weight: bold;">Fuzzy: Evaluation of the generated solutions</div>
						  </div>
						  
						  <div class="form-group">
						  	<div>
						    	<label for="exampleInputFile">FCL Setting File to Last Fuzzy</label>
						    </div>
						    <input type="file" id="fclFileOut" name="fclFileOut" class="btn btn-default" title="Search for a file to add">
						    <p class="help-block">Choose a FCL File to configure the Last Fuzzy Module.</p>
						  </div>
						  
						  <button type="submit" class="btn btn-primary pull-right" data-loading-text="Running...">Submit</button>
						</form>
					</div>
				</div>
			</div>
			
			<div class="col-lg-7">
				<div class="panel panel-primary" style="height: 270px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">Results of Execution</h3>
					</div>
					<div class="panel-body">
						<c:if test="${execution}">
							<div class="row">
								<div class="col-lg-6" style="text-align: right;">
									<p><strong>Executed at:</strong></p>
									<p><strong>Fuzzy: Developers productivity:</strong></p>
									<p><strong>NSGA-II: Generation of Teams:</strong></p>
									<p><strong>Fuzzy: Evaluation of the solutions:</strong></p>
									<!-- p><strong>Total Execution Time:</strong></p>
									<p><strong>Total Request Time:</strong></p>
									<p><strong>Result File:</strong></p-->
								</div>
								<div class="col-lg-6" style="text-align: left;">
									<p>${executionDate}</p>
									<p>${executionTimeFuzzyI}</p>
									<p>${executionTimeNSGA}</p>
									<p>${executionTimeFuzzyII}</p>
									<!--p>${totalExecutionTime}</p>
									<p>${requestTime}</p -->
									<p><a href="${linkToFile}" class="btn btn-success">Download Result File</a></p>
								</div>
							</div>
						</c:if>
					</div>
				</div>
				<!-- div class="panel panel-primary" style="height: 270px;">
					<div class="panel-heading thin-header">
						<h3 class="panel-title">Results of Execution</h3>
					</div>
					<div class="panel-body">
					</div>
				</div-->
			</div>
		</div>
	</div>
</body>