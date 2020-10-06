package no.jax.rs.IntegrationLayer;

import no.jax.rs.IntegrationLayer.models.AgreementAPI;
import no.jax.rs.IntegrationLayer.models.AgreementInput;
import no.jax.rs.IntegrationLayer.models.CustomerAPI;
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
    private static ClientAndServer mockServer1 ,mockServer2 ;
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @BeforeEach
    public  void setupMockServer() throws IOException, JAXBException {
        mockServer1 = ClientAndServer.startClientAndServer(8111);
        mockServer2 = ClientAndServer.startClientAndServer(9111);
        System.out.println("mock server started");

        Expectations.createDefaultExpectations(mockServer1,mockServer2);
    }

    @AfterEach
    public  void after() {
        mockServer1.stop();
        mockServer2.stop();
    }
    @Test
    public void testCreateCustomer() {
               // Create Agreement
        AgreementInput agreementInput = new AgreementInput("John","Rambo","12345678901","example@example.com","TestAgreement",5);
        HttpEntity<AgreementInput> agreementHttpEntity= new HttpEntity<AgreementInput>(agreementInput,headers);
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
