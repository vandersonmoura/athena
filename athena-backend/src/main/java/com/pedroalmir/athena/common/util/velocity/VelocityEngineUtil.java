package com.pedroalmir.athena.common.util.velocity;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * Velocity Engine Util class
 * 
 * @author EasyTeam, created by Pedro Almir
 *
 */
public class VelocityEngineUtil {
	
	/**
	 * Logger
	 */
	static Logger logger = Logger.getLogger(VelocityEngineUtil.class);

	/**
	 * Get velocity template
	 * 
	 * @param params
	 * @param template
	 * @return the content
	 */
	public static String getTemplate(Map<String, Object> params, String template) {
		try {
			
			InputStream templateFile = new FileInputStream(template);
			//InputStream templateFile = VelocityEngineUtil.class.getResourceAsStream(template);
			
			StringBuilder builder = new StringBuilder();
			int i = 0;
			while ((i = templateFile.read()) != -1) {
				builder.append((char)i);
			}
			templateFile.close();

			VelocityContext context = new VelocityContext();

			for (Entry<String, Object> entry : params.entrySet()) {
				context.put(entry.getKey(), entry.getValue());
			}
			
			StringWriter writer = new StringWriter();
			
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
			ve.init();
			ve.evaluate(context, writer, "LOG", builder.toString());
			//Velocity.evaluate(context, writer, "LOG", builder.toString());

			return writer.toString();
		}catch (NullPointerException nPE){
			logger.error(nPE.getMessage());
			nPE.printStackTrace();
		}catch (ResourceNotFoundException rNFE){
			logger.error(rNFE.getMessage());
			rNFE.printStackTrace();
		}catch (MissingResourceException mRE){
			logger.error(mRE.getMessage());
			mRE.printStackTrace();
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	
}
