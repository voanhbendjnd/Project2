package com.javaweb.ControllerAd;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.javaweb.CustomerException.OutputException;
import com.javaweb.beans.ErrorResponse;

public class ControllerAdvisor {
	@ExceptionHandler(OutputException.class)
	public ResponseEntity<Object> handleer(
			OutputException ex, WebRequest required){
		ErrorResponse enon = new ErrorResponse();
		enon.setEr(ex.getMessage());
		ArrayList<String> details = new ArrayList<>();
		details.add("empty");
		enon.setDetail(details);
		return new ResponseEntity<>(enon, HttpStatus.NOT_IMPLEMENTED);
	}
}
