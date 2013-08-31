/**
 * 
 */
package com.pedroalmir.athena.impl.teamAllocation.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Pedro Almir
 *
 */
public class Equipe {
	/**
	 * 
	 */
	private List<Desenvolvedor> desenvolvedores;
	/**
	 * 
	 */
	private double custo;
	/**
	 * 
	 */
	private double produtividade;
	/**
	 * 
	 */
	private double qualidade;
	
	/**
	 * 
	 */
	public Equipe() {
		this.desenvolvedores = new LinkedList<Desenvolvedor>();
		this.custo = 0.0;
		this.produtividade = 0.0;
		this.qualidade = 0.0;
	}
	
	/**
	 * @param desenvolvedores
	 * @param custo
	 * @param produtividade
	 */
	public Equipe(List<Desenvolvedor> desenvolvedores, double custo, double produtividade, double qualidade) {
		super();
		this.desenvolvedores = desenvolvedores;
		this.custo = custo;
		this.produtividade = produtividade;
		this.qualidade = qualidade;
	}
	
	/**
	 * @param desenvolvedor
	 */
	public void addDesenvolvedor(Desenvolvedor desenvolvedor){
		if(this.getDesenvolvedores() != null){
			this.getDesenvolvedores().add(desenvolvedor);
		}
	}
	
	/**
	 * @return the desenvolvedores
	 */
	public List<Desenvolvedor> getDesenvolvedores() {
		return desenvolvedores;
	}
	/**
	 * @param desenvolvedores the desenvolvedores to set
	 */
	public void setDesenvolvedores(List<Desenvolvedor> desenvolvedores) {
		this.desenvolvedores = desenvolvedores;
	}
	/**
	 * @return the custo
	 */
	public double getCusto() {
		return custo;
	}
	/**
	 * @param custo the custo to set
	 */
	public void setCusto(double custo) {
		this.custo = custo;
	}
	/**
	 * @return the produtividade
	 */
	public double getProdutividade() {
		return produtividade;
	}
	/**
	 * @param produtividade the produtividade to set
	 */
	public void setProdutividade(double produtividade) {
		this.produtividade = produtividade;
	}

	/**
	 * @return the qualidade
	 */
	public double getQualidade() {
		return qualidade;
	}

	/**
	 * @param qualidade the qualidade to set
	 */
	public void setQualidade(double qualidade) {
		this.qualidade = qualidade;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		List<String> nomes = new ArrayList<String>(desenvolvedores.size());
		for(Desenvolvedor d : desenvolvedores){
			nomes.add(d.getNome());
		}
		String nomeUnidos = StringUtils.join(nomes, ",");
		return "Equipe [desenvolvedores=[" + nomeUnidos + "], custo=" + custo + ", produtividade=" + produtividade + ", qualidade=" + qualidade + "]";
	}
}
