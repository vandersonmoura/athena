/**
 * 
 */
package com.pedroalmir.athena.impl.nsga.configuration;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.PutConfiguration;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.type.numeric.Int;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.type.string.StringType;

/**
 * @author Pedro Almir
 *
 */
public class NSGAIIConfiguration implements Configuration {

	public PutConfiguration getInputConfiguration() {
		PutConfiguration putConfiguration = new PutConfiguration();
		/* Define minimum and maximum of inputs */
		putConfiguration.setMinimum(2);
		putConfiguration.setMaximum(2);
		/* Define available types */
		putConfiguration.addAvailableType(Int.valueOf(0));
		putConfiguration.addAvailableType(Real.valueOf(0));
		return putConfiguration;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getOutputConfiguration()
	 */
	
	public PutConfiguration getOutputConfiguration() {
		PutConfiguration putConfiguration = new PutConfiguration();
		/* Define minimum and maximum of inputs */
		putConfiguration.setMinimum(2);
		putConfiguration.setMaximum(2);
		/* Define available types */
		putConfiguration.addAvailableType(Int.valueOf(0));
		putConfiguration.addAvailableType(Real.valueOf(0));
		return putConfiguration;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getSettings()
	 */
	public List<Setting> getSettings() {
		List<Setting> settings = new LinkedList<Setting>();
		
        try {
        	Setting populationSize = new Setting("Tamanho da População", "population_size", Int.valueOf(0), "int", false, null, true);
        	Setting maximumEvaluations = new Setting("Número Máximo de Iterações", "maximum_evaluations", Int.valueOf(0), "int", false, null, true);
        	Setting restriction = new Setting("Restrição (Tamanho da Equipe)", "team_size", Int.valueOf(0), "int", false, null, true);
        	
        	Setting selectionOperator = new Setting("Operador de Seleção", "selection_operator", new StringType(""), "string", false, null, true);
        	
        	Setting crossoverOperator = new Setting("Operador de Crossover", "crossover_operator", new StringType(""), "string", false, null, true);
        	Setting probabilityOfCrossover = new Setting("Probabilidade de Crossover", "probabilityOfCrossover", Real.valueOf(0), "double", false, null, true);
        	
        	Setting mutationOperator = new Setting("Operador de Mutação", "mutation_operator", new StringType(""), "string", false, null, true);
        	Setting probabilityOfMutation = new Setting("Probabilidade de Mutação", "probabilityOfMutation", Real.valueOf(0), "double", false, null, true);
        	
        	//Setting problem = new Setting("Problema", "problem", new StringType(""), "string", false, null, true);
        	
        	settings.add(populationSize);
        	settings.add(maximumEvaluations);
        	settings.add(restriction);
        	
        	settings.add(selectionOperator);
        	settings.add(crossoverOperator);
        	settings.add(mutationOperator);
        	settings.add(probabilityOfCrossover);
        	settings.add(probabilityOfMutation);
        	//settings.add(problem);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return settings;
	}

	
	public boolean hasSettings() {
		return true;
	}

}
