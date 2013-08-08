/**
 * 
 */
package com.pedroalmir.athena.common;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.pedroalmir.athena.common.model.enums.EnumProfile;
import com.pedroalmir.athena.common.util.json.JacksonUtil;
import com.pedroalmir.athena.factory.AthenaSystemFactory;
import com.pedroalmir.athena.web.model.User;
import com.pedroalmir.athena.web.model.form.AthenaSystemForm;

/**
 * @author Pedro Almir
 *
 */
public class TestJackson {
	@Test
	public void testJacksonLibrary(){
		User user = new User();
		user.setEmail("contato@pedroalmir.com");
		user.setPassword("123456");
		user.setProfile(EnumProfile.ADMIN);
		
		String json = "{\"id\":null,\"email\":\"contato@pedroalmir.com\",\"password\":\"123456\","
				+ "\"passwordConfirmation\":null,\"profile\":\"ADMIN\"}";
		
		Assert.assertEquals(json, JacksonUtil.serialize(user));
		
		Assert.assertEquals(user, JacksonUtil.deserialize(json, User.class));
	}
	
	@Test
	public void testJacksonWithAthenaSystemForm(){
		//AthenaSystemForm athenaSystemForm = AthenaSystemFactory.createDefaultAthenaSystemForm();
		//System.out.println(JacksonUtil.serialize(athenaSystemForm));
		//System.out.println(new Gson().toJson(athenaSystemForm));
		
	}
	
	public static void main(String[] args) {
		System.out.println(new Gson().toJson(new AthenaSystemForm(AthenaSystemFactory.createAthenaSystemOnlyWithFuzzy())));
	}
}
