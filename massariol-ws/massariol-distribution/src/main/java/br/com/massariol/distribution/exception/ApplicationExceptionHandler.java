package br.com.massariol.distribution.exception;

import br.com.massariol.domain.features.exceptions.ExceptionCnpjInUse;
import br.com.massariol.domain.features.exceptions.ExceptionCpfInUse;
import br.com.massariol.domain.features.exceptions.ExceptionEmailInUse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApplicationExceptionHandler(MessageSource messageSource){
        this.messageSource=messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        var errorMessage = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        var fieldErrorOptional = ex.getBindingResult().getFieldErrors().stream().findFirst();
        var fieldError = fieldErrorOptional.get();
        var errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage), request);
    }

    @ExceptionHandler({ExceptionCpfInUse.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(ExceptionCpfInUse ex, WebRequest request) {
        var errorMessage = messageSource.getMessage("cpf.in-use", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage), request);
    }

    @ExceptionHandler({ExceptionCnpjInUse.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(ExceptionCnpjInUse ex, WebRequest request) {
        var errorMessage = messageSource.getMessage("cnpj.in-use", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage), request);
    }

    @ExceptionHandler({ExceptionEmailInUse.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(ExceptionEmailInUse ex, WebRequest request) {
        var errorMessage = messageSource.getMessage("email.in-use", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage), request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        var errorMessage = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.NOT_FOUND, errorMessage), request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception ex, ErrorResponse errorResponse, WebRequest request) {
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), errorResponse.getHttpStatus(), request);
    }

    @Getter
    @Setter
    static class ErrorResponse {
        private int status;
        private String error;
        private String message;
        @JsonIgnore
        private HttpStatus httpStatus;
        ErrorResponse(HttpStatus httpStatus, String message) {
            this.error = httpStatus.getReasonPhrase();
            this.status = httpStatus.value();
            this.message = message;
            this.httpStatus = httpStatus;
        }
    }
}
