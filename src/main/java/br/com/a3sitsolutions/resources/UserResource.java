package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.models.User;
import br.com.a3sitsolutions.services.UserService;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.Collection;
import java.util.List;

@Path("/user")
@Tag(name = "User")
@SecurityScheme(
        securitySchemeName = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@SecurityRequirement(name = "bearerAuth")
public class UserResource {

    @Inject
    UserService service;

    @GET
    @Operation(
        summary = "Get all User",
        description = "Get all User"
    )
    @APIResponses({
        @APIResponse(
                description = "The User list",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = User[].class)))
    })
    @RolesAllowed("user")
    public Collection<User> getAll(
            @Parameter(description = "The page number")
            @DefaultValue("0")
            @QueryParam("page")
            int page,

            @Parameter(description = "The size of page")
            @DefaultValue("100")
            @QueryParam("size")
            int size,

            @Parameter(description = "The sort column")
            @QueryParam("sortcolumn")
            List<String> sortcolumn
    ) {
        Page pageable = new Page(page, size);

        if (sortcolumn != null && !sortcolumn.isEmpty()) {
            Sort sort = Sort.by(sortcolumn.toArray(new String[0]));
            return service.getAll(pageable, sort);
        }

        return service.getAll(pageable);
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Get User by id",
            description = "Get User by id"
    )
    @APIResponses({
            @APIResponse(
                    description = "The User",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description = "User not found")
    })
    @RolesAllowed("user")
    public User findById(
            @Parameter(description = "The User id")
            @PathParam("id")
            Long id) {
        return service.findById(id);
    }

    @POST
    @Operation(
            summary = "Create User",
            description = "Create User"
    )
    @APIResponses({
            @APIResponse(
                    description = "The User created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @RolesAllowed("user")
    public User create(
            User entity) {
        return service.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Update User by id",
            description = "Update User by id"
    )
    @APIResponses({
            @APIResponse(
                    description = "The User updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description = "User not found")
    })
    @RolesAllowed("user")
    public User update(
            @Parameter(description = "The User id")
            @PathParam("id")
            Long id
    ) {
        User entity = service.findById(id);
        return service.update(entity);
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete User by id",
            description = "Delete User by id"
    )
    @APIResponses({
            @APIResponse(
                    description = "Executed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))),
            @APIResponse(responseCode = "404", description = "User not found")
    })
    @RolesAllowed("user")
    public Boolean delete(
            @Parameter(description = "The User id")
            @PathParam("id")
            Long id) {
        return service.delete(id);
    }
}
