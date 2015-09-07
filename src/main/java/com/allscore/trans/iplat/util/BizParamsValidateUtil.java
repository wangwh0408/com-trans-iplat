package com.allscore.trans.iplat.util;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.allscore.trans.iplat.exception.TransException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-8-20 上午11:21:11
 */
public class BizParamsValidateUtil
{

	public static <T> void validate(T target) {
		Set<ConstraintViolation<T>> constraintViolations = ValidatorUtils
				.getValidator().validate(target);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			ConstraintViolation<T> convi = constraintViolations.iterator()
					.next();
			throw new TransException(
					ConstantUtil.RetInfo.ILLEGAL_ARGUMENT.getValue(),
					convi.getMessage());
		}
	}

}
