package io.quarkuscoffeeshop.customerloyalty.infrastructure;

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

}