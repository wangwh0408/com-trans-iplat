package com.allscore.trans.iplat.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtils {
	 private static Validator validator;
	 static{
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 validator = factory.getValidator();
	 }
	 
	 
	 public static Validator getValidator(){
		 return validator;
	 }
}

