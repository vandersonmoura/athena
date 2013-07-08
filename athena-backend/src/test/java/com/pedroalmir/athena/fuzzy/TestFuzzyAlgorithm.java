/**
 * 
 */
package com.pedroalmir.athena.fuzzy;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.impl.fuzzy.algorithm.FuzzyAlgorithm;
import com.pedroalmir.athena.impl.fuzzy.stoppingCondition.FuzzyCompleteEvaluationStoppingCondition;

/**
 * @author Pedro Almir
 *
 */
public class TestFuzzyAlgorithm {
	
	@Test
	public void testFuzzyAlgorithm(){
		List<Input> inputs = new LinkedList<Input>();
		List<Output> outputs = new LinkedList<Output>();
		List<Setting> settings = new LinkedList<Setting>();
		/* Create inputs */
		Input inputService = new Input("Qualidade do Serviço", "service", Real.valueOf(0), "real", false, null);
		Input inputFood = new Input("Qualidade da Comida", "food", Real.valueOf(0), "real", false, null);
		
		inputService.addValue(Real.valueOf(3));
		inputFood.addValue(Real.valueOf(7));
		
		inputs.add(inputService);
		inputs.add(inputFood);
		/* Create outputs */
		Output outputTip = new Output("Valor da gorgeta", "tip", Real.valueOf(0), "real", false, null);
		outputs.add(outputTip);
		/* Create settings */
		Setting fileSetting = new Setting("Arquivo de Configuração FCL", "fcl_file", new FileType("src/test/resources/fcl/tipper.fcl"), "file", false, null);
		settings.add(fileSetting);
		
		FuzzyAlgorithm fuzzyAlgorithm = new FuzzyAlgorithm(inputs, outputs, settings);
		fuzzyAlgorithm.addStoppingCondition(new FuzzyCompleteEvaluationStoppingCondition(inputs));
		fuzzyAlgorithm.run();
		
		Assert.assertNotNull(fuzzyAlgorithm.getSolutions().iterator());
		Assert.assertEquals(fuzzyAlgorithm.getSolutions().iterator().next().getFitness().getValue(), new Double(11.701603788948043));
	}
}
