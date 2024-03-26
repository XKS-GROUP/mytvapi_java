package com.mytv.api.execptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.amazonaws.services.apigatewaymanagementapi.model.ForbiddenException;
import com.mytv.api.security.EntityResponse;

import io.jsonwebtoken.ExpiredJwtException;


@RestControllerAdvice
@ControllerAdvice
public class RestControllerException {



    // Gérer les erreurs 500 - Internal Server Error
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), Collections.singletonList("Error occurred"));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<Object> handleForbidden(Exception ex, WebRequest request) {
        return EntityResponse.generateResponse("Mauvaise entrée", HttpStatus.BAD_REQUEST, "Accès non autorisé a cet route, veuillez vous authentifier avec les droits appropries");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), Collections.singletonList("Violation de l'intégrité des données. Veuillez vérifier que cette valeur n'existe pas déja."));

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {

        return EntityResponse.generateResponse("Mauvaise entrée", HttpStatus.BAD_REQUEST, "Violation de l'intégrité des données. Veuillez vérifier que cette valeur n'existe pas déja");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> ExpiredJwtException(ExpiredJwtException ex) {

        	return EntityResponse.generateResponse("Authentication", HttpStatus.OK, "Votre session a expiré");
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> NullPointerException(NullPointerException ex) {

        return EntityResponse.generateResponse("Authentication", HttpStatus.BAD_REQUEST, "Verifié que votre compte est bien valide ou à été activé, que la asession n'est pas expiré car vous tentez une operation sur un utilisateur null ou qui n'existe plus");
    }
    // Gérer les execptions levées par les validations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
