/**
 * 
 */
package com.pedroalmir.athena.common;

import org.nfunk.jep.JEP;

/**
 * @author Pedro Almir
 *
 */
public class TestJEP {
	public static void main(String[] args) {
		JEP myParser = new JEP();
		myParser.addVariable("salario", 3000);
		myParser.addVariable("produtividade", 5);
		myParser.addVariable("a", 0.1);
		myParser.parseExpression("(salario+produtividade)*a");
		
		System.out.println(myParser.getValue());
	}
}
