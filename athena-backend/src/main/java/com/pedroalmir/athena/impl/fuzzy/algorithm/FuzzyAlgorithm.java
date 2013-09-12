/**
 * 
 */
package com.pedroalmir.athena.impl.fuzzy.algorithm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.jFuzzyLogic.FIS;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.core.algorithm.AbstractAlgorithm;
import com.pedroalmir.athena.core.problem.objective.Maximise;
import com.pedroalmir.athena.core.problem.objective.Minimise;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.util.PutUtils;
import com.pedroalmir.athena.impl.fuzzy.solution.FuzzySolution;

/**
 * Fuzzy logic is a form of many-valued logic or probabilistic logic; 
 * It deals with reasoning that is approximate rather than fixed and exact. 
 * Compared to traditional binary sets (where variables may take on true or false values) 
 * fuzzy logic variables may have a truth value that ranges in degree between 0 and 1. 
 * <br><br>
 * Fuzzy logic has been extended to handle the concept of partial truth, where the truth value may 
 * range between completely true and completely false. Furthermore, when linguistic variables are used, 
 * these degrees may be managed by specific functions. Irrationality can be described in terms of what 
 * is known as the fuzzjective.
 * <br>
 * Fuzzy logic algorithm
 * <br><br>
 * 	<ol>
 * 		<li>Define the linguistic variables and terms (initialization)
 * 		<li>Construct the membership functions (initialization)
 * 		<li>Construct the rule base (initialization)
 * 		<li>Convert crisp input data to fuzzy values using membership functions (fuzzification)
 * 		<li>Evaluate the rules in the rule base (inference)
 * 		<li>Combine the results of each rule (inference)
 * 		<li>Convert the output data to non-fuzzy values (defuzzification)
 * 	</ol>
 * 
 * @author Pedro Almir
 *
 */
public class FuzzyAlgorithm extends AbstractAlgorithm {
	
	/**
	 * Fuzzy inference system (FIS)
	 * 
	 * A fuzzy logic inference system based on FCL language 
	 * according to "INTERNATIONAL ELECTROTECHNICAL COMMISSION" IEC 1131 - Part 7
	 * 
	 */
	private FIS fuzzyInferenceSystem;
	private Properties fuzzyProperties;
	private final String FUZZY_PROPERTIES_PATH = "src/main/resources/algorithms/fuzzy.properties";
	private final String FUZZY_PROPERTIES_PATH_WAR = "WEB-INF/classes/algorithms/fuzzy.properties";

	/**
	 * @param inputs
	 * @param outputs
	 * @param settings
	 */
	public FuzzyAlgorithm(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		super(inputs, outputs, settings);
	}
	
	/**
	 * @param copy
	 */
	protected FuzzyAlgorithm(AbstractAlgorithm copy) {
		super(copy);
	}

	
	public void addSetting(Setting setting) {
		this.settings.add(Preconditions.checkNotNull(setting));
	}

	
	public void removeSetting(Setting setting) {
		this.settings.remove(Preconditions.checkNotNull(setting));
	}

	
	public AbstractAlgorithm getClone() {
		return new FuzzyAlgorithm(this);
	}
	
	
	
