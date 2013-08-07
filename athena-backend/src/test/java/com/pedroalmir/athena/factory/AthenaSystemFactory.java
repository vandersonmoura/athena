/**
 * 
 */
package com.pedroalmir.athena.factory;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.impl.converter.CSVConverter;
import com.pedroalmir.athena.impl.converter.FinalConverter;
import com.pedroalmir.athena.impl.fuzzy.module.FuzzyModule;
import com.pedroalmir.athena.web.model.form.AthenaSystemForm;

/**
 * @author Pedro Almir
 *
 */
public class AthenaSystemFactory {
	
	/**
	 * @return default athena system form
	 */
	public static AthenaSystemForm createDefaultAthenaSystemForm(){
		return new AthenaSystemForm(createDefaultAthenaSystem());
	}
	

	/**
	 * @return default athena system
	 */
	public static AthenaSystem createAthenaSystemOnlyWithFuzzy(){
		/* Step One: Create system */
		AthenaSystem system = new AthenaSystem("Fuzzy System", "First system by Athena Services");
		
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
		
		return system;
	}
	
	/**
	 * @return default athena system
	 */
	public static AthenaSystem createDefaultAthenaSystem(){
		/* Step One: Create system */
		AthenaSystem system = new AthenaSystem("Fuzzy System", "First system by Athena Services");
		/* Step Two: Organize the modules */
		CSVConverter csvConverter = new CSVConverter();
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
		
		FinalConverter finalConverter = new FinalConverter();
		
		Input productivity_result = new Input("Resultado da Produtividade", "productivity_result", Real.valueOf(0), "real", false, null);
		finalConverter.addInput(productivity_result);
		
		Output result_file = new Output("Result File", "result_file", new FileType(), "file", false, null);
		finalConverter.addOutput(result_file);
		
		/* Setting with value */
		Setting formatterSetting = finalConverter.findSetting("formatter");
		if(formatterSetting != null){
			formatterSetting.getType().setValue("csv_file");
		}
		
		Setting fileNameSetting = finalConverter.findSetting("file_name");
		if(fileNameSetting != null){
			fileNameSetting.getType().setValue("AlocacaoDeEquipes");
		}
		
		finalConverter.addSetting(formatterSetting);
		finalConverter.addSetting(fileNameSetting);
		
		/* Add module */
		system.addModule(finalConverter);
		
		/* Step Four: Define the connections (links) */
		system.addLink("Conhecimento to Knowledge", csvConverter, fuzzyModule, conhecimento, knowledge);
		system.addLink("Habilidade to skill", csvConverter, fuzzyModule, habilidade, skill);
		system.addLink("Atitude to attitude", csvConverter, fuzzyModule, atitude, attitude);
		
		system.addLink("Produtividade to Resultado da Produtividade", fuzzyModule, finalConverter, productivity, productivity_result);
		return system;
	}
	
}
