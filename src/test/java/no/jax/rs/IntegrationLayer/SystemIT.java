package no.jax.rs.IntegrationLayer;

import no.jax.rs.IntegrationLayer.models.Agreement;
import no.jax.rs.IntegrationLayer.models.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootTest(classes =IntegrationLayerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemIT {
    private static ClientAndServer mockServer;
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @BeforeEach
    public  void setupMockServer() throws IOException, JAXBException {
        mockServer = ClientAndServer.startClientAndServer(8111);
        System.out.println("mock server started");

        Expectations.createDefaultExpectations(mockServer);
    }

    @AfterEach
    public  void after() {
        mockServer.stop();
    }
    @Test
    public void testCreateCustomer() {
        // Create customer
        Customer customer= new Customer("saroj","pandey","12348911122","example@example.com");
        HttpEntity<Customer> customerHttpEntity= new HttpEntity<Customer>(customer,headers);
        ResponseEntity<String> customerResponse = restTemplate.postForEntity(
                createURLWithPort("/api/customer"),
                 customerHttpEntity, String.class);
        Assertions.assertEquals(200, customerResponse.getStatusCodeValue());
        // Create Agreement
        Agreement agreement = new Agreement("Demo Agreement",customerResponse.getBody(),5);
        HttpEntity<Agreement> agreementHttpEntity= new HttpEntity<Agreement>(agreement,headers);
        ResponseEntity<String> agreementResponse = restTemplate.exchange(
                createURLWithPort("/api/agreement"),
                HttpMethod.POST, agreementHttpEntity, String.class);
        System.out.println(agreementResponse);
        Assertions.assertEquals(200, agreementResponse.getStatusCodeValue());

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
