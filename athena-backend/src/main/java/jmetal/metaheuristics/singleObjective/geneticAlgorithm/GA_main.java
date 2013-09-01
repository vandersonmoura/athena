/**
 * GA_main.java
 * 
 * @author Antonio J. Nebro
 * @version 1.0
 */

package jmetal.metaheuristics.singleObjective.geneticAlgorithm;

import jmetal.base.Algorithm;
import jmetal.base.Operator;
import jmetal.base.Problem;
import jmetal.base.SolutionSet;
import jmetal.base.operator.crossover.CrossoverFactory;
import jmetal.base.operator.mutation.MutationFactory;
import jmetal.base.operator.selection.SelectionFactory;
import jmetal.problems.pedroalmir.StringProblem;
import jmetal.util.JMException;

/**
 * This class runs a single-objective genetic algorithm (GA). The GA can be
 * a steady-state GA (class ssGA), a generational GA (class gGA), a synchronous
 * cGA (class scGA) or an asynchronous cGA (class acGA). The OneMax
 * problem is used to test the algorithms.
 */
public class GA_main {
	
	public static void main(String[] args) {
		String candidates = "áÁãÃâÂõÕôÔóÓéêÉÊíQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm ";
		try {
			GA_main.execute(9, candidates.toCharArray(), "Olá Mundo");
		} catch (JMException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void execute(int stringLength, char[] candidates, String goal) throws JMException, ClassNotFoundException {
		Problem problem; // The problem to solve
		Algorithm algorithm; // The algorithm to use
		Operator crossover; // Crossover operator
		Operator mutation; // Mutation operator
		Operator selection; // Selection operator

		problem = new StringProblem(candidates, goal);

		// problem = new Sphere("Real", 10) ;
		// problem = new Easom("Real") ;
		// problem = new Griewank("Real", 10) ;

		algorithm = new gGA(problem); // Generational GA
		// algorithm = new ssGA(problem); // Steady-state GA
		// algorithm = new scGA(problem) ; // Synchronous cGA
		// algorithm = new acGA(problem) ; // Asynchronous cGA

		/* Algorithm parameters */
		algorithm.setInputParameter("populationSize", 512);
		algorithm.setInputParameter("maxEvaluations", 25000);
		/*
		 * // Mutation and Crossover for Real codification
		 * parameters = new HashMap() ;
		 * parameters.put("probability", 0.9) ;
		 * parameters.put("distributionIndex", 20.0) ;
		 * crossover = CrossoverFactory.getCrossoverOperator("SBXCrossover", parameters);
		 * parameters = new HashMap() ;
		 * parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
		 * parameters.put("distributionIndex", 20.0) ;
		 * mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);
		 */

		/* Mutation and Crossover for Real codification */
		crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover");
		crossover.setParameter("probability", 0.6);

		mutation = MutationFactory.getMutationOperator("BitFlipMutation");
		mutation.setParameter("probability", 0.1);

		/* Selection Operator */
		selection = SelectionFactory.getSelectionOperator("BinaryTournament");

		/* Add the operators to the algorithm */
		algorithm.addOperator("crossover", crossover);
		algorithm.addOperator("mutation", mutation);
		algorithm.addOperator("selection", selection);

		/* Execute the Algorithm */
		long initTime = System.currentTimeMillis();
		SolutionSet population = algorithm.execute();
		long estimatedTime = System.currentTimeMillis() - initTime;
		System.out.println("Total execution time: " + estimatedTime);

		/* Log messages */
		System.out.println("Objectives values have been writen to file FUN");
		population.printObjectivesToFile("FUN");
		System.out.println("Variables values have been writen to file VAR");
		population.printVariablesToFile("VAR", candidates, goal.length());
	} // main
} // GA_main

