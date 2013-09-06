/**
 * 
 */
package com.pedroalmir.athena.impl.tsp.module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jmetal.base.Operator;
import jmetal.base.Problem;
import jmetal.base.SolutionSet;
import jmetal.base.operator.crossover.CrossoverFactory;
import jmetal.base.operator.mutation.MutationFactory;
import jmetal.base.operator.selection.SelectionFactory;
import jmetal.metaheuristics.singleObjective.geneticAlgorithm.ssGA;
import jmetal.problems.singleObjective.TSP;
import jmetal.util.JMException;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.component.AbstractBundle;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Int;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.type.string.StringType;
import com.pedroalmir.athena.core.util.PutUtils;
import com.pedroalmir.athena.impl.tsp.configuration.TSPConfiguration;
import com.pedroalmir.athena.impl.tsp.solution.TSPSolution;

/**
 * @author Pedro Almir
 *
 */
public class TSPModule extends AbstractBundle implements GenericModule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6303466590064606317L;
	/**
	 * 
	 */
	private boolean loaded;
	
	public String getName() {
		return "TSP Module";
	}

	public String getShortName() {
		return "TSP";
	}

	public String getDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		/* TODO: Create a description */
		return buffer.toString();
	}

	public String getImagePath() {
		return "";
	}

	public List<Input> getInputs() {
		return this.inputs;
	}

	
	public List<Output> getOutputs() {
		return this.outputs;
	}

	
	public List<Setting> getSettings() {
		return this.settings;
	}

	@Override
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	@Override
	public Configuration getConfiguration() {
		return new TSPConfiguration();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(AthenaBundle bundle) {
		if (this == bundle)
			return true;
		if (bundle == null)
			return false;
		if (getClass() != bundle.getClass())
			return false;
		TSPModule other = (TSPModule) bundle;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputs == null) {
			if (other.inputs != null)
				return false;
		} else if (!inputs.equals(other.inputs))
			return false;
		if (outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!outputs.equals(other.outputs))
			return false;
		if (settings == null) {
			if (other.settings != null)
				return false;
		} else if (!settings.equals(other.settings))
			return false;
		return true;
	}

	@Override
	public Algorithm getAlgorithm() {
		return null;
	}

	@Override
	public boolean isOptimizationAlgorithm() {
		return true;
	}

	@Override
	public void load(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		this.executionLog.appendLogLine("Carregando módulo TSP...");
		this.inputs =  inputs;
		this.outputs = outputs;
		this.settings = settings;
		
		this.loaded = true;
		this.executionLog.appendLogLine("Módulo TSP carregado com sucesso...");
	}

	@Override
	public void load() {
		this.executionLog.appendLogLine("Carregando módulo TSP...");
		this.loaded = true;
		this.executionLog.appendLogLine("Módulo TSP carregado com sucesso...");
	}

	@Override
	public boolean isLoaded() {
		return this.loaded;
	}

	@Override
	public boolean isPublic() {
		return true;
	}

	@Override
	public List<Solution> run() {
		List<Solution> solutions = new LinkedList<Solution>();
		/* The problem to solve */
		Problem problem;
		try {
			this.executionLog.appendLogLine("Configurando problema...");
			problem = new TSP(((FileType) this.inputs.get(0).getValues().get(0)).getFilePath());
			
			/* The algorithm to use */
			jmetal.base.Algorithm algorithm = new ssGA(problem);
			// algorithm = new gGA(problem) ;

			// Algorithm params
			Setting populationSize = PutUtils.findSetting("population_size", settings);
			algorithm.setInputParameter("populationSize", ((Int) populationSize.getType()).getValue());
			
			Setting maximumEvaluations = PutUtils.findSetting("maximum_evaluations", settings);
			algorithm.setInputParameter("maxEvaluations", ((Int) maximumEvaluations.getType()).getValue());
			this.executionLog.setIterations((Integer) ((Int) maximumEvaluations.getType()).getValue());

			// Mutation and Crossover for Real codification */
			Setting crossoverOperator = PutUtils.findSetting("crossover_operator", settings);
			Operator crossover = CrossoverFactory.getCrossoverOperator(((StringType) crossoverOperator.getType()).getValue().toString());
			
			Setting probabilityOfCrossover = PutUtils.findSetting("probabilityOfCrossover", settings);
			crossover.setParameter("probability", ((Real) probabilityOfCrossover.getType()).getValue());
			
			Setting mutationOperator = PutUtils.findSetting("mutation_operator", settings);
			Operator mutation = MutationFactory.getMutationOperator(((StringType) mutationOperator.getType()).getValue().toString());
			
			Setting probabilityOfMutation = PutUtils.findSetting("probabilityOfMutation", settings);
			mutation.setParameter("probability", ((Real) probabilityOfMutation.getType()).getValue());

			/* Selection Operator */
			Setting selectionOperator = PutUtils.findSetting("selection_operator", settings);
			Operator selection = SelectionFactory.getSelectionOperator(((StringType) selectionOperator.getType()).getValue().toString());

			/* Add the operators to the algorithm */
			algorithm.addOperator("crossover", crossover);
			algorithm.addOperator("mutation", mutation);
			algorithm.addOperator("selection", selection);
			
			this.executionLog.appendLogLine("Início da execução do algoritmo...");
			/* Execute the Algorithm */
			long initTime = System.currentTimeMillis();
			SolutionSet population = algorithm.execute();
			long estimatedTime = System.currentTimeMillis() - initTime;
			System.out.println("Total time of execution: " + estimatedTime);
			this.executionLog.appendLogLine("Fim da execução do algoritmo...");
			
			TSPSolution solution = population.getFitnessValue();
			
			this.executionLog.appendLogLine("Melhor solução encontrada...");
			this.executionLog.appendLogLine("Fitness: " + solution.getFitnesss());
			this.executionLog.appendLogLine("Caminho: " + StringUtils.join(solution.getSolution(), ", "));
			
			solutions.add(solution);
			return solutions;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JMException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
