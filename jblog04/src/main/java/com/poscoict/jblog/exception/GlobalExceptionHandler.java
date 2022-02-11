package com.poscoict.jblog.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Exception e, Model model) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}
	
}
