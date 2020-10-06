package no.jax.rs.IntegrationLayer.web;

import no.jax.rs.IntegrationLayer.models.Agreement;
import no.jax.rs.IntegrationLayer.models.ResponseParser;
import no.jax.rs.IntegrationLayer.services.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/agreement")
@CrossOrigin
public class AgreementController {
    @Autowired
    RestClient restClient;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAgreement(@Valid Agreement agreement){
        ResponseParser response= restClient.callCreateAgreementAPI(agreement);
        return Response.status(Response.Status.fromStatusCode(response.getStatusCode())).entity(response.getBody()).build();
    }
}
