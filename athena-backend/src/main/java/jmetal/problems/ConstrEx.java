/**
 * ConstrEx.java
 * 
 * @author Antonio J. Nebro
 * @author Juan J. Durillo
 * @version 1.0
 */
package jmetal.problems;

import java.io.IOException;

import jmetal.base.*;
import jmetal.base.solutionType.ArrayRealSolutionType;
import jmetal.base.solutionType.BinaryRealSolutionType;
import jmetal.base.solutionType.RealSolutionType;
import jmetal.util.JMException;
import jmetal.util.Configuration.*;

/**
 * Class representing problem Constr_Ex
 */
public class ConstrEx extends Problem {
	/**
	 * Constructor
	 * Creates a default instance of the Constr_Ex problem
	 * 
	 * @param solutionType
	 *            The solution type must "Real" or "BinaryReal".
	 */
	public ConstrEx(String solutionType) throws ClassNotFoundException {
		numberOfVariables_ = 2;
		numberOfObjectives_ = 2;
		numberOfConstraints_ = 2;
		problemName_ = "Constr_Ex";

		lowerLimit_ = new double[numberOfVariables_];
		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_[0] = 0.1;
		lowerLimit_[1] = 0.0;
		upperLimit_[0] = 1.0;
		upperLimit_[1] = 5.0;

		if (solutionType.compareTo("BinaryReal") == 0)
			solutionType_ = new BinaryRealSolutionType(this);
		else if (solutionType.compareTo("Real") == 0)
			solutionType_ = new RealSolutionType(this);
		else {
			System.out.println("Error: solution type " + solutionType + " invalid");
			System.exit(-1);
		}
	} // ConstrEx

	/**
	 * Evaluates a solution
	 * 
	 * @param solution
	 *            The solution to evaluate
	 * @throws JMException
	 */
	public void evaluate(Solution solution) throws JMException {
		Variable[] variable = solution.getDecisionVariables();

		double[] f = new double[numberOfObjectives_];
		f[0] = variable[0].getValue();
		f[1] = (1.0 + variable[1].getValue()) / variable[0].getValue();

		solution.setObjective(0, f[0]);
		solution.setObjective(1, f[1]);
	} // evaluate

	/**
	 * Evaluates the constraint overhead of a solution
	 * 
	 * @param solution
	 *            The solution
	 * @throws JMException
	 */
	public void evaluateConstraints(Solution solution) throws JMException {
		double[] constraint = new double[this.getNumberOfConstraints()];

		double x1 = solution.getDecisionVariables()[0].getValue();
		double x2 = solution.getDecisionVariables()[1].getValue();

		constraint[0] = (x2 + 9 * x1 - 6.0);
		constraint[1] = (-x2 + 9 * x1 - 1.0);

		double total = 0.0;
		int number = 0;
		for (int i = 0; i < this.getNumberOfConstraints(); i++)
			if (constraint[i] < 0.0) {
				total += constraint[i];
				number++;
			}

		solution.setOverallConstraintViolation(total);
		solution.setNumberOfViolatedConstraint(number);
	} // evaluateConstraints
} // ConstrEx