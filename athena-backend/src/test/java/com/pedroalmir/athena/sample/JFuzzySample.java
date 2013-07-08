/**
 * 
 */
package com.pedroalmir.athena.sample;

import net.sourceforge.jFuzzyLogic.FIS;

/**
 * @author Pedro Almir
 * 
 */
public class JFuzzySample {
	public static void main(String[] args) throws Exception {
		/* Load from 'FCL' file */
		String fileName = "src/test/resources/fcl/tipper.fcl";
		FIS fis = FIS.load(fileName, true);
		
		/* Error while loading? */
		if (fis == null) {
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		/* Show */
		fis.chart();

		/* Set inputs */
		fis.setVariable("service", 3);
		fis.setVariable("food", 7);

		/* Evaluate */
		fis.evaluate();
		
		/* Show output variable's chart */
		double defuzzifiedValue = fis.getVariable("tip").getLatestDefuzzifiedValue();

		/* Print ruleSet */
		System.out.println(defuzzifiedValue);
	}
}
