package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.models.User;
import br.com.a3sitsolutions.services.SecurityService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/security")
@Tag(name = "Security")
public class SecurityResource {

    @Inject
    SecurityService service;

    @POST
    @Operation(
            summary = "Login User",
            description = "Login User"
    )
    @APIResponses({
            @APIResponse(
                    description = "The JWT token",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN,
                            schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "403", description = "User denied")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(
            @RequestBody(description = "User", required = true)
            User entity) {

        String token = service.generateToken(entity.getEmail(), entity.getPassword());

        if (token != null) {
            return token;
        }

        return Response.status(Response.Status.FORBIDDEN).build().toString();
    }
}
