package com.restful.custom_annotations;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<ValidEmailDomain, String> {

	private List<String> allowedDomains;

	@Override
	public void initialize(ValidEmailDomain constraintAnnotation) {
		allowedDomains = Arrays.asList(constraintAnnotation.allowedDomains());
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null || !email.contains("@")) {
			return false;
		}
		String domain = email.substring(email.indexOf("@") + 1);
		return allowedDomains.contains(domain);
	}
}