package com.pedroalmir.athena.impl.teamAllocation.model;

/**
 * @author Pedro Almir
 *
 */
public class Desenvolvedor {
	
	private String nome;
	private double produtividade;
    private double salario;
    private double atitude;
    private double conhecimento;
    private double habilidade;
    
    /**
     * @param nome
     * @param salario
     * @param atitude
     * @param conhecimento
     * @param habilidade
     */
    public Desenvolvedor(String nome, double salario, double atitude,
			double conhecimento, double habilidade) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.atitude = atitude;
		this.conhecimento = conhecimento;
		this.habilidade = habilidade;
    }
    

	/**
	 * @param nome
	 * @param produtividade
	 * @param salario
	 */
	public Desenvolvedor(String nome, double produtividade, double salario) {
		super();
		this.nome = nome;
		this.produtividade = produtividade;
		this.salario = salario;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Desenvolvedor [nome=" + nome + ", produtividade=" + produtividade + ", salario=" + salario + ", atitude="
				+ atitude + ", conhecimento=" + conhecimento + ", habilidade=" + habilidade + "]";
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}


	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}


	/**
	 * @return the atitude
	 */
	public double getAtitude() {
		return atitude;
	}


	/**
	 * @param atitude the atitude to set
	 */
	public void setAtitude(double atitude) {
		this.atitude = atitude;
	}


	/**
	 * @return the conhecimento
	 */
	public double getConhecimento() {
		return conhecimento;
	}


	/**
	 * @param conhecimento the conhecimento to set
	 */
	public void setConhecimento(double conhecimento) {
		this.conhecimento = conhecimento;
	}


	/**
	 * @return the habilidade
	 */
	public double getHabilidade() {
		return habilidade;
	}


	/**
	 * @param habilidade the habilidade to set
	 */
	public void setHabilidade(double habilidade) {
		this.habilidade = habilidade;
	}

}
