/**
 * 
 */
package com.pedroalmir.athena.impl.teamAllocation.controller;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jmetal.metaheuristics.nsgaII.NSGAII_main;
import jmetal.util.JMException;
import net.sourceforge.jFuzzyLogic.FIS;

import com.pedroalmir.athena.impl.teamAllocation.model.Desenvolvedor;
import com.pedroalmir.athena.impl.teamAllocation.model.NSGAIIResult;
import com.pedroalmir.athena.impl.teamAllocation.model.TeamAllocationResult;

/**
 * @author Pedro Almir
 *
 */
public class TeamAllocationApproach {
	
	//private static List<Desenvolvedor> desenvolvedores = Arquivo.load("dados/csv/Candidatos.csv");
	@SuppressWarnings("unused")
	private static final String INPUT_FCL_FILE_PATH = "dados/fcl/tipperEntrada.fcl";
	@SuppressWarnings("unused")
	private static final String OUTPUT_FCL_FILE_PATH = "dados/fcl/tipperSaida3.fcl";
	@SuppressWarnings("unused")
	private static final String BASE_FOLDER_NSGAII = "dados/nsgaII/";
	@SuppressWarnings("unused")
	private static final String BASE_FOLDER_FUZZY = "dados/fuzzy/";
	@SuppressWarnings("unused")
	private static final int TAMANHO_EQUIPE = 3;
	@SuppressWarnings("unused")
	private static final String FORMATTED_CSV_PATH = "mainResult.csv";
	
//	public static void main(String[] args) throws IOException {
//		TeamAllocationResult execute = new TeamAllocationApproach().execute(INPUT_FCL_FILE_PATH, OUTPUT_FCL_FILE_PATH, desenvolvedores, 100, 25000, 3, false);
//		
//		File file = new File(FORMATTED_CSV_PATH);
//		file.createNewFile();
//		CSVWriter csvWriter = new CSVWriter(new FileWriter(file), ';');
//		csvWriter.writeAll(execute.parseToCSV());
//		csvWriter.close();
//	}
	
