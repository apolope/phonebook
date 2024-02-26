package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.models.People;
import br.com.a3sitsolutions.services.PeopleService;
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

@Path("/people")
@Tag(name = "People")
@SecurityScheme(
        securitySchemeName = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@SecurityRequirement(name = "bearerAuth")
public class PeopleResource {

    @Inject
    PeopleService service;

    @GET
    @Operation(
            summary = "Get all People",
            description = "Get all People"
    )
    @APIResponses({
            @APIResponse(
                    description = "The People list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = People[].class)))
    })
    @RolesAllowed("user")
    public Collection<People> getAll(
            @Parameter(description = "The page number")
            @DefaultValue("0")
            @QueryParam("page")
            int page,

            @Parameter(description = "The size of page")
            @DefaultValue("10")
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
            summary = "Get People by id",
            description = "Get People by id"
    )
    @APIResponses({
            @APIResponse(
                    description = "The People",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = People.class))),
            @APIResponse(responseCode = "404", description = "People not found")
    })
    @RolesAllowed("user")
    public People findById(
            @Parameter(description = "The People id")
            @PathParam("id")
            Long id) {
        return service.findById(id);
    }

    @POST
    @Operation(
            summary = "Create People",
            description = "Create People"
    )
    @APIResponses({
            @APIResponse(
                    description = "The People created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = People.class)))
    })
    @RolesAllowed("user")
    public People create(
            People entity) {
        return service.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Update People by id",
            description = "Update People by id"
    )
    @APIResponses({
            @APIResponse(
                    description = "The People updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = People.class))),
            @APIResponse(responseCode = "404", description = "People not found")
    })
    @RolesAllowed("user")
    public People update(
            @Parameter(description = "The People id")
            @PathParam("id")
            Long id
    ) {
        People entity = service.findById(id);
        return service.update(entity);
    }

    @DELETE
    @Operation(
            summary = "Delete People by id",
            description = "Delete People by id"
    )
    @APIResponses({
            @APIResponse(
                    description = "Executed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))),
            @APIResponse(responseCode = "404", description = "People not found")
    })
    @RolesAllowed("user")
    public Boolean delete(
            Long id) {
        return service.delete(id);
    }
}
