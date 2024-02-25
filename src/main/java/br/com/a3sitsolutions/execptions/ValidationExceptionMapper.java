package br.com.a3sitsolutions.execptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException e) {

        return Response
                .status(Response.Status.fromStatusCode(e.httpStatusCode.code()))
                .entity(e.getMessage())
                .build();
    }
}
