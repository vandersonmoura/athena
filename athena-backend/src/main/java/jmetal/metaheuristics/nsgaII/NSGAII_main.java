/**
 * NSGAII_main.java
 * 
 * @author Juan J. Durillo
 * @author Antonio J. Nebro
 * @version 1.0
 *          This implementation of NSGA-II makes use of a QualityIndicator object
 *          to obtained the convergence speed of the algorithm. This version is used
 *          in the paper:
 *          A.J. Nebro, J.J. Durillo, C.A. Coello Coello, F. Luna, E. Alba
 *          "A Study of Convergence Speed in Multi-Objective Metaheuristics."
 *          To be presented in: PPSN'08. Dortmund. September 2008.
 * 
 *          Besides the classic NSGA-II, a steady-state version (ssNSGAII) is also
 *          included (See: J.J. Durillo, A.J. Nebro, F. Luna and E. Alba
 *          "On the Effect of the Steady-State Selection Scheme in
 *          Multi-Objective Genetic Algorithms"
 *          5th International Conference, EMO 2009, pp: 183-197.
 *          April 2009)
 * 
 */
package jmetal.metaheuristics.nsgaII;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import jmetal.base.Algorithm;
import jmetal.base.Operator;
import jmetal.base.Problem;
import jmetal.base.SolutionSet;
import jmetal.base.operator.crossover.CrossoverFactory;
import jmetal.base.operator.mutation.MutationFactory;
import jmetal.base.operator.selection.SelectionFactory;
import jmetal.problems.TeamAllocation;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;

import com.pedroalmir.athena.impl.teamAllocation.model.Desenvolvedor;
import com.pedroalmir.athena.impl.teamAllocation.model.Equipe;
import com.pedroalmir.athena.impl.teamAllocation.model.NSGAIIResult;

public class NSGAII_main {
	/* Logger object */
	public static Logger logger_;
	/* FileHandler object */
	public static FileHandler fileHandler_;
	
	/**
	 * @param desenvolvedores
	 * @param tamanhoDaEquipe
	 * @param populationSize
	 * @param maxEvaluations
	 * @return
	 * @throws JMException
	 * @throws SecurityException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static NSGAIIResult execute(List<Desenvolvedor> desenvolvedores, int tamanhoDaEquipe, int populationSize, int maxEvaluations) 
			throws JMException, SecurityException, IOException, ClassNotFoundException {
		long begin = System.currentTimeMillis();
		/* The problem to solve */
		Problem problem = new TeamAllocation(desenvolvedores, tamanhoDaEquipe);
		Algorithm algorithm = new NSGAII(problem);
		
		/* Algorithm parameters */
		algorithm.setInputParameter("populationSize", populationSize);
		algorithm.setInputParameter("maxEvaluations", maxEvaluations);

		/* Mutation and Crossover for Real codification */
		Operator crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover");
		crossover.setParameter("probability", 0.9);
		crossover.setParameter("distributionIndex", 20.0);

		Operator mutation = MutationFactory.getMutationOperator("BitFlipMutation");
		mutation.setParameter("probability", 0.05);
		mutation.setParameter("distributionIndex", 20.0);

		/* Selection Operator */
		Operator selection = SelectionFactory.getSelectionOperator("BinaryTournament2");

		/* Add the operators to the algorithm */
		algorithm.addOperator("crossover", crossover);
		algorithm.addOperator("mutation", mutation);
		algorithm.addOperator("selection", selection);

		/* Execute the Algorithm */
		SolutionSet population = algorithm.execute();

		/* Result messages */
		//logger_.info("Total execution time: " + executionTime + "ms");
		//population.sort(new jmetal.base.operator.comparator.CrowdingDistanceComparator());
		
		List<Equipe> equipes = population.getListOfResults(desenvolvedores);
		Map<String, List<Double>> objetivos = population.getListObjetivos();
		long executionTime = System.currentTimeMillis() - begin;
		
		return new NSGAIIResult(equipes, executionTime, objetivos);
	}

	/**
	 * @throws JMException
	 * @throws IOException
	 * @throws SecurityException
	 */
	public static NSGAIIResult execute(List<Desenvolvedor> desenvolvedores, int tamanhoDaEquipe, String basePathNSGAII, boolean writeFiles,
			int populationSize, int maxEvaluations) 
			throws JMException, SecurityException, IOException, ClassNotFoundException {
		/* The problem to solve */
		Problem problem = null;
		/* The algorithm to use */
		Algorithm algorithm = null; 
		/* Crossover operator */
		Operator crossover = null; 
		/* Mutation operator */
		Operator mutation = null;
		/* Selection operator */ 
		Operator selection = null; 

		/* Object to get quality indicators */
		QualityIndicator indicators;

		/* Logger object and file to store log messages */
		if(writeFiles){
			logger_ = Configuration.logger_;
			fileHandler_ = new FileHandler("NSGAII_main.log");
			logger_.addHandler(fileHandler_);
		}

		indicators = null;
		problem = new TeamAllocation(desenvolvedores, tamanhoDaEquipe);
		algorithm = new NSGAII(problem);

		/* Algorithm parameters */
		algorithm.setInputParameter("populationSize", populationSize);
		algorithm.setInputParameter("maxEvaluations", maxEvaluations);

		/* Mutation and Crossover for Real codification */
		crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover");
		crossover.setParameter("probability", 0.9);
		crossover.setParameter("distributionIndex", 20.0);

		mutation = MutationFactory.getMutationOperator("BitFlipMutation");
		mutation.setParameter("probability", 0.05);
		mutation.setParameter("distributionIndex", 20.0);

		/* Selection Operator */
		selection = SelectionFactory.getSelectionOperator("BinaryTournament2");

		/* Add the operators to the algorithm */
		algorithm.addOperator("crossover", crossover);
		algorithm.addOperator("mutation", mutation);
		algorithm.addOperator("selection", selection);

		/* Add the indicator object to the algorithm */
		algorithm.setInputParameter("indicators", indicators);

		/* Execute the Algorithm */
		long begin = System.currentTimeMillis();
		SolutionSet population = algorithm.execute();
		long executionTime = System.currentTimeMillis() - begin;

		/* Result messages */
		//logger_.info("Total execution time: " + executionTime + "ms");
		population.sort(new jmetal.base.operator.comparator.CrowdingDistanceComparator());
		List<Equipe> equipes = null;
		Map<String, List<Double>> objetivos = null;
		
		if(writeFiles){
			//logger_.info("Variables values have been writen to file VAR");
			population.printVariablesToFileTallo(basePathNSGAII, "VAR");
			//logger_.info("Objectives values have been writen to file FUN");
			objetivos = population.printObjectivesToFileTallo(basePathNSGAII, "FUN");
			equipes = population.printResultsToFile(basePathNSGAII, "resultados/Resultados.csv", desenvolvedores);
		}else{
			equipes = population.getListOfResults(desenvolvedores);
			objetivos = population.getListObjetivos();
		}
		
		return new NSGAIIResult(equipes, executionTime, objetivos);
	}
	
}