	public TeamAllocationResult execute(String inputFCLFilePath, String outputFCLFilePath, 
			List<Desenvolvedor> desenvolvedores, int populationSize, int maxEvaluations, int tamanhoDaEquipe, boolean debug){
		/* 1. Execute input fuzzy */
		System.out.println("Avaliando desenvolvedores...");

		long begin = System.currentTimeMillis();
		FIS firstFis = FIS.load(inputFCLFilePath, true);
		
		for (Desenvolvedor temp : desenvolvedores) {
			/* Set inputs */
			firstFis.setVariable("Conhecimento", temp.getConhecimento());
			firstFis.setVariable("Atitude", temp.getAtitude());
			firstFis.setVariable("Habilidade", temp.getHabilidade());
			
			/* Evaluate */
			firstFis.evaluate();
			
			/*Transformando um double em 2 casas decimais*/  
		    DecimalFormat fmt = new DecimalFormat("0.00");
		    double produtividade = firstFis.getVariable("Produtividade").getLatestDefuzzifiedValue();
		    produtividade = Double.valueOf(fmt.format(produtividade).replaceAll(",", "."));
		    
			temp.setProdutividade(produtividade);
			/* Just for debug */
			if(debug){
				System.out.println(temp);
			}
		}
		long inputFuzzyExecutionTime = System.currentTimeMillis() - begin;
		
		/* 2. Execute NSGA-II */
		System.out.println("Gerando Equipes...");
		NSGAIIResult nsgaiiResult = null;
		long beginNSGAII = System.currentTimeMillis();
		
		for (int i = 0; i < 10; i++) {
			/* Just for debug */
			if(debug){
				System.out.println("Avaliação " + i);
			}
			try {
				nsgaiiResult = NSGAII_main.execute(desenvolvedores, tamanhoDaEquipe, populationSize, maxEvaluations);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (JMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		long time = System.currentTimeMillis() - beginNSGAII;
		nsgaiiResult.setExecutionTime(time);
		/* 3. Execute output fuzzy */
		System.out.println("Qualificando Equipes...");
		
		List<Double> objetivo1 = nsgaiiResult.getObjetivos().get("Produtividade");
		List<Double> objetivo2 = nsgaiiResult.getObjetivos().get("Custo");
		
		long beginFinal = System.currentTimeMillis();
		FIS lastFis = FIS.load(outputFCLFilePath, true);
		ArrayList<Double> qualidadeDasEquipes = new ArrayList<Double>();
		
		for (int i = 0; i < objetivo1.size(); i++) {
			/* Set inputs */
			lastFis.setVariable("Produtividade", objetivo1.get(i));
			lastFis.setVariable("Custo", objetivo2.get(i));
			
			/* Evaluate */
			lastFis.evaluate();
			
			/*Transformando um double em 2 casas decimais*/  
		    DecimalFormat fmt = new DecimalFormat("0.00");
		    double qualidade = lastFis.getVariable("Qualidade").getLatestDefuzzifiedValue();
		    qualidade = Double.valueOf(fmt.format(qualidade).replaceAll(",", "."));
			
			qualidadeDasEquipes.add(qualidade);
			nsgaiiResult.getEquipes().get(i).setQualidade(qualidade);
		}
		long outputFuzzyExecutionTime = System.currentTimeMillis() - beginFinal;
		
		/*  
		System.out.println("Processo Finalizado!");
		if(debug){
			System.out.println("Equipes Geradas:\n");
			for(Equipe e : nsgaiiResult.getEquipes()){
				System.out.println(e.toString());
			}
		}
		*/
		return new TeamAllocationResult(inputFuzzyExecutionTime, outputFuzzyExecutionTime, nsgaiiResult.getExecutionTime(), nsgaiiResult.getEquipes(), desenvolvedores);
	}
	
//	public static void maina(String[] args) {
//		/* 1. Execute input fuzzy */
//		System.out.println("Avaliando desenvolvedores...");
//		FIS firstFis = FIS.load(INPUT_FCL_FILE_PATH, true);
//		
//		for (Desenvolvedor temp : desenvolvedores) {
//			/* Set inputs */
//			firstFis.setVariable("Conhecimento", temp.getConhecimento());
//			firstFis.setVariable("Atitude", temp.getAtitude());
//			firstFis.setVariable("Habilidade", temp.getHabilidade());
//			
//			/* Evaluate */
//			firstFis.evaluate();
//			
//			/*Transformando um double em 2 casas decimais*/  
//		    DecimalFormat fmt = new DecimalFormat("0.00");
//		    double produtividade = firstFis.getVariable("Produtividade").getLatestDefuzzifiedValue();
//		    produtividade = Double.valueOf(fmt.format(produtividade).replaceAll(",", "."));
//		    
//			temp.setProdutividade(produtividade);
//			/* Just for debug */
//			System.out.println(temp);
//		}
//		
//		/* 2. Execute NSGA-II */
//		System.out.println("Gerando Equipes...");
//		NSGAIIResult nsgaiiResult = null;
//		for (int i = 0; i < 30; i++) {
//			/* Just for debug */
//			//System.out.println("Avaliação " + i);
//			try {
//				nsgaiiResult = NSGAII_main.execute(desenvolvedores, TAMANHO_EQUIPE, BASE_FOLDER_NSGAII, false, 100, 25000);
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (JMException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		/* 3. Execute output fuzzy */
//		System.out.println("Qualificando Equipes...");
//		
//		List<Double> objetivo1 = nsgaiiResult.getObjetivos().get("Produtividade");
//		List<Double> objetivo2 = nsgaiiResult.getObjetivos().get("Custo");
//		
//		FIS lastFis = FIS.load(OUTPUT_FCL_FILE_PATH, true);
//		ArrayList<Double> qualidadeDasEquipes = new ArrayList<Double>();
//		
//		for (int i = 0; i < objetivo1.size(); i++) {
//			/* Set inputs */
//			lastFis.setVariable("Produtividade", objetivo1.get(i));
//			lastFis.setVariable("Custo", objetivo2.get(i));
//			
//			/* Evaluate */
//			lastFis.evaluate();
//			
//			/*Transformando um double em 2 casas decimais*/  
//		    DecimalFormat fmt = new DecimalFormat("0.00");
//		    double qualidade = lastFis.getVariable("Qualidade").getLatestDefuzzifiedValue();
//		    qualidade = Double.valueOf(fmt.format(qualidade).replaceAll(",", "."));
//			
//			qualidadeDasEquipes.add(qualidade);
//			nsgaiiResult.getEquipes().get(i).setQualidade(qualidade);
//		}
//		
//		/* Save result */
//		//Arquivo.save(BASE_FOLDER_FUZZY + "Qualidade.csv", qualidadeDasEquipes);
//		System.out.println("Processo Finalizado!");
//		System.out.println("Equipes Geradas:\n");
//		
//		for(Equipe e : nsgaiiResult.getEquipes()){
//			System.out.println(e.toString());
//		}
//	}
}
