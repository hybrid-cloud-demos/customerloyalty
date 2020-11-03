package io.quarkuscoffeeshop.customerloyalty.infrastructure;

import io.quarkuscoffeeshop.customerloyalty.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiResource {

    Logger logger = LoggerFactory.getLogger(ApiResource.class);

    @Inject
    LoyaltyMemberRepository loyaltyMemberRepository;

    @POST
    @Path("/register")
    @Transactional
    public Response registerUser(final MembershipApplication membershipApplication) {
        logger.info("received MembershipApplication: {}", membershipApplication);
        LoyaltyMember loyaltyMember = LoyaltyMember.processMembershipApplication(membershipApplication);
        logger.debug("persisting LoyaltyMember {}", loyaltyMember);
        loyaltyMemberRepository.persist(loyaltyMember);
        return Response.created(URI.create("/" + loyaltyMember.getCodeName())).entity(loyaltyMember).build();
    }

    @GET
    @Path("/members")
    public Response getAllMembers() {
        logger.debug("retrieving all members");
        return Response.ok().entity(LoyaltyMember.listAll()).build();
    }

    @GET
    @Path("/members/{id}")
    public Response getMembers(@PathParam("id") Long id) {
        logger.debug("looking up member with id {}", id);
        return Response.ok().entity(LoyaltyMember.findById(id)).build();
    }

    @GET
    @Path("/animals")
    public Response getAllAnimals() {
        logger.debug("retrieving all animals");
        return Response.ok().entity(Animal.listAll()).build();
    }

    @GET
    @Path("/animals/{id}")
    public Response getAnimal(@PathParam("id") Long id) {
        logger.debug("looking up animal with id {}", id);
        return Response.ok().entity(Animal.findById(id)).build();
    }

    @GET
    @Path("/adjectives")
    public Response allAdjectives() {
        logger.debug("retrieving all adjectives");
        return Response.ok().entity(Adjective.listAll()).build();
    }

    @GET
    @Path("/adjectives/{id}")
    public Response allAdjectives(@PathParam("id") Long id) {
        logger.debug("looking up adjective with id {}", id);
        return Response.ok().entity(Adjective.findById(id)).build();
    }

}