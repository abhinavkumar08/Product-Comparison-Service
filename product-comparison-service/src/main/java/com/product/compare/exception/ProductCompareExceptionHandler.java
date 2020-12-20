package com.product.compare.exception;

import com.product.compare.document.ErrorResponse;
import com.product.compare.validation.RequestValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ProductCompareExceptionHandler {

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<?> handleRequestValidationException(RequestValidationException exception, WebRequest request){

        ErrorResponse response = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

    }

   @ExceptionHandler(ProductCompareServiceException.class)
    public ResponseEntity<?> handleBookingApiException(Exception exception, WebRequest request){

        ErrorResponse response = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception exception, WebRequest request){

        ErrorResponse response = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(PullDataToImportException.class)
    public ResponseEntity<?> handleExceptionWhileImportingDataThroughPullMechanism(Exception exception, WebRequest request){

        ErrorResponse response = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(response, HttpStatus.NOT_IMPLEMENTED);

    }

}
