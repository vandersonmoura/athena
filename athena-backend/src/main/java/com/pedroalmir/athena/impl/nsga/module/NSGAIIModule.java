/**
 * 
 */
package com.pedroalmir.athena.impl.nsga.module;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jmetal.metaheuristics.nsgaII.NSGAII_main;
import jmetal.util.JMException;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.component.AbstractBundle;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.core.type.numeric.Int;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.util.PutUtils;
import com.pedroalmir.athena.impl.nsga.configuration.NSGAIIConfiguration;
import com.pedroalmir.athena.impl.teamAllocation.model.Desenvolvedor;
import com.pedroalmir.athena.impl.teamAllocation.model.Equipe;
import com.pedroalmir.athena.impl.teamAllocation.model.NSGAIIResult;

/**
 * @author Pedro Almir
 *
 */
public class NSGAIIModule extends AbstractBundle implements GenericModule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6303466590064606317L;
	/**
	 * 
	 */
	private boolean loaded;
	
	public String getName() {
		return "NSGA II Algorithm Module";
	}

	public String getShortName() {
		return "NSGA-II";
	}

	public String getDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		/* TODO: Create NSGA II description */
		return buffer.toString();
	}

	public String getImagePath() {
		return "";
	}

	public List<Input> getInputs() {
		return this.inputs;
	}

	
	public List<Output> getOutputs() {
		return this.outputs;
	}

	
	public List<Setting> getSettings() {
		return this.settings;
	}

	@Override
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	@Override
	public Configuration getConfiguration() {
		return new NSGAIIConfiguration();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(AthenaBundle bundle) {
		if (this == bundle)
			return true;
		if (bundle == null)
			return false;
		if (getClass() != bundle.getClass())
			return false;
		NSGAIIModule other = (NSGAIIModule) bundle;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputs == null) {
			if (other.inputs != null)
				return false;
		} else if (!inputs.equals(other.inputs))
			return false;
		if (outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!outputs.equals(other.outputs))
			return false;
		if (settings == null) {
			if (other.settings != null)
				return false;
		} else if (!settings.equals(other.settings))
			return false;
		return true;
	}

	@Override
	public Algorithm getAlgorithm() {
		return null;
	}

	@Override
	public boolean isOptimizationAlgorithm() {
		return true;
	}

	@Override
	public void load(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		this.executionLog.appendLogLine("Carregando módulo NSGA-II...");
		this.inputs =  inputs;
		this.outputs = outputs;
		this.settings = settings;
		
		this.loaded = true;
		this.executionLog.appendLogLine("Módulo NSGA-II carregado com sucesso...");
	}

	@Override
	public void load() {
		this.executionLog.appendLogLine("Carregando módulo NSGA-II...");
		this.loaded = true;
		this.executionLog.appendLogLine("Módulo NSGA-II carregado com sucesso...");
	}

	@Override
	public boolean isLoaded() {
		return this.loaded;
	}

	@Override
	public boolean isPublic() {
		return true;
	}

	@Override
	public List<Solution> run() {
		/* 2. Execute NSGA-II */
		try {
			long beginNSGAII = System.currentTimeMillis();
			this.executionLog.appendLogLine("Início da execução do NSGA-II");
			NSGAIIResult nsgaiiResult = null;
			
			this.executionLog.appendLogLine("Configurando o problema...");
			Setting restriction =  PutUtils.findSetting("team_size", settings);
			Setting populationSize =  PutUtils.findSetting("population_size", settings);
			Setting maximumEvaluations =  PutUtils.findSetting("maximum_evaluations", settings);
			this.executionLog.setIterations((Integer) ((Int) maximumEvaluations.getType()).getValue());
			
			List<Desenvolvedor> desenvolvedores = new LinkedList<Desenvolvedor>();
			Desenvolvedor desenvolvedor = null; 
			for(int i = 0; i < this.inputs.get(0).getValues().size(); i++){
				desenvolvedor = new Desenvolvedor("Desenvolvedor " + i+1, 
						((Double) this.inputs.get(0).getValues().get(i).getValue()), 
						((Double) this.inputs.get(1).getValues().get(i).getValue()));
				desenvolvedores.add(desenvolvedor);
			}
			
			this.executionLog.appendLogLine("Gerando Equipes...");
			nsgaiiResult = NSGAII_main.execute(desenvolvedores, 
					(Integer) ((Int) restriction.getType()).getValue(), 
					(Integer) ((Int) populationSize.getType()).getValue(), 
					(Integer) ((Int) maximumEvaluations.getType()).getValue());
			
			this.executionLog.appendLogLine("Equipes geradas com sucesso!");
			int count = 1;
			for(Equipe equipe : nsgaiiResult.getEquipes()){
				
				this.executionLog.appendLogLine("Equipe " + (count++) + ": [" + StringUtils.join(equipe.getNames(), ", ") + "] "
						+ "Custo Total: " + equipe.getCusto() + " - Produtividade Total: " + equipe.getProdutividade());
				
				if(this.outputs.get(0).getName().equalsIgnoreCase("custo")){
					this.outputs.get(0).addValue(Real.valueOf(equipe.getCusto()));
					this.outputs.get(1).addValue(Real.valueOf(equipe.getProdutividade()));
				}else{
					this.outputs.get(1).addValue(Real.valueOf(equipe.getCusto()));
					this.outputs.get(0).addValue(Real.valueOf(equipe.getProdutividade()));
				}
			}
			
			long time = System.currentTimeMillis() - beginNSGAII;
			nsgaiiResult.setExecutionTime(time);
			this.executionLog.appendLogLine("Fim da execução do algoritmo...");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (JMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
