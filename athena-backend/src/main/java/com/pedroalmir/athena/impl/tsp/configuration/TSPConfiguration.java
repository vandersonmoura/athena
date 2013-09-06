/**
 * 
 */
package com.pedroalmir.athena.impl.tsp.configuration;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.PutConfiguration;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Int;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.type.string.StringType;

/**
 * @author Pedro Almir
 *
 */
public class TSPConfiguration implements Configuration {

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getInputConfiguration()
	 */
	@Override
	public PutConfiguration getInputConfiguration() {
		PutConfiguration putConfiguration = new PutConfiguration();
		/* Set the minimum of outputs */
		putConfiguration.setMinimum(1);
		/* Set the maximum of outputs */
		putConfiguration.setMaximum(1);
		/* 
		 * This line add FileType as an available type to input configuration.
		 * In this case the value will not be considered.
		 *  
		 * */
		putConfiguration.addAvailableType(new FileType());
		return putConfiguration;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getOutputConfiguration()
	 */
	@Override
	public PutConfiguration getOutputConfiguration() {
		PutConfiguration putConfiguration = new PutConfiguration();
		/* Set the minimum of outputs */
		putConfiguration.setMinimum(1);
		/* Set the maximum of outputs */
		putConfiguration.setMaximum(1);
		/* 
		 * This line add FileType as an available type to input configuration.
		 * In this case the value will not be considered.
		 *  
		 * */
		putConfiguration.addAvailableType(new FileType());
		return putConfiguration;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getSettings()
	 */
	@Override
	public List<Setting> getSettings() {
		List<Setting> settings = new LinkedList<Setting>();
		
        try {
        	Setting populationSize = new Setting("Tamanho da População", "population_size", Int.valueOf(0), "int", false, null, true);
        	Setting maximumEvaluations = new Setting("Número Máximo de Iterações", "maximum_evaluations", Int.valueOf(0), "int", false, null, true);
        	
        	Setting selectionOperator = new Setting("Operador de Seleção", "selection_operator", new StringType(""), "string", false, null, true);
        	
        	Setting crossoverOperator = new Setting("Operador de Crossover", "crossover_operator", new StringType(""), "string", false, null, true);
        	Setting probabilityOfCrossover = new Setting("Probabilidade de Crossover", "probabilityOfCrossover", Real.valueOf(0), "double", false, null, true);
        	
        	Setting mutationOperator = new Setting("Operador de Mutação", "mutation_operator", new StringType(""), "string", false, null, true);
        	Setting probabilityOfMutation = new Setting("Probabilidade de Mutação", "probabilityOfMutation", Real.valueOf(0), "double", false, null, true);
        	
        	settings.add(populationSize);
        	settings.add(maximumEvaluations);
        	settings.add(selectionOperator);
        	settings.add(crossoverOperator);
        	settings.add(mutationOperator);
        	settings.add(probabilityOfCrossover);
        	settings.add(probabilityOfMutation);
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
		return settings;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#hasSettings()
	 */
	@Override
	public boolean hasSettings() {
		return true;
	}

}
