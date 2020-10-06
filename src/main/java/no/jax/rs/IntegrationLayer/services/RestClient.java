package no.jax.rs.IntegrationLayer.services;

import no.jax.rs.IntegrationLayer.models.Agreement;
import no.jax.rs.IntegrationLayer.models.Customer;
import no.jax.rs.IntegrationLayer.models.ResponseParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestClient {
    // Defining endpoints here
    private static final  String CREATE_CUSTOMER_API = "http://localhost:8111/api2/customer";
    private static final  String CREATE_AGREEMENT_API = "http://localhost:8111/api2/agreement";
    static RestTemplate restTemplate = new RestTemplate();

    public ResponseParser callCreateCustomerAPI(Customer customer){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response= restTemplate.postForEntity(CREATE_CUSTOMER_API,customer,String.class);
        return new ResponseParser(response.getBody(),response.getStatusCodeValue());

    }
    public  ResponseParser callCreateAgreementAPI(Agreement agreement){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response= restTemplate.postForEntity(CREATE_AGREEMENT_API,agreement,String.class);
        System.out.println("repsone from agreement");
        System.out.println(response);
        return new ResponseParser(response.getBody(),response.getStatusCodeValue());
    }
}
