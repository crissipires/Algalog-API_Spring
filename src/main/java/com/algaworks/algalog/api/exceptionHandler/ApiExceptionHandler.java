package com.algaworks.algalog.api.exceptionHandler;

import com.algaworks.algalog.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        List<Problema.Campo> campos = new ArrayList<>();
        campos.add(new Problema.Campo(fields,fieldsMessage));

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDatahora(LocalDateTime.now());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preechimento correto e tente novamente!");
        problema.setCampos(campos);

        return super.handleExceptionInternal(ex,problema,headers,status,request);
    }
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDatahora(LocalDateTime.now());
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex,problema,new HttpHeaders(),status, request);
    }
}
