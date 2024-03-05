package br.com.guilherme.exceptions.hander;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.guilherme.exceptions.ExceptionResponse;
import br.com.guilherme.exceptions.UnsupportedMathOperationException;

//ControllerAdvice é um controle de exceção global, se o erro não tiver nenhum tratamento cai nesse controlador de exceção 
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	//Controla todos os demais erros internos
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(
			Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);	
		
	}
	
	//Controla somete o bad request
	@ExceptionHandler(UnsupportedMathOperationException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(
			Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);	
		
	}

}
