package jmetal.problems.singleObjective;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import jmetal.base.Problem;
import jmetal.base.Solution;
import jmetal.base.Variable;
import jmetal.base.solutionType.BinaryRealSolutionType;
import jmetal.base.solutionType.RealSolutionType;
import jmetal.util.JMException;
import net.sourceforge.jFuzzyLogic.FIS;

public class Pro1 extends Problem {
	/**
	 * Constructor
	 * Creates a default instance of the Griewank problem
	 * 
	 * @param numberOfVariables
	 *            Number of variables of the problem
	 * @param solutionType
	 *            The solution type must "Real" or "BinaryReal".
	 */
	public Pro1(String solutionType, Integer numberOfVariables) throws ClassNotFoundException {
		numberOfVariables_ = numberOfVariables;
		numberOfObjectives_ = 1;
		numberOfConstraints_ = 0;
		problemName_ = "Sphere";

		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];
		for (int var = 0; var < 7; var++) {

			if (var == 0) {
				lowerLimit_[var] = 0.0;
				upperLimit_[var] = 0.0;
				lowerLimit_[var + 7] = 0.0;
				upperLimit_[var + 7] = 0.0;
				lowerLimit_[var + 14] = 0.0;
				upperLimit_[var + 14] = 0.0;

			}

			if (var == 1) {
				lowerLimit_[var] = 0.01;
				upperLimit_[var] = 3.0;
				lowerLimit_[var + 7] = 0.01;
				upperLimit_[var + 7] = 3.0;
				lowerLimit_[var + 14] = 0.01;
				upperLimit_[var + 14] = 3.0;

			}

			if (var == 2) {
				lowerLimit_[var] = 3.01;
				upperLimit_[var] = 4.0;
				lowerLimit_[var + 7] = 3.01;
				upperLimit_[var + 7] = 4.0;
				lowerLimit_[var + 14] = 3.01;
				upperLimit_[var + 14] = 4.0;

			}

			if (var == 3) {
				lowerLimit_[var] = 4.01;
				upperLimit_[var] = 5.0;
				lowerLimit_[var + 7] = 4.01;
				upperLimit_[var + 7] = 5.0;
				lowerLimit_[var + 14] = 4.01;
				upperLimit_[var + 14] = 5.0;

			}

			if (var == 4) {
				lowerLimit_[var] = 5.01;
				upperLimit_[var] = 7.0;
				lowerLimit_[var + 7] = 5.01;
				upperLimit_[var + 7] = 7.0;
				lowerLimit_[var + 14] = 5.01;
				upperLimit_[var + 14] = 7.0;

			}

			if (var == 5) {
				lowerLimit_[var] = 7.01;
				upperLimit_[var] = 7.0;
				lowerLimit_[var + 7] = 7.01;
				upperLimit_[var + 7] = 8.0;
				lowerLimit_[var + 14] = 7.01;
				upperLimit_[var + 14] = 8.0;

			}

			if (var == 6) {
				lowerLimit_[var] = 10.0;
				upperLimit_[var] = 10.0;
				lowerLimit_[var + 7] = 10.0;
				upperLimit_[var + 7] = 10.0;
				lowerLimit_[var + 14] = 10.0;
				upperLimit_[var + 14] = 10.0;

			}

			lowerLimit_[21] = 0.0;
			upperLimit_[21] = 0.0;
			lowerLimit_[22] = 0.01;
			upperLimit_[22] = 0.5;
			lowerLimit_[23] = 0.51;
			upperLimit_[23] = 1.5;
			lowerLimit_[24] = 1.51;
			upperLimit_[24] = 2.0;
			lowerLimit_[25] = 2.01;
			upperLimit_[25] = 2.5;
			lowerLimit_[26] = 2.51;
			upperLimit_[26] = 3.5;
			lowerLimit_[27] = 3.51;
			upperLimit_[27] = 4.0;
			lowerLimit_[28] = 4.01;
			upperLimit_[28] = 4.5;
			lowerLimit_[29] = 4.51;
			upperLimit_[29] = 5.5;
			lowerLimit_[30] = 5.51;
			upperLimit_[30] = 6.0;
			lowerLimit_[31] = 6.01;
			upperLimit_[31] = 7.5;
			lowerLimit_[32] = 7.51;
			upperLimit_[32] = 8.5;
			lowerLimit_[33] = 10.0;
			upperLimit_[33] = 10.0;

		} // for

