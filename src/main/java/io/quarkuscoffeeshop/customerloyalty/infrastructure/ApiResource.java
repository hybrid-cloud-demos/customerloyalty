package io.quarkuscoffeeshop.customerloyalty.infrastructure;

import io.quarkuscoffeeshop.customerloyalty.domain.Adjective;
import io.quarkuscoffeeshop.customerloyalty.domain.Animal;
import io.quarkuscoffeeshop.customerloyalty.domain.RewardsMember;
import io.quarkuscoffeeshop.customerloyalty.domain.MembershipApplication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiResource {

    @POST
    @Path("/register")
    public Response registerUser(MembershipApplication membershipApplication) {

        RewardsMember rewardsMember = RewardsMember.processApplication(membershipApplication);
        return Response.created(URI.create("/" + rewardsMember.getCodeName())).entity(rewardsMember).build();
    }

    @GET
    @Path("/animals")
    public Response getAllAnimals() {
        return Response.ok().entity(Animal.listAll()).build();
    }

    @GET
    @Path("/animal/{id}")
    public Response getAnimal(@PathParam("id") Long id) {
        return Response.ok().entity(Animal.findById(id)).build();
    }

    @GET
    @Path("/adjectives")
    public Response allAdjectives() {
        return Response.ok().entity(Adjective.findAll().stream().toArray()).build();
    }
}