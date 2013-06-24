package com.pedroalmir.athena.web.model.validation;

import br.com.caelum.vraptor.ioc.Component;

import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.util.RegexUtil;
import com.pedroalmir.athena.common.util.StringUtil;
import com.pedroalmir.athena.common.validation.ValidatorBase;
import com.pedroalmir.athena.common.validation.annotation.ValidationMethod;
import com.pedroalmir.athena.web.model.User;
import com.pedroalmir.athena.web.repository.UserDAO;


/**
 * 
 * Used to validate the User
 * 
 * @author Pedro Almir
 */
@Component
@MainDAO(UserDAO.class)
public class UserValidation extends ValidatorBase<UserDAO> {


	/**
	 * 
	 * @param user The User with fields to be validated
	 */
	@ValidationMethod
	public void validCorrectEmail(User user) {
		if (!StringUtil.isEmpty(user.getEmail()) && !RegexUtil.validEmail(user.getEmail())) {
			addError("user_email", "common.invalid");
		}
	}

}
