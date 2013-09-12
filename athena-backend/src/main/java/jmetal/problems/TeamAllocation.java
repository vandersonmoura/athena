package jmetal.problems;

import java.util.LinkedList;
import java.util.List;

import jmetal.base.Problem;
import jmetal.base.Solution;
import jmetal.base.solutionType.IntSolutionType;
import jmetal.util.JMException;

import com.pedroalmir.athena.impl.teamAllocation.model.Desenvolvedor;

/**
 * 
 * @author Werney
 */
public class TeamAllocation extends Problem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Desenvolvedor> candidatos = new LinkedList<Desenvolvedor>();
	private int tamanhoEquipe;
	private final int a = 10;
	private final int b = 10000;

	/**
	 * @param desenvolvedores
	 * @param tamanhoEquipe
	 * @throws ClassNotFoundException
	 */
	public TeamAllocation(List<Desenvolvedor> desenvolvedores, int tamanhoEquipe) throws ClassNotFoundException {
		
		problemName_ = "TeamAllocation";
		numberOfVariables_ = desenvolvedores.size();
		numberOfObjectives_ = 2;
		numberOfConstraints_ = 0;

		this.tamanhoEquipe = tamanhoEquipe;

		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];

		for (int i = 0; i < numberOfVariables_; i++) {
			lowerLimit_[i] = 0;
			upperLimit_[i] = 1;
		}
		
		solutionType_ = new IntSolutionType(this);
		candidatos = desenvolvedores;
	}

	@Override
	public void evaluate(Solution solution) throws JMException {
		double x[] = new double[numberOfVariables_];
		double produtividadeTotal = 0.0;
		double salarioTotal = 0.0;
		int equipeFormada = 0;
		
		for (int i = 0; i < numberOfVariables_; i++) {
			x[i] = solution.getDecisionVariables()[i].getValue();
			produtividadeTotal += candidatos.get(i).getProdutividade() * x[i];
			salarioTotal += candidatos.get(i).getSalario() * x[i];
			if (x[i] == 1)
				equipeFormada++;
		}

		produtividadeTotal = produtividadeTotal - (a * Math.abs(tamanhoEquipe - equipeFormada));
		salarioTotal += b * Math.abs(tamanhoEquipe - equipeFormada);

		solution.setObjective(0, -1 * produtividadeTotal);
		solution.setObjective(1, salarioTotal);
	}
}
