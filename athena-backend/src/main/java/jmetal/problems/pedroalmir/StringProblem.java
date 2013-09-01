/**
 * 
 */
package jmetal.problems.pedroalmir;

import java.util.LinkedList;
import java.util.List;

import jmetal.base.Problem;
import jmetal.base.Solution;
import jmetal.base.solutionType.IntSolutionType;
import jmetal.util.JMException;

/**
 * length
 * 
 * @author Pedro Almir
 * 
 */
public class StringProblem extends Problem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 871246890128825054L;
	private int goalLength;
	private char[] candidates;
	private String goal;
	private int count = 1;

	public StringProblem(char[] candidates, String goal) throws ClassNotFoundException {
		problemName_ = "StringProblem";
		numberOfVariables_ = candidates.length;
		numberOfObjectives_ = 1;
		numberOfConstraints_ = 0;

		this.goalLength = goal.length();

		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];

		for (int i = 0; i < numberOfVariables_; i++) {
			lowerLimit_[i] = 1;
			upperLimit_[i] = goalLength+1;
		}
		
		//this.solutionType_ = new PermutationSolutionType(this);
		this.solutionType_ = new IntSolutionType(this);
		this.candidates = candidates;
		this.goal = goal;
	}

	/*
	 * (non-Javadoc)
	 * @see jmetal.base.Problem#evaluate(jmetal.base.Solution)
	 */
	@Override
	public void evaluate(Solution solution) throws JMException {

		char[] generatedSolution = new char[numberOfVariables_];
		
//		for(int i = 0; i < numberOfVariables_; i++){
//			generatedSolution[i] = '$';
//		}
		
		double x[] = new double[numberOfVariables_];
		List<Integer> listOfIndex = new LinkedList<Integer>();
		
		double fitness = 0.0;
		
		for (int i = 0; i < numberOfVariables_; i++) {
			x[i] = solution.getDecisionVariables()[i].getValue();
			String value = String.valueOf(this.candidates[i]);
			
			if(x[i] < 1 || x[i] > 9){
				if(this.goal.contains(value)){
					fitness -= 10;
				}
			}else if(x[i] >= 1 && x[i] <= 9){
				int index = (int) x[i] - 1;
				int position = this.goal.indexOf(value);
				
				if(!listOfIndex.contains(index)){
					listOfIndex.add(index);
					generatedSolution[index] = this.candidates[i];
				}
				
				if(index == position){
					fitness += 1000;
				}
			}
			
		}
		
		String solucao = String.copyValueOf(generatedSolution, 0, goalLength);

//		for (int i = 0; i < goalLength; i++) {
//			if (solucao.charAt(i) == this.goal.charAt(i)) {
//				fitness+=100;
//			}
//		}

//		double error = 0.0;
//		for (int i = 0; i < solucao.length(); i++) {
//			if (solucao.charAt(i) != this.goal.charAt(i)) {
//				error++;
//			}
//		}

		
		//fitness -= (100*Math.abs(goalLength - listOfIndex.size())); 
		fitness -= (100*Math.abs(goalLength - generatedSolution.length));
		System.out.println("Solution " + (count++) + ": " + solucao + " | Fitness: " + -1 * fitness);

		// solution.setObjective(0, fitness);
		solution.setObjective(0, -1 * fitness);
	}

	/**
	 * @param generatedSolution
	 * @return string
	 */
	@SuppressWarnings("unused")
	private String createString(List<Character> generatedSolution) {
		char[] characters = new char[generatedSolution.size()];
		for (int i = 0; i < generatedSolution.size(); i++) {
			characters[i] = generatedSolution.get(i);
		}
		return String.copyValueOf(characters);
	}

	/**
	 * @return the stringLength
	 */
	public int getStringLength() {
		return goalLength;
	}

	/**
	 * @param stringLength
	 *            the stringLength to set
	 */
	public void setStringLength(int stringLength) {
		this.goalLength = stringLength;
	}

	/**
	 * @return the candidates
	 */
	public char[] getCandidates() {
		return candidates;
	}

	/**
	 * @param candidates
	 *            the candidates to set
	 */
	public void setCandidates(char[] candidates) {
		this.candidates = candidates;
	}

	/**
	 * @return the goal
	 */
	public String getGoal() {
		return goal;
	}

	/**
	 * @param goal
	 *            the goal to set
	 */
	public void setGoal(String goal) {
		this.goal = goal;
	}

}
