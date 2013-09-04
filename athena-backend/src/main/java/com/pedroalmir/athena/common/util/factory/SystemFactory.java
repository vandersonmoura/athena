/**
 * 
 */
package com.pedroalmir.athena.common.util.factory;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Int;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.type.string.StringType;
import com.pedroalmir.athena.impl.converter.FromCSVConverter;
import com.pedroalmir.athena.impl.converter.ToCSVConverter;
import com.pedroalmir.athena.impl.fuzzy.module.FuzzyModule;
import com.pedroalmir.athena.impl.fuzzy.stoppingCondition.FuzzyCompleteEvaluationStoppingCondition;
import com.pedroalmir.athena.web.model.form.AthenaSystemForm;
import com.pedroalmir.athena.web.model.form.bundle.AthenaBundleForm;
import com.pedroalmir.athena.web.model.form.link.LinkForm;
import com.pedroalmir.athena.web.model.form.put.InputForm;
import com.pedroalmir.athena.web.model.form.put.OutputForm;
import com.pedroalmir.athena.web.model.form.put.SettingForm;

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
	
	/**
	 * @param systemForm
	 * @param request
	 * @return athena system
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static AthenaSystem createSystemFromView(AthenaSystemForm systemForm, HttpServletRequest request) 
			throws InstantiationException, IllegalAccessException {
		
		/* Step 1: Create system */
		AthenaSystem system = new AthenaSystem(systemForm.getName(), systemForm.getDescription());
		
		/* Step 2: Create list of real bundles */
		List<AthenaBundleForm> bundlesForm = systemForm.getBundles();
		/* Step 2.1: Order by frontOrder field */
		Collections.sort(bundlesForm, new Comparator<AthenaBundleForm>() {
			@Override
			public int compare(AthenaBundleForm o1, AthenaBundleForm o2) {
				return o1.getFrontOrder().compareTo(o2.getFrontOrder());
			}
		});
		/* Step 2.2: Create real bundles */
		List<AthenaBundle> realBundles = new LinkedList<AthenaBundle>();
		
		@SuppressWarnings("unused")
		List<Input> inputs = new LinkedList<Input>();
		@SuppressWarnings("unused")
		List<Output> outputs = new LinkedList<Output>();
		
		for (AthenaBundleForm athenaBundleForm : bundlesForm) {
			/* Step 2.2.1: Find bundle class by uniqueKey */
			Class<AthenaBundle> bundleKlass = AthenaEnvironment.getBundleFromUniqueKey(athenaBundleForm.getUniqueKey(), request);
			/* Step 2.2.2: Create new instance from this bundle */
			AthenaBundle newInstance = bundleKlass.newInstance();
			/* Step 2.2.3: Extract all settings and add all in new instance of bundle */
			newInstance.setFrontIdentifier(athenaBundleForm.getFrontIdentifier());
			/* Step 2.2.4: Extract all inputs and add all in new instance of bundle */
			List<Input> extractAllInputs = extractAllInputs(athenaBundleForm);
			newInstance.addAllInput(extractAllInputs);
			/* Step 2.2.5: Extract all outputs and add all in new instance of bundle */
			List<Output> extractAllOutputs = extractAllOutputs(athenaBundleForm);
			newInstance.addAllOutput(extractAllOutputs);
			/* Step 2.2.6: Extract all settings and add all in new instance of bundle */
			newInstance.addAllSetting(extractAllSettings(athenaBundleForm));
			/* Step 2.2.7: Add in real bundle list */
			realBundles.add(newInstance);
		}
		
		/* Step 3: Create real links */
		List<Link> realLinks = new LinkedList<Link>();
		for(LinkForm linkForm : systemForm.getLinks()){
			/* Step 3.1: Find source module */
			AthenaBundle srcModule = findModule(realBundles, linkForm.getBundleSrc());
			/* Step 3.2: Find destination module */
			AthenaBundle dstModule = findModule(realBundles, linkForm.getBundleDst());
			
			/* Step 3.3: Find destination Input */
			Input dstInput = findInput(realBundles, linkForm.getBundleDst(), linkForm.getInputId());
			dstInput.setLinked(true);
			
			/* Step 3.4: Find source Output */
			Output srcOutput = findOutput(realBundles, linkForm.getBundleSrc(), linkForm.getOutputId());
			srcOutput.setLinked(true);
			
			/* Step 3.5: Add link to real link list */
			realLinks.add(new Link(linkForm.getDescription(), srcModule, dstModule, srcOutput, dstInput));
		}
		
		
		/* Step 4: Add all bundles */
		system.addAllModule(realBundles);
		/* Step 5: Add all links */
		system.addAllLink(realLinks);
		
		return system;
	}
	
	

	/**
	 * @param realBundles
	 * @param bundleIdentifier
	 * @param inputIdentifier
	 * @return input
	 */
	private static Input findInput(List<AthenaBundle> realBundles, String bundleIdentifier, String inputIdentifier) {
		for (AthenaBundle athenaBundle : realBundles) {
			if(athenaBundle.getFrontIdentifier().equalsIgnoreCase(bundleIdentifier)){
				for(Input in : athenaBundle.getInputs()){
					if(in.getIdentifier().equals(inputIdentifier)){
						return in;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @param realBundles
	 * @param bundleIdentifier
	 * @param outputIdentifier
	 * @return output
	 */
	private static Output findOutput(List<AthenaBundle> realBundles, String bundleIdentifier, String outputIdentifier) {
		for (AthenaBundle athenaBundle : realBundles) {
			if(athenaBundle.getFrontIdentifier().equalsIgnoreCase(bundleIdentifier)){
				for(Output out : athenaBundle.getOutputs()){
					if(out.getIdentifier().equals(outputIdentifier)){
						return out;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param realBundles
	 * @param bundleIdentifier
	 * @return athena bundle with bundleIdentifier
	 */
	private static AthenaBundle findModule(List<AthenaBundle> realBundles, String bundleIdentifier) {
		for (AthenaBundle athenaBundle : realBundles) {
			if(athenaBundle.getFrontIdentifier().equalsIgnoreCase(bundleIdentifier)){
				return athenaBundle;
			}
		}
		return null;
	}

	/**
	 * @param athenaBundleForm
	 * @return list of settings
	 */
	private static List<Setting> extractAllSettings(AthenaBundleForm athenaBundleForm) {
		List<Setting> realSettings = new LinkedList<Setting>();
		Setting setting = null;
		for(SettingForm settingForm : athenaBundleForm.getSettings()){
			Type type = parseType(settingForm.getType().getRepresentation());
			type.setValue(settingForm.getType().getValue());
			setting = new Setting(settingForm.getName(), settingForm.getIdentifier(), type, settingForm.getType().getRepresentation(), 
					settingForm.isMultipleValue(), null, settingForm.isRequired());
			realSettings.add(setting);
		}
		return realSettings;
	}

	/**
	 * @param athenaBundleForm
	 * @return list of outputs
	 */
	private static List<Output> extractAllOutputs(AthenaBundleForm athenaBundleForm) {
		List<Output> realOutputs = new LinkedList<Output>();
		Output out = null;
		for(OutputForm outputForm : athenaBundleForm.getOutputs()){
			out = new Output(outputForm.getFrontName(), outputForm.getFrontIdentifier(), parseType(outputForm.getFrontType()), outputForm.getFrontType(), false, null);
			realOutputs.add(out);
		}
		return realOutputs;
	}

	/**
	 * Extract all inputs
	 * 
	 * @param athenaBundleForm
	 * @return list of inputs
	 */
	private static List<Input> extractAllInputs(AthenaBundleForm athenaBundleForm) {
		List<Input> realInputs = new LinkedList<Input>();
		Input in = null;
		for(InputForm inputForm : athenaBundleForm.getInputs()){
			in = new Input(inputForm.getFrontName(), inputForm.getFrontIdentifier(), parseType(inputForm.getFrontType()), inputForm.getFrontType(), false, null);
			if(inputForm.getFrontComponents() != null && !inputForm.getFrontComponents().isEmpty()){
				Type t = in.getType().getClone();
				t.setValue(inputForm.getFrontComponents().get(0));
				in.addValue(t);
			}else{
				/*
				 * TODO: Remove this!
				 * if 
				 * 	inputForm.getFrontComponents() != null 
				 * then
				 *  input isn't linked.
				 * */
			}
			realInputs.add(in);
		}
		return realInputs;
	}

	/**
	 * @param frontType
	 * @return type
	 */
	private static Type parseType(String frontType) {
		if(frontType.equals("int")){
			return Int.valueOf(0);
		}else if (frontType.equals("double")){
			return Real.valueOf(0);
		}else if (frontType.equals("file")){
			return new FileType();
		}else if (frontType.equals("string")){
			return new StringType("");
		}
		return null;
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