		for (int var = 34; var < numberOfVariables_; var++) {
			lowerLimit_[var] = 1.0;
			upperLimit_[var] = 5.0;

			// lowerLimit_[1] = 0.0;
			// upperLimit_[1] = 10.0;
		} // for

		if (solutionType.compareTo("BinaryReal") == 0)
			solutionType_ = new BinaryRealSolutionType(this);
		else if (solutionType.compareTo("Real") == 0)
			solutionType_ = new RealSolutionType(this);
		else {
			System.out.println("Error: solution type " + solutionType + " invalid");
			System.exit(-1);
		}
	} // Griewank

	/**
	 * Evaluates a solution
	 * 
	 * @param solution
	 *            The solution to evaluate
	 * @throws JMException
	 */
	// @Override
	@Override
	public void evaluate(Solution solution) throws JMException {
		Variable[] decisionVariables = solution.getDecisionVariables();
		/**
		 * double sum = 0.0 ;
		 * double mult = 1.0 ;
		 * double d = 4000.0 ;
		 * double x = decisionVariables[0].getValue();
		 * double y = decisionVariables[1].getValue();
		 */

		String conhecimento = "";
		String habilidade = "";
		float avaliacao = 0;
		String atitude = "";
		String produtividade = "";
		float erros = 0;
		Date fim = null;
		long tempo = 0;
		BufferedReader in = null;
		BufferedReader in2 = null;
		BufferedReader in3 = null;
		float diferenca = 0;
		BufferedReader in4 = null;
		float erroTotal = 0;

		for (int correcao = 0; correcao < 34; correcao++) {
			if (Double.toString(decisionVariables[correcao].getValue()).equals("NaN") && correcao != 0 && correcao != 7
					&& correcao != 14 && correcao != 21)
				decisionVariables[correcao].setValue(decisionVariables[correcao - 1].getValue() + 0.5);
		}

		try {

			PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fconhecimento.fcl"), "UTF-8"));

			saida2.println("TERM ConhecimentoPouca := (0.0, 1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[2].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM ConhecimentoMedia := ("
					+ new DecimalFormat("0.00").format(decisionVariables[1].getValue()).replace(',', '.') + ", 0) ("
					+ new DecimalFormat("0.00").format(decisionVariables[3].getValue()).replace(',', '.') + ",1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[5].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM ConhecimentoMuita := ("
					+ new DecimalFormat("0.00").format(decisionVariables[4].getValue()).replace(',', '.') + ", 0) (10.0, 1);");

			saida2.close();
		} catch (IOException e) {
		}

		try {

			PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fhabilidade.fcl"), "UTF-8"));

			if (Double.toString(decisionVariables[7].getValue()).equals("NaN"))
				decisionVariables[7].setValue(0.0);

			saida2.println("TERM HabilidadePouca := (0.0, 1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[9].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM HabilidadeMedia := ("
					+ new DecimalFormat("0.00").format(decisionVariables[8].getValue()).replace(',', '.') + ", 0) ("
					+ new DecimalFormat("0.00").format(decisionVariables[10].getValue()).replace(',', '.') + ",1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[12].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM HabilidadeMuita := ("
					+ new DecimalFormat("0.00").format(decisionVariables[11].getValue()).replace(',', '.') + ", 0) (10.0, 1);");

			// JOptionPane.showMessageDialog(null,"complexidade!","Aviso", JOptionPane.INFORMATION_MESSAGE);

			saida2.close();
		} catch (IOException e) {
		}

		try {

			PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fatitude.fcl"), "UTF-8"));

			saida2.println("TERM AtitudeRuim := (0.0, 1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[16].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM AtitudeRegular := ("
					+ new DecimalFormat("0.00").format(decisionVariables[15].getValue()).replace(',', '.') + ", 0) ("
					+ new DecimalFormat("0.00").format(decisionVariables[17].getValue()).replace(',', '.') + ",1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[19].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM AtitudeBoa := ("
					+ new DecimalFormat("0.00").format(decisionVariables[18].getValue()).replace(',', '.') + ", 0) (10.0, 1);");

			// JOptionPane.showMessageDialog(null,"volatilidade!","Aviso", JOptionPane.INFORMATION_MESSAGE);

			saida2.close();
		} catch (IOException e) {
		}

		try {

			PrintWriter saida2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("fprodutividade.fcl"), "UTF-8"));

			saida2.println("TERM ProdutividadeMuitoBaixa := (0.0, 1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[23].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM ProdutividadeBaixa := ("
					+ new DecimalFormat("0.00").format(decisionVariables[22].getValue()).replace(',', '.') + ", 0) ("
					+ new DecimalFormat("0.00").format(decisionVariables[24].getValue()).replace(',', '.') + ",1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[26].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM ProdutividadeMedia := ("
					+ new DecimalFormat("0.00").format(decisionVariables[25].getValue()).replace(',', '.') + ", 0) ("
					+ new DecimalFormat("0.00").format(decisionVariables[27].getValue()).replace(',', '.') + ",1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[28].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM ProdutividadeAlta := ("
					+ new DecimalFormat("0.00").format(decisionVariables[28].getValue()).replace(',', '.') + ", 0) ("
					+ new DecimalFormat("0.00").format(decisionVariables[30].getValue()).replace(',', '.') + ",1) ("
					+ new DecimalFormat("0.00").format(decisionVariables[32].getValue()).replace(',', '.') + ",0);");

			saida2.println("TERM ProdutividadeMuitoAlta := ("
					+ new DecimalFormat("0.00").format(decisionVariables[31].getValue()).replace(',', '.') + ", 0) (10.0, 1);");

			// JOptionPane.showMessageDialog(null,"volatilidade!","Aviso", JOptionPane.INFORMATION_MESSAGE);

			saida2.close();

		} catch (IOException e) {
		}

		try {
			in = new BufferedReader(new FileReader("conhecimento.csv"));
			in2 = new BufferedReader(new FileReader("habilidade.csv"));
			in3 = new BufferedReader(new FileReader("atitude.csv"));
			in4 = new BufferedReader(new FileReader("produtividade.csv"));

			for (int leitura = 0; leitura < 200; leitura++) {

				BufferedReader incopia = new BufferedReader(new FileReader("tipper.fcl"));
				PrintWriter saida = new PrintWriter(new OutputStreamWriter(new FileOutputStream("tipperGA2.fcl"), "UTF-8"));
				String str;
				int listaRegras = 34;
				int controle = 1;
				while (incopia.ready()) {
					str = incopia.readLine();

					if (str.equals("-;")) {
						if ((java.lang.Math.round(decisionVariables[listaRegras].getValue())) == 1.0) {
							saida.println("ProdutividadeMuitoBaixa;");
						}
						if ((java.lang.Math.round(decisionVariables[listaRegras].getValue())) == 2.0) {
							saida.println("ProdutividadeBaixa;");
						}
						if ((java.lang.Math.round(decisionVariables[listaRegras].getValue())) == 3.0) {
							saida.println("ProdutividadeMedia;");
						}
						if ((java.lang.Math.round(decisionVariables[listaRegras].getValue())) == 4.0) {
							saida.println("ProdutividadeAlta;");
						}
						if ((java.lang.Math.round(decisionVariables[listaRegras].getValue())) == 5.0) {
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
				// inentrada.close();

				conhecimento = in.readLine();
				habilidade = in2.readLine();
				atitude = in3.readLine();
				produtividade = in4.readLine();

				String fileName = "tipperGA2.fcl";
				FIS fis = null;

				fis = FIS.load(fileName, true);
				// Error while loading?
				while (fis == null) {
					// System.err.println("Can't load file: '"
					// + fileName + "'");
					// return;
					fis = FIS.load(fileName, true);
				}

				fis.setVariable("Conhecimento", Double.parseDouble(conhecimento));
				fis.setVariable("Habilidade", Double.parseDouble(habilidade));
				fis.setVariable("Atitude", Double.parseDouble(atitude));

				// Evaluate
				fis.evaluate();

				diferenca = (float) (fis.getVariable("Produtividade").getValue() - Double.parseDouble(produtividade));

				diferenca = diferenca / (float) Double.parseDouble(produtividade);
				diferenca = diferenca * diferenca;
				// erros = erros + ((diferenca/(float)fis.getVariable("Produtividade").getValue()) *
				// (diferenca/(float)fis.getVariable("Produtividade").getValue()));

				fis = null;

				File f = new File("tipperGA2.fcl");
				saida.flush();
				f.delete();

				erroTotal = erroTotal + diferenca;

			}
			// saida.close();

			in.close();
			in2.close();
			in3.close();
			in4.close();

		} catch (IOException ex) {
			Logger.getLogger(Pro1.class.getName()).log(Level.SEVERE, null, ex);
		}

		// erros = erros / 21;
		// avaliacao = diferenca;

		erroTotal = erroTotal / 200;

		solution.setObjective(0, erroTotal);
	} // evaluate
} // Griewank