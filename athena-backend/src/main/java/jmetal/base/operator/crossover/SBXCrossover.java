/**
 * SBXCrossover.java
 * Class representing a simulated binary (SBX) crossover operator
 * @author Juan J. Durillo
 * @version 1.0
 */

package jmetal.base.operator.crossover;

import java.util.Properties;

import jmetal.base.*;
import jmetal.base.variable.*;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;
import jmetal.util.Configuration.*;
import jmetal.util.wrapper.XReal;

/**
 * This class allows to apply a SBX crossover operator using two parent
 * solutions.
 * NOTE: the operator is applied to Real solutions, so the type of the solutions
 * must be </code>SolutionType_.Real</code>.
 * NOTE: if you use the default constructor, the value of the etc_c parameter is
 * DEFAULT_INDEX_CROSSOVER. You can change it using the parameter 
 * "distributionIndex" before invoking the execute() method -- see lines 196-199
 */
public class SBXCrossover extends Crossover {
    
  /**
   * EPS defines the minimum difference allowed between real values
   */
  protected static final double EPS= 1.0e-14;
                                                                                      
  /**
   * eta_c stores the index for crossover to use
   */
  public double eta_c = 20.0;
  
  /**
   * REAL_SOLUTION represents class jmetal.base.solutionType.RealSolutionType
   */
  private static Class REAL_SOLUTION ; 

  /**
   * REAL_SOLUTION represents class jmetal.base.solutionType.ArrayRealSolutionType
   */
  private static Class ARRAY_REAL_SOLUTION ; 
  
