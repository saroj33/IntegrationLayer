package no.jax.rs.IntegrationLayer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import com.google.gson.Gson;
import no.jax.rs.IntegrationLayer.models.Agreement;
import no.jax.rs.IntegrationLayer.models.Customer;
import org.mockserver.integration.ClientAndServer;

public class Expectations {
    private static Gson gson = new Gson();
    public static void createDefaultExpectations(ClientAndServer mockServer) {
        createCustomer(mockServer);
        createAgreement(mockServer);
    }
    private static void createCustomer (ClientAndServer mockServer) {
        Customer customer = new Customer("saroj","pandey","12348911122","example@example.com");
        mockServer.when(request().withMethod("POST")
        .withHeader("Content-Type", "application/json").withPath("/api2/customer")
        .withBody(gson.toJson(customer)))
        .respond(response().withStatusCode(200).withBody("dsfsdf-sdfsdf-sdfds")) ;
    }

    private static void createAgreement (ClientAndServer mockServer) {
        Agreement agreement = new Agreement("Demo Agreement","dsfsdf-sdfsdf-sdfds",5);
        mockServer.when(request().withMethod("POST")
                .withHeader("Content-Type", "application/json").withPath("/api2/agreement")
                .withBody(gson.toJson(agreement)))
                .respond(response().withStatusCode(200).withBody("unique-agreement-ID")) ;
    }
}
