package com.luan.pix.api.controller.exception;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.luan.pix.domain.exception.EntidadeEmUsoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ApiErrorType apiErrorType = ApiErrorType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		
		ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail)
	    		.userMessage(detail)
				.build();
		
		return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
	}
	
	private ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType apiErrorType,
			String detail) {
		
		return ApiError.builder()
				.timestamp(OffsetDateTime.now())
				.status(status.value())
				.type(apiErrorType.getUri())
				.title(apiErrorType.getTitle())
				.detail(detail);
	}

}