  /** 
   * Constructor
   * Create a new SBX crossover operator whit a default
   * index given by <code>DEFAULT_INDEX_CROSSOVER</code>
   */
  public SBXCrossover() {
    try {
    	REAL_SOLUTION = Class.forName("jmetal.base.solutionType.RealSolutionType") ;
    	ARRAY_REAL_SOLUTION = Class.forName("jmetal.base.solutionType.ArrayRealSolutionType") ;
    } catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    } // catch
  } // SBXCrossover
    

  /**
   * Constructor
   * Create a new SBX crossover operator whit a default
   * index given by <code>DEFAULT_INDEX_CROSSOVER</code>
   */
  public SBXCrossover(Properties properties) {
    this();
    eta_c = (new Double((String)(properties.get("eta_c")))).doubleValue();
  } // SBXCrossover

  /**
   * Perform the crossover operation. 
   * @param probability Crossover probability
   * @param parent1 The first parent
   * @param parent2 The second parent
   * @return An array containing the two offsprings
   */
  public Solution[] doCrossover(double probability, 
                                Solution parent1, 
                                Solution parent2) throws JMException {
    
    Solution [] offSpring = new Solution[2];

    offSpring[0] = new Solution(parent1);
    offSpring[1] = new Solution(parent2);
                    
    int i;
    double rand;
    double y1, y2, yL, yu;
    double c1, c2;
    double alpha, beta, betaq;
    double valueX1,valueX2;
		XReal x1 = new XReal(parent1) ;		
		XReal x2 = new XReal(parent2) ;		
		XReal offs1 = new XReal(offSpring[0]) ;
		XReal offs2 = new XReal(offSpring[1]) ;
		
		int numberOfVariables = x1.getNumberOfDecisionVariables() ;

    if (PseudoRandom.randDouble() <= probability){
      for (i=0; i<numberOfVariables; i++){
        valueX1 = x1.getValue(i);
        valueX2 = x2.getValue(i);
        if (PseudoRandom.randDouble()<=0.5 ){
          if (java.lang.Math.abs(valueX1- valueX2) > EPS){
            
            if (valueX1 < valueX2){
              y1 = valueX1;
              y2 = valueX2;
            } else {
              y1 = valueX2;
              y2 = valueX1;
            } // if                       
            
            yL = x1.getLowerBound(i) ;
            yu = x1.getUpperBound(i) ;
            rand = PseudoRandom.randDouble();
            beta = 1.0 + (2.0*(y1-yL)/(y2-y1));
            alpha = 2.0 - java.lang.Math.pow(beta,-(eta_c+1.0));
            
            if (rand <= (1.0/alpha)){
              betaq = java.lang.Math.pow ((rand*alpha),(1.0/(eta_c+1.0)));
            } else {
              betaq = java.lang.Math.pow ((1.0/(2.0 - rand*alpha)),(1.0/(eta_c+1.0)));
            } // if
            
            c1 = 0.5*((y1+y2)-betaq*(y2-y1));
            beta = 1.0 + (2.0*(yu-y2)/(y2-y1));
            alpha = 2.0 - java.lang.Math.pow(beta,-(eta_c+1.0));
            
            if (rand <= (1.0/alpha)){
              betaq = java.lang.Math.pow ((rand*alpha),(1.0/(eta_c+1.0)));
            } else {
              betaq = java.lang.Math.pow ((1.0/(2.0 - rand*alpha)),(1.0/(eta_c+1.0)));
            } // if
              
            c2 = 0.5*((y1+y2)+betaq*(y2-y1));
            
            if (c1<yL)
              c1=yL;
            
            if (c2<yL)
              c2=yL;
            
            if (c1>yu)
              c1=yu;
            
            if (c2>yu)
              c2=yu;                        
              
            if (PseudoRandom.randDouble()<=0.5) {
              offs1.setValue(i, c2) ;
              offs2.setValue(i, c1) ;
            } else {
              offs1.setValue(i, c1) ;
              offs2.setValue(i, c2) ;
            } // if
          } else {
            offs1.setValue(i,valueX1) ;
            offs2.setValue(i, valueX2) ;
          } // if
        } else {
          offs1.setValue(i, valueX2) ;
          offs2.setValue(i, valueX1) ;
        } // if
      } // if
    } // if
                                    
     return offSpring;                                                                                      
  } // doCrossover
  
  
  /**
  * Executes the operation
  * @param object An object containing an array of two parents
  * @return An object containing the offSprings
  */
  public Object execute(Object object) throws JMException {
    Solution [] parents = (Solution [])object;

    if (((parents[0].getType().getClass() != REAL_SOLUTION) &&
        (parents[1].getType().getClass() != REAL_SOLUTION)) &&
        ((parents[0].getType().getClass() != ARRAY_REAL_SOLUTION) &&
         (parents[1].getType().getClass() != ARRAY_REAL_SOLUTION))) {

      Configuration.logger_.severe("SBXCrossover.execute: the solutions " +
					"type " + parents[0].getType() + " is not allowed with this operator");

      Class cls = java.lang.String.class;
      String name = cls.getName(); 
      throw new JMException("Exception in " + name + ".execute()") ;
    } // if 
    
    Double probability = (Double)getParameter("probability");
    if (parents.length < 2)
    {
      Configuration.logger_.severe("SBXCrossover.execute: operator needs two " +
          "parents");
      Class cls = java.lang.String.class;
      String name = cls.getName(); 
      throw new JMException("Exception in " + name + ".execute()") ;      
    }
    else if (probability == null)
    {
      Configuration.logger_.severe("SBXCrossover.execute: probability not " +
      "specified");
      Class cls = java.lang.String.class;
      String name = cls.getName(); 
      throw new JMException("Exception in " + name + ".execute()") ;  
    }
 
    Double distributionIndex = (Double)getParameter("distributionIndex");
    if (distributionIndex != null) {
      eta_c = distributionIndex ;
    } // if
    
    Solution [] offSpring;
    offSpring = doCrossover(probability.doubleValue(),
                            parents[0],
                            parents[1]);
        
        
    for (int i = 0; i < offSpring.length; i++)
    {
      offSpring[i].setCrowdingDistance(0.0);
      offSpring[i].setRank(0);
    } 
    return offSpring;//*/
  } // execute 
} // SBXCrossover
