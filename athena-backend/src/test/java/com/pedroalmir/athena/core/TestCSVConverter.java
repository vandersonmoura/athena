/**
 * 
 */
package com.pedroalmir.athena.core;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.impl.converter.FromCSVConverter;
/**
 * @author Pedro Almir
 *
 */
public class TestCSVConverter {
	
	@Test
	public void testCSVConverter(){
		FromCSVConverter converter = new FromCSVConverter();
		/* Input config */
		Input input = new Input("CSV File", "csv_file", new FileType(), "file", false, null);
		input.addValue(new FileType("src/test/resources/csv/atitude.csv"));
		converter.addInput(input);
		/* Output config */
		Output output = new Output("Atitude", "atitude", Real.valueOf(0), "real", false, null);
		converter.addOutput(output);
		/* Convert */
		List<Output> outputs = converter.convert();
		Assert.assertNotNull(outputs);
		Assert.assertEquals(1, outputs.size());
		for(Type d : outputs.get(0).getValues()){
			System.out.println(d.getValue());
		}
	}
	
	@Test
	public void testCSVConverterCompleteFile(){
		FromCSVConverter converter = new FromCSVConverter();
		/* Input config */
		Input input = new Input("CSV File", "csv_file", new FileType(), "file", false, null);
		input.addValue(new FileType("src/test/resources/csv/candidatos.csv"));
		
		converter.addInput(input);
		
		/* Output config */
		Output conhecimento = new Output("Conhecimento", "conhecimento", Real.valueOf(0), "real", false, null);
		converter.addOutput(conhecimento);
		Output habilidade = new Output("Habilidade", "habilidade", Real.valueOf(0), "real", false, null);
		converter.addOutput(habilidade);
		Output atitude = new Output("Atitude", "atitude", Real.valueOf(0), "real", false, null);
		converter.addOutput(atitude);
		Output salario = new Output("Sal�rio", "salario", Real.valueOf(0), "real", false, null);
		converter.addOutput(salario);
		/* Convert */
		List<Output> outputs = converter.convert();
		Assert.assertNotNull(outputs);
		Assert.assertEquals(4, outputs.size());
		for(Output o : outputs){
			System.out.println(o.getName());
			for(Type d : o.getValues()){
				System.out.println(d.getValue());
			}
		}
	}
}
