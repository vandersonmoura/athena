/**
 * aMOCell1.java
 * @author Juan J. Durillo
 * @version 1.0
 */
package jmetal.metaheuristics.mocell;

import java.util.Comparator;

import jmetal.base.*;
import jmetal.base.operator.comparator.*;
import jmetal.util.*;
import jmetal.util.archive.CrowdingArchive;

/**
 * This class represents the asynchronous version of the MOCell algorithm
 */
public class aMOCell1 extends Algorithm{

  /**
   * Stores the problem to solve
   */
  private Problem problem_;     

  /** 
   * Constructor
   * @param problem Problem to solve
   */
  public aMOCell1(Problem problem){
    problem_ = problem;
  } //aMOCell1       

  /**   
   * Runs of the aMOCell1 algorithm.
   * @return a <code>SolutionSet</code> that is a set of non dominated solutions
   * as a result of the algorithm execution  
   * @throws JMException 
   */   
  public SolutionSet execute() throws JMException, ClassNotFoundException {
    int populationSize, archiveSize, maxEvaluations, evaluations, feedBack;
    Operator mutationOperator, crossoverOperator, selectionOperator;
    SolutionSet currentSolutionSet;
    CrowdingArchive archive;
    SolutionSet [] neighbors;    
    Neighborhood neighborhood;
    Comparator dominance = new DominanceComparator(),
    crowding  = new CrowdingComparator();                   
    Distance distance = new Distance();

    //Read the params
    populationSize    = ((Integer)getInputParameter("populationSize")).intValue();
    archiveSize       = ((Integer)getInputParameter("archiveSize")).intValue();
    maxEvaluations    = ((Integer)getInputParameter("maxEvaluations")).intValue();                
    feedBack          = ((Integer)getInputParameter("feedBack")).intValue();

    //Read the operators
    mutationOperator  = operators_.get("mutation");
    crossoverOperator = operators_.get("crossover");
    selectionOperator = operators_.get("selection");        

    //Init the variables
    //init the population and the archive
    currentSolutionSet  = new SolutionSet(populationSize);        
    archive            = new CrowdingArchive(archiveSize,problem_.getNumberOfObjectives());                
    evaluations        = 0;                        
    neighborhood       = new Neighborhood(populationSize);
    neighbors          = new SolutionSet[populationSize];

    //Create the initial population
    for (int i = 0; i < populationSize; i++){
      Solution solution = new Solution(problem_);
      problem_.evaluate(solution);           
      problem_.evaluateConstraints(solution);
      currentSolutionSet.add(solution);
      solution.setLocation(i);
      evaluations++;
    }


    while (evaluations < maxEvaluations){                                 
      for (int ind = 0; ind < currentSolutionSet.size(); ind++){
        Solution individual = new Solution(currentSolutionSet.get(ind));

        Solution [] parents = new Solution[2];
        Solution [] offSpring;

        //neighbors[ind] = neighborhood.getFourNeighbors(currentSolutionSet,ind);
        neighbors[ind] = neighborhood.getEightNeighbors(currentSolutionSet,ind);                                                           
        neighbors[ind].add(individual);

        //parents
        parents[0] = (Solution)selectionOperator.execute(neighbors[ind]);
        parents[1] = (Solution)selectionOperator.execute(neighbors[ind]);

        //Create a new solution, using genetic operators mutation and crossover
        offSpring = (Solution [])crossoverOperator.execute(parents);               
        mutationOperator.execute(offSpring[0]);

        //Evaluate solution and constraints
        problem_.evaluate(offSpring[0]);
        problem_.evaluateConstraints(offSpring[0]);
        evaluations++;

        // Check dominance
        int flag = dominance.compare(individual,offSpring[0]);               
        if (flag == 1) { // offSpring[0] dominates
          offSpring[0].setLocation(individual.getLocation());                                      
          currentSolutionSet.replace(offSpring[0].getLocation(),offSpring[0]);
          archive.add(new Solution(offSpring[0]));                   
        } else if (flag == 0) { //Both two are non-dominates               
          neighbors[ind].add(offSpring[0]);
          //(new Spea2Fitness(neighbors[ind])).fitnessAssign();                   
          //neighbors[ind].sort(new FitnessAndCrowdingDistanceComparator());
          Ranking rank = new Ranking(neighbors[ind]);
          for (int j = 0; j < rank.getNumberOfSubfronts(); j++) {
            (distance).crowdingDistanceAssignment(rank.getSubfront(j),
                problem_.getNumberOfObjectives());
          }

          boolean deleteMutant = true;          
          int compareResult = crowding.compare(individual,offSpring[0]);
          if (compareResult == 1) //The offSpring[0] is better
            deleteMutant = false;

          if (!deleteMutant){
            offSpring[0].setLocation(individual.getLocation());
            currentSolutionSet.replace(offSpring[0].getLocation(),offSpring[0]);
            archive.add(new Solution(offSpring[0]));
          } else {
            archive.add(new Solution(offSpring[0]));    
          }
        }                              
      }                     

      //Stores a portion of the archive into the population
      (distance).crowdingDistanceAssignment(archive,problem_.getNumberOfObjectives());                      
      for (int j = 0; j < feedBack; j++){
        if (archive.size() > j){
          int r = PseudoRandom.randInt(0,currentSolutionSet.size()-1);
          if (r < currentSolutionSet.size()){
            Solution individual = archive.get(j);
            individual.setLocation(r);
            currentSolutionSet.replace(r,new Solution(individual));
          }
        }
      }                      
    }
    return archive;
  } // execute        
} // aMOCell1

