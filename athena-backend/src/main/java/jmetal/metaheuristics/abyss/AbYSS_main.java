/**
 * AbYSS_main.java
 *
 * @author Juan J. Durillo
 * @version 1.0
 * 
 * This class executes the algorithm described in:
 *   A.J. Nebro, F. Luna, E. Alba, B. Dorronsoro, J.J. Durillo, A. Beham 
 *   "AbYSS: Adapting Scatter Search to Multiobjective Optimization." 
 *   IEEE Transactions on Evolutionary Computation. Vol. 12, 
 *   No. 4 (August 2008), pp. 439-457
 */
package jmetal.metaheuristics.abyss;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import jmetal.base.*;
import jmetal.base.operator.crossover.*;
import jmetal.base.operator.localSearch.MutationLocalSearch;
import jmetal.base.operator.mutation.*;
import jmetal.problems.*;
import jmetal.problems.DTLZ.*;
import jmetal.problems.LZ09.*;
import jmetal.problems.WFG.*;
import jmetal.problems.ZDT.*;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;
/**
 * This class is the main program used to configure and run AbYSS, a 
 * multiobjective scatter search metaheuristics.
 * Comments: AbYSS is configured to work only with continuous decision 
 *           variables.
 */
public class AbYSS_main {
  public static Logger      logger_ ;      // Logger object
  public static FileHandler fileHandler_ ; // FileHandler object
  
  /**
   * @param args Command line arguments.
   * @throws JMException 
   * @throws IOException 
   * @throws SecurityException 
   * Usage: three choices
   *      - jmetal.metaheuristics.nsgaII.NSGAII_main
   *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName
   *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName paretoFrontFile
   */
  public static void main(String [] args) throws 
                                 JMException, SecurityException, IOException, ClassNotFoundException {    
    Problem   problem     ; // The problem to solve
    Algorithm algorithm   ; // The algorithm to use
    Operator  crossover   ; // Crossover operator
    Operator  mutation    ; // Mutation operator
    Operator  improvement ; // Operator for improvement
            
    QualityIndicator indicators ; // Object to get quality indicators

    // Logger object and file to store log messages
    logger_      = Configuration.logger_ ;
    fileHandler_ = new FileHandler("AbYSS.log"); 
    logger_.addHandler(fileHandler_) ;
    
    indicators = null ;
    if (args.length == 1) {
      Object [] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0],params);
    } // if
    else if (args.length == 2) {
      Object [] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0],params);
      indicators = new QualityIndicator(problem, args[1]) ;
    } // if
    else { // Default problem
      problem = new Kursawe("Real", 3); 
      //problem = new Kursawe("BinaryReal", 3);
      //problem = new Water("Real");
      //problem = new ZDT1("ArrayReal", 100);
      //problem = new ConstrEx("Real");
      //problem = new DTLZ1("Real");
      //problem = new OKA2("Real") ;
    } // else
    
    // STEP 2. Select the algorithm (AbYSS)
    algorithm = new AbYSS(problem) ;
    
    // STEP 3. Set the input parameters required by the metaheuristic
    algorithm.setInputParameter("populationSize", 20);
    algorithm.setInputParameter("refSet1Size"   , 10);
    algorithm.setInputParameter("refSet2Size"   , 10);
    algorithm.setInputParameter("archiveSize"   , 100);
    algorithm.setInputParameter("maxEvaluations", 25000);
      
    // STEP 4. Specify and configure the crossover operator, used in the
    //         solution combination method of the scatter search
    crossover = CrossoverFactory.getCrossoverOperator("SBXCrossover");                   
    crossover.setParameter("probability"      , 1.0) ;                   
    crossover.setParameter("distributionIndex", 20.0) ;
    
    // STEP 5. Specify and configure the improvement method. We use by default
    //         a polynomial mutation in this method.
    mutation = MutationFactory.getMutationOperator("PolynomialMutation");
    mutation.setParameter("probability", 1.0/problem.getNumberOfVariables());
    
    improvement = new MutationLocalSearch(problem,mutation);
    improvement.setParameter("improvementRounds", 1);
          
    // STEP 6. Add the operators to the algorithm
    algorithm.addOperator("crossover",crossover);
    algorithm.addOperator("improvement",improvement);   
    
    long initTime      ;
    long estimatedTime ;    
    initTime = System.currentTimeMillis();
    
    // STEP 7. Run the algorithm 
    SolutionSet population = algorithm.execute();
    estimatedTime = System.currentTimeMillis() - initTime;

    // STEP 8. Print the results
    logger_.info("Total execution time: "+estimatedTime + "ms");
    logger_.info("Variables values have been writen to file VAR");
    population.printVariablesToFile("VAR");    
    logger_.info("Objectives values have been writen to file FUN");
    population.printObjectivesToFile("FUN");
  
    if (indicators != null) {
      logger_.info("Quality indicators") ;
      logger_.info("Hypervolume: " + indicators.getHypervolume(population)) ;
      logger_.info("GD         : " + indicators.getGD(population)) ;
      logger_.info("IGD        : " + indicators.getIGD(population)) ;
      logger_.info("Spread     : " + indicators.getSpread(population)) ;
      logger_.info("Epsilon    : " + indicators.getEpsilon(population)) ;  
    } // if
  } //main
} // AbYSS_main
