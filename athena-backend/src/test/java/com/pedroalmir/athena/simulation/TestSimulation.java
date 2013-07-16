/**
 * 
 */
package com.pedroalmir.athena.simulation;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.core.system.simulation.Simulation;
import com.pedroalmir.athena.core.system.simulation.SimulationData;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.impl.converter.CSVConverter;
import com.pedroalmir.athena.impl.fuzzy.module.FuzzyModule;

/**
 * @author Pedro Almir
 *
 */
public class TestSimulation {
	@Test
	public void simutationTest(){
		/* Step One: Create system */
		AthenaSystem system = new AthenaSystem("Fuzzy System", "First system by Athena Services");
		/* Step Two: Organize the modules */
		CSVConverter csvConverter = new CSVConverter();
		/* Step Three: Define Inputs and Outputs */
		Input csvInput = new Input("CSV File", "csv_file", new FileType(), "file", false, null);
		Output conhecimento = new Output("Conhecimento", "conhecimento", Real.valueOf(0), "real", false, null);
		Output habilidade = new Output("Habilidade", "habilidade", Real.valueOf(0), "real", false, null);
		Output atitude = new Output("Atitude", "atitude", Real.valueOf(0), "real", false, null);
		Output salario = new Output("Salário", "salario", Real.valueOf(0), "real", false, null);
		/* Add Input and Output, but without values */
		csvConverter.addInput(csvInput);
		csvConverter.addOutput(conhecimento);
		csvConverter.addOutput(habilidade);
		csvConverter.addOutput(atitude);
		csvConverter.addOutput(salario);
		/* Add module to main system */
		system.addModule(csvConverter);
		/* Step Two: Organize the modules */
		FuzzyModule fuzzyModule = new FuzzyModule();
		/* Step Three: Define Inputs and Outputs */
		Input knowledge = new Input("Conhecimento", "knowledge", Real.valueOf(0), "real", false, null);
		Input skill = new Input("Habilidade", "skill", Real.valueOf(0), "real", false, null);
		Input attitude = new Input("Atitude", "attitude", Real.valueOf(0), "real", false, null);
		Output productivity = new Output("Produtividade", "productivity", Real.valueOf(0), "real", false, null);
		/* Setting with value */
		Setting fileSetting = new Setting("Arquivo de Configuração FCL", "fcl_file", new FileType("src/test/resources/fcl/tipper.fcl"), "file", false, null);
		/* Add Input and Output, but without values */
		fuzzyModule.addInput(knowledge);
		fuzzyModule.addInput(skill);
		fuzzyModule.addInput(attitude);
		fuzzyModule.addOutput(productivity);
		fuzzyModule.addSetting(fileSetting);
		/* Add module to main system */
		system.addModule(fuzzyModule);
		/* Step Four: Define the connections (links) */
		system.addLink("Conhecimento to Knowledge", csvConverter, fuzzyModule, conhecimento, knowledge);
		system.addLink("Habilidade to skill", csvConverter, fuzzyModule, habilidade, skill);
		system.addLink("Atitude to attitude", csvConverter, fuzzyModule, atitude, attitude);
		/* SimulationData */
		Simulation simulation = new Simulation("Fuzzy system simulation", system);
		
		List<Input> inputs = new LinkedList<Input>();
		List<Output> outputs = new LinkedList<Output>();
		List<Setting> settings = new LinkedList<Setting>();
		
		csvInput.addValue(new FileType("src/test/resources/csv/atitude.csv"));
		inputs.add(csvInput);
		
		outputs.add(conhecimento);
		outputs.add(habilidade);
		outputs.add(atitude);
		outputs.add(salario);
		
		simulation.addSimulationData(csvConverter, inputs, outputs, null);
		SimulationData data = new SimulationData(bundle, inputs, outputs, settings)
		
	}
}
