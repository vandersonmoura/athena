/**
 * SolutionSet.java
 * 
 * @author Juan J. Durillo
 * @version 1.0
 */
package jmetal.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jmetal.util.Configuration;

import com.pedroalmir.athena.impl.teamAllocation.model.Desenvolvedor;
import com.pedroalmir.athena.impl.teamAllocation.model.Equipe;

/**
 * Class representing a SolutionSet (a set of solutions)
 */
public class SolutionSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3412742866207055036L;

	/**
	 * Stores a list of <code>solution</code> objects.
	 */
	protected List<Solution> solutionsList_;

	/**
	 * Maximum size of the solution set
	 */
	private int capacity_ = 0;

	/**
	 * Constructor.
	 * Creates an unbounded solution set.
	 */
	public SolutionSet() {
		solutionsList_ = new ArrayList<Solution>();
	} // SolutionSet

	/**
	 * Creates a empty solutionSet with a maximum capacity.
	 * 
	 * @param maximumSize
	 *            Maximum size.
	 */
	public SolutionSet(int maximumSize) {
		solutionsList_ = new ArrayList<Solution>();
		capacity_ = maximumSize;
	} // SolutionSet

	/**
	 * Inserts a new solution into the SolutionSet.
	 * 
	 * @param solution
	 *            The <code>Solution</code> to store
	 * @return True If the <code>Solution</code> has been inserted, false
	 *         otherwise.
	 */
	public boolean add(Solution solution) {
		if (solutionsList_.size() == capacity_) {
			Configuration.logger_.severe("The population is full");
			Configuration.logger_.severe("Capacity is : " + capacity_);
			Configuration.logger_.severe("\t Size is: " + this.size());
			return false;
		} // if

		solutionsList_.add(solution);
		return true;
	} // add

	/**
	 * Returns the ith solution in the set.
	 * 
	 * @param i
	 *            Position of the solution to obtain.
	 * @return The <code>Solution</code> at the position i.
	 * @throws IndexOutOfBoundsException.
	 */
	public Solution get(int i) {
		if (i >= solutionsList_.size()) {
			throw new IndexOutOfBoundsException("Index out of Bound " + i);
		}
		return solutionsList_.get(i);
	} // get

	/**
	 * Returns the maximum capacity of the solution set
	 * 
	 * @return The maximum capacity of the solution set
	 */
	public int getMaxSize() {
		return capacity_;
	} // getMaxSize

	/**
	 * Sorts a SolutionSet using a <code>Comparator</code>.
	 * 
	 * @param comparator
	 *            <code>Comparator</code> used to sort.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sort(Comparator comparator) {
		if (comparator == null) {
			Configuration.logger_.severe("No criterium for compare exist");
			return;
		} // if
		Collections.sort(solutionsList_, comparator);
	} // sort

	/**
	 * Returns the number of solutions in the SolutionSet.
	 * 
	 * @return The size of the SolutionSet.
	 */
	public int size() {
		return solutionsList_.size();
	} // size

	/**
	 * @param desenvolvedores
	 * @return
	 */
	public List<Equipe> getListOfResults(List<Desenvolvedor> desenvolvedores) {
		List<Equipe> equipes = new LinkedList<Equipe>();
		Equipe equipe = null;

		int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length;

		for (int i = 0; i < solutionsList_.size(); i++) {
			/* Nova equipe */
			equipe = new Equipe();

			for (int j = 0; j < numberOfVariables; j++) {
				if (solutionsList_.get(i).getDecisionVariables()[j].toString().equals("1")) {
					equipe.addDesenvolvedor(desenvolvedores.get(j));
				}
			}

			String[] values = solutionsList_.get(i).toStringResults().split(";");

			DecimalFormat fmt = new DecimalFormat("0.00");
			double produtividade = Double.valueOf(values[0]);
			produtividade = Double.valueOf(fmt.format(produtividade).replaceAll(",", "."));
			equipe.setProdutividade(produtividade);

			double custo = Double.valueOf(values[1]);
			custo = Double.valueOf(fmt.format(custo).replaceAll(",", "."));
			equipe.setCusto(custo);

			equipes.add(equipe);

		}

		return equipes;
	}

	/**
	 * @return
	 */
	public Map<String, List<Double>> getListObjetivos() {
		Map<String, List<Double>> objetivos = new HashMap<String, List<Double>>();

		List<Double> objetivo1 = new LinkedList<Double>();
		List<Double> objetivo2 = new LinkedList<Double>();

		for (int i = 0; i < solutionsList_.size(); i++) {

			Double valueObjetivo1 = Math.abs(solutionsList_.get(i).getObjective(0));
			Double valueObjetivo2 = solutionsList_.get(i).getObjective(1);

			objetivo1.add(valueObjetivo1);
			objetivo2.add(valueObjetivo2);
		}

		objetivos.put("Produtividade", objetivo1);
		objetivos.put("Custo", objetivo2);
		return objetivos;
	}

	public List<Equipe> printResultsToFile(String folder, String filePath, List<Desenvolvedor> desenvolvedores) {
		List<Equipe> equipes = new LinkedList<Equipe>();
		Equipe equipe = null;

		try {
			FileOutputStream fos = new FileOutputStream(folder + filePath, true);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length;
			for (int i = 0; i < solutionsList_.size(); i++) {
				/* Nova equipe */
				equipe = new Equipe();
				for (int j = 0; j < numberOfVariables; j++) {
					if (solutionsList_.get(i).getDecisionVariables()[j].toString().equals("1")) {
						bw.write(desenvolvedores.get(j).getNome() + ", ");
						equipe.addDesenvolvedor(desenvolvedores.get(j));
					}
				}
				String[] values = solutionsList_.get(i).toStringResults().split(";");

				DecimalFormat fmt = new DecimalFormat("0.00");
				double produtividade = Double.valueOf(values[0]);
				produtividade = Double.valueOf(fmt.format(produtividade).replaceAll(",", "."));
				equipe.setProdutividade(produtividade);

				double custo = Double.valueOf(values[1]);
				custo = Double.valueOf(fmt.format(custo).replaceAll(",", "."));
				equipe.setCusto(custo);

				equipes.add(equipe);

				bw.write("; " + solutionsList_.get(i).toStringResults());
				bw.newLine();
			}

			/* Close the file */
			bw.close();
			return equipes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Writes the objective funcion values of the <code>Solution</code> objects into the set in a file.
	 * 
	 * @param path
	 *            The output file name
	 */
	public void printObjectivesToFile(String path) {
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for (int i = 0; i < solutionsList_.size(); i++) {
				// if (this.vector[i].getFitness()<1.0) {
				bw.write(solutionsList_.get(i).toString());
				bw.newLine();
				// }
			}

			/* Close the file */
			bw.close();
		} catch (IOException e) {
			Configuration.logger_.severe("Error acceding to the file");
			e.printStackTrace();
		}
	} // printObjectivesToFile

	public Map<String, List<Double>> printObjectivesToFileTallo(String folder, String filePath) {
		Map<String, List<Double>> objetivos = new HashMap<String, List<Double>>();
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(folder + filePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			FileOutputStream file1 = new FileOutputStream(folder + "resultados/ProdutividadeEquipe.csv", true);
			OutputStreamWriter sw1 = new OutputStreamWriter(file1);
			BufferedWriter print1 = new BufferedWriter(sw1);

			FileOutputStream file2 = new FileOutputStream(folder + "resultados/CustoEquipe.csv", true);
			OutputStreamWriter sw2 = new OutputStreamWriter(file2);
			BufferedWriter print2 = new BufferedWriter(sw2);

			List<Double> objetivo1 = new LinkedList<Double>();
			List<Double> objetivo2 = new LinkedList<Double>();

			for (int i = 0; i < solutionsList_.size(); i++) {
				bw.write(solutionsList_.get(i).toString());
				bw.newLine();

				Double valueObjetivo1 = Math.abs(solutionsList_.get(i).getObjective(0));
				Double valueObjetivo2 = solutionsList_.get(i).getObjective(1);

				print1.write(valueObjetivo1 + "");
				print1.newLine();

				print2.write(valueObjetivo2 + "");
				print2.newLine();

				objetivo1.add(valueObjetivo1);
				objetivo2.add(valueObjetivo2);
			}

			/* Close the file */
			bw.close();
			print1.close();
			print2.close();

			objetivos.put("Produtividade", objetivo1);
			objetivos.put("Custo", objetivo2);
			return objetivos;
		} catch (IOException e) {
			Configuration.logger_.severe("Error acceding to the file");
			e.printStackTrace();
		}
		return null;
	} // printObjectivesToFile
	
	public void printVariablesToFile(String path) {
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			
			int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length;
			
			for (int i = 0; i < solutionsList_.size(); i++) {
				for (int j = 0; j < numberOfVariables; j++){
					bw.write(solutionsList_.get(i).getDecisionVariables()[j].toString() + " ");
					
				}
				bw.newLine();
			}

			/* Close the file */
			bw.close();
		} catch (IOException e) {
			Configuration.logger_.severe("Error acceding to the file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the decision variable values of the <code>Solution</code> solutions objects into the set in a file.
	 * 
	 * @param path
	 *            The output file name
	 */
	public void printVariablesToFile(String path, char[] candidates, int goalLength) {
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			
			int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length;
			char[] generatedSolution = new char[numberOfVariables];
			
			for (int i = 0; i < solutionsList_.size(); i++) {
				for (int j = 0; j < numberOfVariables; j++){
					
					bw.write(solutionsList_.get(i).getDecisionVariables()[j].toString() + " ");
					
					int index = Integer.valueOf(solutionsList_.get(i).getDecisionVariables()[j].toString()) - 1;
					
					generatedSolution[index] = candidates[j];
					
				}
				bw.newLine();
			}
			
			System.out.println("Better solution: " + String.copyValueOf(generatedSolution, 0, goalLength));
			
			/* Close the file */
			bw.close();
		} catch (IOException e) {
			Configuration.logger_.severe("Error acceding to the file");
			e.printStackTrace();
		}
	} // printVariablesToFile

	public void printVariablesToFileTallo(String folder, String filePath) { // continuar
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(folder + filePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			FileOutputStream file = new FileOutputStream(folder + "resultados/equipe.csv");
			OutputStreamWriter sw = new OutputStreamWriter(file);
			BufferedWriter print = new BufferedWriter(sw);

			int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length;
			for (int i = 0; i < solutionsList_.size(); i++) {
				for (int j = 0; j < numberOfVariables; j++)
					bw.write(solutionsList_.get(i).getDecisionVariables()[j].toString() + " ");
				bw.newLine();
			}

			for (int i = 0; i < numberOfVariables; i++) {
				print.write(solutionsList_.get(0).getDecisionVariables()[i].toString() + " ");
				print.newLine();
			}

			/* Close the file */
			bw.close();
			print.close();
		} catch (IOException e) {
			Configuration.logger_.severe("Error acceding to the file");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void printVariablesToFileGA(String path) {
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			/**
			 * int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length ;
			 * for (int i = 0; i < solutionsList_.size(); i++) {
			 * for (int j = 0; j < numberOfVariables; j++)
			 * bw.write(solutionsList_.get(i).getDecisionVariables()[j].toString() + " ");
			 * bw.newLine();
			 * 
			 * 
			 * 
			 * }
			 * 
			 * 
			 * 
			 * 
			 * 
			 * /* Close the file
			 */

			PrintWriter saida = new PrintWriter(new OutputStreamWriter(new FileOutputStream("tipperGA2.fcl"), "UTF-8"));
			BufferedReader incopia = new BufferedReader(new FileReader("tipper.fcl"));
			ArrayList<Integer> regras = new ArrayList<Integer>();
			int numberOfVariables = solutionsList_.get(0).getDecisionVariables().length;
			for (int i = 0; i < solutionsList_.size(); i++) {
				for (int j = 0; j < numberOfVariables; j++) {
					float temp = Float.parseFloat(solutionsList_.get(i).getDecisionVariables()[j].toString());
					int valor = java.lang.Math.round(temp);
					regras.add(valor);
					bw.write(Integer.toString(valor));
					bw.newLine();
				}
			}

			try {

				PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fconhecimento.fcl"), "UTF-8"));

				saida2.println("TERM ConhecimentoPouca := (0.0, 1) ("
						+ new DecimalFormat("0.00").format(regras.get(2)).replace(',', '.') + ",0);");

				saida2.println("TERM ConhecimentoMedia := (" + new DecimalFormat("0.00").format(regras.get(1)).replace(',', '.')
						+ ", 0) (" + new DecimalFormat("0.00").format(regras.get(3)).replace(',', '.') + ",1) ("
						+ new DecimalFormat("0.00").format(regras.get(5)).replace(',', '.') + ",0);");

				saida2.println("TERM ConhecimentoMuita := (" + new DecimalFormat("0.00").format(regras.get(4)).replace(',', '.')
						+ ", 0) (10.0, 1);");

				saida2.close();
			} catch (IOException e) {
			}

			try {

				PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fhabilidade.fcl"), "UTF-8"));

				saida2.println("TERM HabilidadePouca := (0.0, 1) ("
						+ new DecimalFormat("0.00").format(regras.get(9)).replace(',', '.') + ",0);");

				saida2.println("TERM HabilidadeMedia := (" + new DecimalFormat("0.00").format(regras.get(8)).replace(',', '.')
						+ ", 0) (" + new DecimalFormat("0.00").format(regras.get(10)).replace(',', '.') + ",1) ("
						+ new DecimalFormat("0.00").format(regras.get(12)).replace(',', '.') + ",0);");

				saida2.println("TERM HabilidadeMuita := (" + new DecimalFormat("0.00").format(regras.get(11)).replace(',', '.')
						+ ", 0) (10.0, 1);");

				// JOptionPane.showMessageDialog(null,"complexidade!","Aviso", JOptionPane.INFORMATION_MESSAGE);

				saida2.close();
			} catch (IOException e) {
			}

			try {

				PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fatitude.fcl"), "UTF-8"));

				saida2.println("TERM AtitudeRuim := (0.0, 1) ("
						+ new DecimalFormat("0.00").format(regras.get(16)).replace(',', '.') + ",0);");

				saida2.println("TERM AtitudeRegular := (" + new DecimalFormat("0.00").format(regras.get(15)).replace(',', '.')
						+ ", 0) (" + new DecimalFormat("0.00").format(regras.get(17)).replace(',', '.') + ",1) ("
						+ new DecimalFormat("0.00").format(regras.get(19)).replace(',', '.') + ",0);");

				saida2.println("TERM AtitudeBoa := (" + new DecimalFormat("0.00").format(regras.get(18)).replace(',', '.')
						+ ", 0) (10.0, 1);");

				// JOptionPane.showMessageDialog(null,"volatilidade!","Aviso", JOptionPane.INFORMATION_MESSAGE);

				saida2.close();
			} catch (IOException e) {
			}

			try {

				PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fprodutividade.fcl"), "UTF-8"));

				saida2.println("TERM ProdutividadeMuitoBaixa := (0.0, 1) ("
						+ new DecimalFormat("0.00").format(regras.get(23)).replace(',', '.') + ",0);");

				saida2.println("TERM ProdutividadeBaixa := ("
						+ new DecimalFormat("0.00").format(regras.get(22)).replace(',', '.') + ", 0) ("
						+ new DecimalFormat("0.00").format(regras.get(24)).replace(',', '.') + ",1) ("
						+ new DecimalFormat("0.00").format(regras.get(26)).replace(',', '.') + ",0);");

				saida2.println("TERM ProdutividadeMedia := ("
						+ new DecimalFormat("0.00").format(regras.get(25)).replace(',', '.') + ", 0) ("
						+ new DecimalFormat("0.00").format(regras.get(27)).replace(',', '.') + ",1) ("
						+ new DecimalFormat("0.00").format(regras.get(28)).replace(',', '.') + ",0);");

				saida2.println("TERM ProdutividadeAlta := (" + new DecimalFormat("0.00").format(regras.get(28)).replace(',', '.')
						+ ", 0) (" + new DecimalFormat("0.00").format(regras.get(30)).replace(',', '.') + ",1) ("
						+ new DecimalFormat("0.00").format(regras.get(32)).replace(',', '.') + ",0);");

				saida2.println("TERM ProdutividadeMuitoAlta := ("
						+ new DecimalFormat("0.00").format(regras.get(31)).replace(',', '.') + ", 0) (10.0, 1);");

				// JOptionPane.showMessageDialog(null,"volatilidade!","Aviso", JOptionPane.INFORMATION_MESSAGE);

				saida2.close();

			} catch (IOException e) {
			}

			int listaRegras = 34;
			int controle = 1;
			while (incopia.ready()) {
				String str = incopia.readLine();

				if (str.equals("-;")) {
					if (regras.get(listaRegras) == 1.0) {
						saida.println("ProdutividadeMuitoBaixa;");
					}
					if (regras.get(listaRegras) == 2.0) {
						saida.println("ProdutividadeBaixa;");
					}
					if (regras.get(listaRegras) == 3.0) {
						saida.println("ProdutividadeMedia;");
					}
					if (regras.get(listaRegras) == 4.0) {
						saida.println("ProdutividadeAlta;");
					}
					if (regras.get(listaRegras) == 5.0) {
						saida.println("ProdutividadeMuitoAlta;");
					}

					listaRegras = listaRegras + 1;
					controle = 0;

				}

				if (str.equals("---2;")) {
					BufferedReader inentrada = new BufferedReader(new FileReader("fconhecimento.fcl"));
					while (inentrada.ready()) {
						str = inentrada.readLine();
						if (str.equals("NaN"))
							str = "0.0";
						saida.println(str);
					}
					inentrada.close();
					saida.println("END_FUZZIFY");
					saida.println(" ");
					inentrada = new BufferedReader(new FileReader("fhabilidade.fcl"));
					saida.println("// Fuzzify input variable 'Habilidade'");
					saida.println("FUZZIFY Habilidade");
					while (inentrada.ready()) {
						str = inentrada.readLine();
						if (str.equals("NaN"))
							str = "0.0";
						saida.println(str);
					}

					controle = 0;
					inentrada.close();
				}

				if (str.equals("---3;")) {
					BufferedReader inentrada = new BufferedReader(new FileReader("fatitude.fcl"));
					while (inentrada.ready()) {
						str = inentrada.readLine();
						if (str.equals("NaN"))
							str = "0.0";
						saida.println(str);
					}
					controle = 0;

				}

				if (str.equals("---4;")) {
					BufferedReader inentrada = new BufferedReader(new FileReader("fprodutividade.fcl"));
					while (inentrada.ready()) {
						str = inentrada.readLine();
						if (str.equals("NaN"))
							str = "0.0";
						saida.println(str);
					}
					controle = 0;

				}

				if (controle == 1) {
					saida.println(str);
				}

				controle = 1;

			}
			saida.close();

			incopia.close();

			bw.close();
		} catch (IOException e) {
			Configuration.logger_.severe("Error acceding to the file");
			e.printStackTrace();
		}
	} // printVariablesToFile

	/**
	 * Empties the SolutionSet
	 */
	public void clear() {
		solutionsList_.clear();
	} // clear

	/**
	 * Deletes the <code>Solution</code> at position i in the set.
	 * 
	 * @param i
	 *            The position of the solution to remove.
	 */
	public void remove(int i) {
		if (i > solutionsList_.size() - 1) {
			Configuration.logger_.severe("Size is: " + this.size());
		} // if
		solutionsList_.remove(i);
	} // remove

	/**
	 * Returns an <code>Iterator</code> to access to the solution set list.
	 * 
	 * @return the <code>Iterator</code>.
	 */
	public Iterator<Solution> iterator() {
		return solutionsList_.iterator();
	} // iterator

	/**
	 * Returns a new <code>SolutionSet</code> which is the result of the union
	 * between the current solution set and the one passed as a parameter.
	 * 
	 * @param solutionSet
	 *            SolutionSet to join with the current solutionSet.
	 * @return The result of the union operation.
	 */
	public SolutionSet union(SolutionSet solutionSet) {
		// Check the correct size. In development
		int newSize = this.size() + solutionSet.size();
		if (newSize < capacity_)
			newSize = capacity_;

		// Create a new population
		SolutionSet union = new SolutionSet(newSize);
		for (int i = 0; i < this.size(); i++) {
			union.add(this.get(i));
		} // for

		for (int i = this.size(); i < (this.size() + solutionSet.size()); i++) {
			union.add(solutionSet.get(i - this.size()));
		} // for

		return union;
	} // union

	/**
	 * Replaces a solution by a new one
	 * 
	 * @param position
	 *            The position of the solution to replace
	 * @param solution
	 *            The new solution
	 */
	public void replace(int position, Solution solution) {
		if (position > this.solutionsList_.size()) {
			solutionsList_.add(solution);
		} // if
		solutionsList_.remove(position);
		solutionsList_.add(position, solution);
	} // replace

	/**
	 * Copies the objectives of the solution set to a matrix
	 * 
	 * @return A matrix containing the objectives
	 */
	public double[][] writeObjectivesToMatrix() {
		if (this.size() == 0) {
			return null;
		}
		double[][] objectives;
		objectives = new double[size()][get(0).numberOfObjectives()];
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < get(0).numberOfObjectives(); j++) {
				objectives[i][j] = get(i).getObjective(j);
			}
		}
		return objectives;
	} // writeObjectivesMatrix

} // SolutionSet

