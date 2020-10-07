package no.jax.rs.IntegrationLayer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import com.google.gson.Gson;
import no.jax.rs.IntegrationLayer.models.AgreementAPI;
import no.jax.rs.IntegrationLayer.models.CustomerAPI;
import no.jax.rs.IntegrationLayer.models.LetterServiceAPI;
import org.mockserver.integration.ClientAndServer;

public class Expectations {
    private static Gson gson = new Gson();
    public static void createDefaultExpectations(ClientAndServer mockServer1,ClientAndServer mockServer2) {
        createCustomer(mockServer1);
        createAgreement(mockServer1);
        // Using another mock server for letter service
        sendAgreementToCustomer(mockServer2);
        updateAgreement(mockServer1);
    }
    private static void createCustomer (ClientAndServer mockServer) {
        CustomerAPI customerAPI = new CustomerAPI("John","Rambo","12345678901","example@example.com");
        mockServer.when(request().withMethod("POST")
        .withHeader("Content-Type", "application/json").withPath("/api2/customer")
        .withBody(gson.toJson(customerAPI)))
        .respond(response().withStatusCode(200).withBody("dsfsdf-sdfsdf-sdfds")) ;
    }

    private static void createAgreement (ClientAndServer mockServer) {
        AgreementAPI agreementApi = new AgreementAPI("TestAgreement","dsfsdf-sdfsdf-sdfds",5,"started");
        mockServer.when(request().withMethod("POST")
                .withHeader("Content-Type", "application/json").withPath("/api2/agreement")
                .withBody(gson.toJson(agreementApi)))
                .respond(response().withStatusCode(200).withBody("unique-agreement-ID")) ;
    }

    private static void updateAgreement (ClientAndServer mockServer) {
        AgreementAPI agreementApi = new AgreementAPI("TestAgreement","dsfsdf-sdfsdf-sdfds",5,"Avtale sendt");
        // Add id to the object
        agreementApi.setId("unique-agreement-ID");
        mockServer.when(request().withMethod("PATCH")
                .withHeader("Content-Type", "application/json").withPath("/api2/agreement")
                .withBody(gson.toJson(agreementApi)))
                .respond(response().withStatusCode(200).withBody("{'agreementID':'unique-agreement-ID','agreementStatus':'Avtale sendt'}")) ;
    }

    private static void sendAgreementToCustomer (ClientAndServer mockServer) {
        LetterServiceAPI letterServiceAPI = new LetterServiceAPI("unique-agreement-ID","dsfsdf-sdfsdf-sdfds","example@example.com",5);
        mockServer.when(request().withMethod("POST")
                .withHeader("Content-Type", "application/json").withPath("/post-letter")
                .withBody(gson.toJson(letterServiceAPI)))
                .respond(response().withStatusCode(200).withBody("sent")) ;
    }
}
