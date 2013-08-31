/**
 * 
 */
package com.pedroalmir.athena.simulation;

import org.junit.Test;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.impl.converter.FromCSVConverter;
import com.pedroalmir.athena.impl.converter.ToCSVConverter;
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
		FromCSVConverter csvConverter = new FromCSVConverter();
		/* Step Three: Define Inputs and Outputs */
		Input csvInput = new Input("CSV File", "csv_file", new FileType(), "file", false, null);
		csvInput.addValue(new FileType("src/test/resources/csv/candidatos.csv"));
		
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
		Setting fileSetting = fuzzyModule.findSetting("fcl_file");
		if(fileSetting != null){
			fileSetting.getType().setValue("src/test/resources/fcl/tipperEntrada.fcl");
		}
		
		/* Add Input and Output, but without values */
		fuzzyModule.addInput(knowledge);
		fuzzyModule.addInput(skill);
		fuzzyModule.addInput(attitude);
		fuzzyModule.addOutput(productivity);
		fuzzyModule.addSetting(fileSetting);
		
		/* Add module to main system */
		system.addModule(fuzzyModule);
		
		ToCSVConverter finalConverter = new ToCSVConverter();
		
		Input productivity_result = new Input("Resultado da Produtividade", "productivity_result", Real.valueOf(0), "real", false, null);
		finalConverter.addInput(productivity_result);
		
		Output result_file = new Output("Result File", "result_file", new FileType(), "file", false, null);
		finalConverter.addOutput(result_file);
		
		/* Setting with value */
		Setting fileNameSetting = finalConverter.findSetting("file_name");
		if(fileNameSetting != null){
			fileNameSetting.getType().setValue("AlocacaoDeEquipes");
		}
		
		finalConverter.addSetting(fileNameSetting);
		
		/* Add module */
		system.addModule(finalConverter);
		
		/* Step Four: Define the connections (links) */
		system.addLink("Conhecimento to Knowledge", csvConverter, fuzzyModule, conhecimento, knowledge);
		system.addLink("Habilidade to skill", csvConverter, fuzzyModule, habilidade, skill);
		system.addLink("Atitude to attitude", csvConverter, fuzzyModule, atitude, attitude);
		
		system.addLink("Produtividade to Resultado da Produtividade", fuzzyModule, finalConverter, productivity, productivity_result);
		
		system.createAndExecuteSimulation("Fuzzy system simulation");
	}
	
	@Test
	public void simutationTestAllDataInFinalConverter(){
		/* Step One: Create system */
		AthenaSystem system = new AthenaSystem("Fuzzy System", "First system by Athena Services");
		/* Step Two: Organize the modules */
		FromCSVConverter csvConverter = new FromCSVConverter();
		/* Step Three: Define Inputs and Outputs */
		Input csvInput = new Input("CSV File", "csv_file", new FileType(), "file", false, null);
		csvInput.addValue(new FileType("src/test/resources/csv/candidatos.csv"));
		
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
		Setting fileSetting = fuzzyModule.findSetting("fcl_file");
		if(fileSetting != null){
			fileSetting.getType().setValue("src/test/resources/fcl/tipperEntrada.fcl");
		}
		
		/* Add Input and Output, but without values */
		fuzzyModule.addInput(knowledge);
		fuzzyModule.addInput(skill);
		fuzzyModule.addInput(attitude);
		fuzzyModule.addOutput(productivity);
		fuzzyModule.addSetting(fileSetting);
		
		/* Add module to main system */
		system.addModule(fuzzyModule);
		
		ToCSVConverter finalConverter = new ToCSVConverter();
		
		Input knowledgeFinal = new Input("Conhecimento", "knowledge_result", Real.valueOf(0), "real", false, null);
		Input skillFinal = new Input("Habilidade", "skill_result", Real.valueOf(0), "real", false, null);
		Input attitudeFinal = new Input("Atitude", "attitude_result", Real.valueOf(0), "real", false, null);
		Input salarioFinal = new Input("Salário", "salario_result", Real.valueOf(0), "real", false, null);
		
		Input productivity_result = new Input("Resultado da Produtividade", "productivity_result", Real.valueOf(0), "real", false, null);
		finalConverter.addInput(productivity_result);
		
		finalConverter.addInput(knowledgeFinal);
		finalConverter.addInput(skillFinal);
		finalConverter.addInput(attitudeFinal);
		finalConverter.addInput(salarioFinal);
		
		Output result_file = new Output("Result File", "result_file", new FileType(), "file", false, null);
		finalConverter.addOutput(result_file);
		
		/* Setting with value */
		Setting fileNameSetting = finalConverter.findSetting("file_name");
		if(fileNameSetting != null){
			fileNameSetting.getType().setValue("AlocacaoDeEquipesFull");
		}
		
		finalConverter.addSetting(fileNameSetting);
		
		/* Add module */
		system.addModule(finalConverter);
		
		/* Step Four: Define the connections (links) */
		system.addLink("Conhecimento to Knowledge", csvConverter, fuzzyModule, conhecimento, knowledge);
		system.addLink("Habilidade to skill", csvConverter, fuzzyModule, habilidade, skill);
		system.addLink("Atitude to attitude", csvConverter, fuzzyModule, atitude, attitude);
		
		system.addLink("Knowledge to Knowledge Final", csvConverter, finalConverter, conhecimento, knowledgeFinal);
		system.addLink("Skill to Skill Final", csvConverter, finalConverter, habilidade, skillFinal);
		system.addLink("Attitude to Attitude Final", csvConverter, finalConverter, atitude, attitudeFinal);
		system.addLink("Salary to Salary Final", csvConverter, finalConverter, salario, salarioFinal);
		
		system.addLink("Produtividade to Resultado da Produtividade", fuzzyModule, finalConverter, productivity, productivity_result);
		
		system.createAndExecuteSimulation("Fuzzy system simulation");
	}
}
