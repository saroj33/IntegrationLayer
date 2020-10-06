package no.jax.rs.IntegrationLayer.web;

import no.jax.rs.IntegrationLayer.models.Customer;
import no.jax.rs.IntegrationLayer.models.ResponseParser;
import no.jax.rs.IntegrationLayer.services.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;

@Path("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    RestClient restClient;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(@Valid Customer customer){
        ResponseParser response=restClient.callCreateCustomerAPI(customer);

        return Response.status(Response.Status.fromStatusCode(response.getStatusCode())).entity(response.getBody()).build();
    }
}
