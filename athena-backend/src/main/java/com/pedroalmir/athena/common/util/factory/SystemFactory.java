/**
 * 
 */
package com.pedroalmir.athena.common.util.factory;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.impl.converter.FromCSVConverter;
import com.pedroalmir.athena.impl.converter.ToCSVConverter;
import com.pedroalmir.athena.impl.fuzzy.module.FuzzyModule;
import com.pedroalmir.athena.impl.fuzzy.stoppingCondition.FuzzyCompleteEvaluationStoppingCondition;

/**
 * @author Pedro Almir
 *
 */
public class SystemFactory {
	
	/**
	 * Create Fuzzy System
	 * 
	 * @param fclPath
	 * 			FCL File Path
	 * @param csvPath
	 * 			CSV File Path
	 * @param resultPath
	 * 			Result File Path
	 * @return Fuzzy System to <strong>A Hybrid Approach to Solve the Agile Team Allocation Problem</strong>
	 */
	public static AthenaSystem createFuzzySystem(String fclPath, String csvPath, String resultPath){
		/* Step One: Create system */
		AthenaSystem system = new AthenaSystem("Fuzzy System", "First system by Athena Services");
		/* Step Two: Organize the modules */
		FromCSVConverter csvConverter = new FromCSVConverter();
		/* Step Three: Define Inputs and Outputs */
		Input csvInput = new Input("CSV File", "csv_file", new FileType(), "file", false, null);
		csvInput.addValue(new FileType(csvPath));
		
		Output conhecimento = new Output("Conhecimento", "conhecimento", Real.valueOf(0), "real", false, null);
		Output habilidade = new Output("Habilidade", "habilidade", Real.valueOf(0), "real", false, null);
		Output atitude = new Output("Atitude", "atitude", Real.valueOf(0), "real", false, null);
		Output salario = new Output("Salario", "salario", Real.valueOf(0), "real", false, null);
		
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
			fileSetting.getType().setValue(fclPath);
		}
		
		/* Add Input and Output, but without values */
		fuzzyModule.addInput(knowledge);
		fuzzyModule.addInput(skill);
		fuzzyModule.addInput(attitude);
		fuzzyModule.addOutput(productivity);
		fuzzyModule.addSetting(fileSetting);
		
		/* Add module to main system */
		system.addModule(fuzzyModule);
		
		ToCSVConverter ToCSVConverter = new ToCSVConverter();
		
		Input knowledgeFinal = new Input("Conhecimento", "knowledge_result", Real.valueOf(0), "real", false, null);
		Input skillFinal = new Input("Habilidade", "skill_result", Real.valueOf(0), "real", false, null);
		Input attitudeFinal = new Input("Atitude", "attitude_result", Real.valueOf(0), "real", false, null);
		Input salarioFinal = new Input("Salario", "salario_result", Real.valueOf(0), "real", false, null);
		
		Input productivity_result = new Input("Resultado da Produtividade", "productivity_result", Real.valueOf(0), "real", false, null);
		
		ToCSVConverter.addInput(knowledgeFinal);
		ToCSVConverter.addInput(skillFinal);
		ToCSVConverter.addInput(attitudeFinal);
		ToCSVConverter.addInput(salarioFinal);
		
		ToCSVConverter.addInput(productivity_result);
		
		Output result_file = new Output("Result File", "result_file", new FileType(), "file", false, null);
		ToCSVConverter.addOutput(result_file);
		
		/* Setting with value */
		Setting fileNameSetting = ToCSVConverter.findSetting("file_name");
		if(fileNameSetting != null){
			fileNameSetting.getType().setValue("AlocacaoDeEquipesFull");
		}
		
		ToCSVConverter.addSetting(fileNameSetting);
		
		/* Add module */
		system.addModule(ToCSVConverter);
		
		/* Step Four: Define the connections (links) */
		system.addLink("Conhecimento to Knowledge", csvConverter, fuzzyModule, conhecimento, knowledge);
		system.addLink("Habilidade to skill", csvConverter, fuzzyModule, habilidade, skill);
		system.addLink("Atitude to attitude", csvConverter, fuzzyModule, atitude, attitude);
		
		system.addLink("Knowledge to Knowledge Final", csvConverter, ToCSVConverter, conhecimento, knowledgeFinal);
		system.addLink("Skill to Skill Final", csvConverter, ToCSVConverter, habilidade, skillFinal);
		system.addLink("Attitude to Attitude Final", csvConverter, ToCSVConverter, atitude, attitudeFinal);
		system.addLink("Salary to Salary Final", csvConverter, ToCSVConverter, salario, salarioFinal);
		
		system.addLink("Produtividade to Resultado da Produtividade", fuzzyModule, ToCSVConverter, productivity, productivity_result);
		
		return system;
	}

	public static FuzzyModule createTipperFuzzySystem(Double service, Double food, String fclFileRealPath) {
		
		List<Input> inputs = new LinkedList<Input>();
		List<Output> outputs = new LinkedList<Output>();
		List<Setting> settings = new LinkedList<Setting>();
		/* Create inputs */
		Input inputService = new Input("Qualidade do Serviço", "service", Real.valueOf(0), "real", false, null);
		Input inputFood = new Input("Qualidade da Comida", "food", Real.valueOf(0), "real", false, null);
		
		inputService.addValue(Real.valueOf(service));
		inputFood.addValue(Real.valueOf(food));
		
		inputs.add(inputService);
		inputs.add(inputFood);
		/* Create outputs */
		Output outputTip = new Output("Valor da gorjeta", "tip", Real.valueOf(0), "real", false, null);
		outputs.add(outputTip);
		/* Create settings */
		Setting fileSetting = new Setting("Arquivo de Configuração FCL", "fcl_file", new FileType(fclFileRealPath), "file", false, null);
		settings.add(fileSetting);
		
		FuzzyModule fuzzyModule = new FuzzyModule();
		
		fuzzyModule.load(inputs, outputs, settings);
		fuzzyModule.getAlgorithm().addStoppingCondition(new FuzzyCompleteEvaluationStoppingCondition(inputs));
		
		/* Add module to main system */
		return fuzzyModule;
	}
}
