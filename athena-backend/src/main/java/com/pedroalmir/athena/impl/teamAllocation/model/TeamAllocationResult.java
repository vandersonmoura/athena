/**
 * 
 */
package com.pedroalmir.athena.impl.teamAllocation.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Almir
 *
 */
public class TeamAllocationResult {
	/**
	 * 
	 */
	private final long inputFuzzyExecutionTime;
	private final long outputFuzzyExecutionTime;
	private final long nsgaIIExecutionTime;
	
	/**
	 * 
	 */
	private final List<Equipe> equipes;
	private final List<Desenvolvedor> desenvolvedores;
	
	/**
	 * @param inputFuzzyExecutionTime
	 * @param outputFuzzyExecutionTime
	 * @param nsgaIIExecutionTime
	 * @param equipes
	 * @param desenvolvedores
	 */
	public TeamAllocationResult(long inputFuzzyExecutionTime, long outputFuzzyExecutionTime, long nsgaIIExecutionTime,
			List<Equipe> equipes, List<Desenvolvedor> desenvolvedores) {
		super();
		this.inputFuzzyExecutionTime = inputFuzzyExecutionTime;
		this.outputFuzzyExecutionTime = outputFuzzyExecutionTime;
		this.nsgaIIExecutionTime = nsgaIIExecutionTime;
		this.equipes = equipes;
		this.desenvolvedores = desenvolvedores;
	}
	
	/**
	 * @return this result in CSV format
	 */
	public List<String[]> parseToCSV(){
		List<String[]> data = new ArrayList<String[]>();
		
		/* Write header */
		String[] header = new String[this.equipes.size() + 7];
		header[0] = "Nome";
		header[1] = "Produtividade";
		header[2] = "Salario";
		header[3] = "Atitude";
		header[4] = "Conhecimento";
		header[5] = "Habilidade";
		header[6] = "";
		
		for(int i = 7; i < this.equipes.size(); i++){
			header[i] = "Equipe " + (i-6);
		}
		/* Add header */
		data.add(header);
		
		/* Write Body */
		for(Desenvolvedor developer : this.desenvolvedores){
			String[] body = new String[this.equipes.size() + 7];
			body[0] = developer.getNome();
			body[1] = developer.getProdutividade() + "";
			body[2] = developer.getSalario() + "";
			body[3] = developer.getAtitude() + "";
			body[4] = developer.getConhecimento() + "";
			body[5] = developer.getHabilidade() + "";
			body[6] = "";
			
			for(int i = 7; i < this.equipes.size(); i++){
				body[i] = (this.equipes.get(i-7).getDesenvolvedores().contains(developer) ? "Selecionado" : "Nao Selecionado");
			}
			
			data.add(body);
		}
		
		/* Empty Line */
		String[] body = new String[this.equipes.size() + 7];
		for(int i = 0; i < this.equipes.size() + 7; i++){
			body[i] = "";
		}
		data.add(body);
		
		/* Produtividade Line*/
		String[] produtivadeLine = new String[this.equipes.size() + 7];
		for(int i = 0; i < 5; i++){
			produtivadeLine[i] = "";
		}
		produtivadeLine[5] = "Produtividade da Equipe";
		produtivadeLine[6] = "";
		
		/* Custo Line*/
		String[] custoLine = new String[this.equipes.size() + 7];
		for(int i = 0; i < 5; i++){
			custoLine[i] = "";
		}
		custoLine[5] = "Custo da Equipe";
		custoLine[6] = "";
		
		/* Qualidade Line*/
		String[] qualidadeLine = new String[this.equipes.size() + 7];
		for(int i = 0; i < 5; i++){
			qualidadeLine[i] = "";
		}
		qualidadeLine[5] = "Qualidade da Equipe";
		qualidadeLine[6] = "";
		
		for(int i = 7; i < this.equipes.size(); i++){
			produtivadeLine[i] = this.equipes.get(i-7).getProdutividade() + "";
			custoLine[i] = this.equipes.get(i-7).getCusto() + "";
			qualidadeLine[i] = this.equipes.get(i-7).getQualidade() + "";
		}
		
		data.add(produtivadeLine);
		data.add(custoLine);
		data.add(qualidadeLine);
			
		return data;
	}
	
	/**
	 * @return the inputFuzzyExecutionTime
	 */
	public long getInputFuzzyExecutionTime() {
		return inputFuzzyExecutionTime;
	}
	/**
	 * @return the outputFuzzyExecutionTime
	 */
	public long getOutputFuzzyExecutionTime() {
		return outputFuzzyExecutionTime;
	}
	/**
	 * @return the nsgaIIExecutionTime
	 */
	public long getNsgaIIExecutionTime() {
		return nsgaIIExecutionTime;
	}
	/**
	 * @return the equipes
	 */
	public List<Equipe> getEquipes() {
		return equipes;
	}
	/**
	 * @return the desenvolvedores
	 */
	public List<Desenvolvedor> getDesenvolvedores() {
		return desenvolvedores;
	}
}
