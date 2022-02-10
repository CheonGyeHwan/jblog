package com.poscoict.jblog.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Model model, Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		if (e.toString().contains("NoHandlerFoundException")) {
			return "error/404";
		}
		
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}
	
}