	public void algorithmInitialisation() {
		/* Load fuzzy properties */
		loadProperties();
		/* try to find fcl file setting */
		Setting fclFile = PutUtils.findSetting(fuzzyProperties.getProperty("fuzzy.setting.fcl"), settings);
		/* Verify if fcl setting is instanceof FileType */
		Preconditions.checkArgument(fclFile.getType() instanceof FileType, "The argument fcl must be a file.");
		/* Verify if fcl setting is not null */
		Preconditions.checkNotNull(fclFile.getType());
		/* Load fcl file and create fuzzy inference system */
		this.executionLog.appendLogLine("Carregando arquivo FCL...");
		fuzzyInferenceSystem = FIS.load(((FileType) fclFile.getType()).getFilePath(), true);
		this.executionLog.appendLogLine("Arquivo FCL carregado: " + ((FileType) fclFile.getType()).getFile().getName());
		/* Check if load is all right */
		Preconditions.checkNotNull(fuzzyInferenceSystem, "Can't load file.");
		//System.out.println("Fuzzy initialized with success!");
		this.executionLog.appendLogLine("Algoritmo Fuzzy iniciado com sucesso!");
	}

	
	protected void algorithmIteration() {
		/**/
		List<Type> inputVariables = new LinkedList<Type>();
		List<Type> outputVariables = new LinkedList<Type>();
		
		List<String> inputValues = new LinkedList<String>();
		List<String> outputValues = new LinkedList<String>();
		/* Set inputs variables */
		/* TODO: Verify FCL variables with inputs identifiers */
		for(Input input: this.inputs){
			/* Esse código gerou problema porque o frontend gera identificadores
			 * que são desconhecidos pelo usuário que está usando o sistema, então
			 * o nome dos inputs deve ser o mesmo encontrado no arquivo FCL.
			 * TODO: Analisar para saber se essa realmente é a melhor saída.
			 * fuzzyInferenceSystem.setVariable(input.getIdentifier(), (Double) input.getValues().get(getIterations()).getValue());
			 * */
			fuzzyInferenceSystem.setVariable(input.getName().trim(), (Double) input.getValues().get(getIterations()).getValue());
			inputVariables.add(input.getValues().get(getIterations()));
			inputValues.add(input.getValues().get(getIterations()).getValue().toString());
		}
		/* Evaluate */
		fuzzyInferenceSystem.evaluate();
		/* Get outputs variables */
		for(Output output : this.outputs){
			double defuzzifiedValue = fuzzyInferenceSystem.getVariable(output.getName().trim()).getLatestDefuzzifiedValue();
			output.getValues().add(Real.valueOf(defuzzifiedValue));
			outputVariables.add(Real.valueOf(defuzzifiedValue));
			outputValues.add(defuzzifiedValue + "");
		}
		/* Creating the list of solutions */
		this.solutions.add(new FuzzySolution(inputVariables, outputVariables));
		
		/*  */
		this.executionLog.appendLogLine("Iteração " + (getIterations() + 1) + ": In(" 
				+ StringUtils.join(inputValues, ", ") + ") -> " 
				+ "Out(" + StringUtils.join(outputValues, ", ") + ")");
		
		//System.out.println("Fuzzy iteration number " + (getIterations() + 1));
	}

	
	public FuzzySolution getBestSolution() {
		Iterator<Solution> iterator = this.getSolutions().iterator();
		FuzzySolution best = (FuzzySolution) iterator.next();
		while(iterator != null && iterator.hasNext()){
			FuzzySolution fuzzySolution = (FuzzySolution) iterator.next();
			if(this.getOptimizationProblem().getObjective() instanceof Maximise){
				int compareTo = best.getFitness().getValue().compareTo(fuzzySolution.getFitness().getValue());
				if(compareTo < 0){
					best = fuzzySolution;
				}
			}else if(this.getOptimizationProblem().getObjective() instanceof Minimise){
				int compareTo = best.getFitness().getValue().compareTo(fuzzySolution.getFitness().getValue());
				if(compareTo > 0){
					best = fuzzySolution;
				}
			}
		}
		return best;
	}

	
	public List<Solution> getSolutions() {
		return this.solutions;
	}
	
	/**
	 * Load Properties
	 */
	private void loadProperties() {
        try {
        	InputStream inputStream = null;
        	
        	if(AthenaEnvironment.IN_TOMCAT){
        		inputStream = new FileInputStream(AthenaEnvironment.addRootPath(this.FUZZY_PROPERTIES_PATH_WAR));
        	}else{
        		inputStream = new FileInputStream(this.FUZZY_PROPERTIES_PATH);
        	}
        	
        	if(fuzzyProperties == null)
        		fuzzyProperties = new Properties();
        	/* load properties */
        	fuzzyProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
