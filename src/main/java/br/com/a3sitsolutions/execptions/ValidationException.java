package br.com.a3sitsolutions.execptions;

import io.netty.handler.codec.http.HttpResponseStatus;

public class ValidationException extends RuntimeException {

    public String message;

    public HttpResponseStatus httpStatusCode = HttpResponseStatus.INTERNAL_SERVER_ERROR;

    public ValidationException(){

        super("There was a problem when validating data");
    }

    public ValidationException(String message){
        super(message);
        this.message = message;
    }

    public ValidationException(String message, Throwable throwable){
        super(message, throwable);
        this.message = message;
    }

    public ValidationException(Throwable throwable){
        super(throwable);
    }

    public ValidationException(String message, HttpResponseStatus httpStatusCode){
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public ValidationException(String message, HttpResponseStatus httpStatusCode, Throwable throwable){
        super(message, throwable);
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
