package no.jax.rs.IntegrationLayer.services;

import no.jax.rs.IntegrationLayer.models.*;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestClient {
    // Defining 3rd party endpoints here
    private static final  String CREATE_CUSTOMER_API = "http://localhost:8111/api2/customer";
    private static final  String CREATE_AGREEMENT_API = "http://localhost:8111/api2/agreement";
    private static final  String SEND_AGREEMENT_TO_CUSTOMER_API = "http://localhost:9111/post-letter";
    static RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public ResponseParser makeHttpCalls(Object calledClass,String url, HttpMethod method){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            HttpEntity<Object> entity = new HttpEntity<>(calledClass, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);
            return new ResponseParser(response.getBody(),response.getStatusCodeValue());
        } catch (HttpStatusCodeException ex){
            throw  ex;
        }
    }

    public  ResponseParser createAgreementHandler (AgreementInput agreementInput){

        try {
            // Creating customer
            CustomerAPI customerAPI = new CustomerAPI(agreementInput.getFirstName(), agreementInput.getLastName(), agreementInput.getNorwegianID(), agreementInput.getEmail());
            ResponseParser customerAPIResponse = this.makeHttpCalls(customerAPI, CREATE_CUSTOMER_API, HttpMethod.POST);

            // Creating agreement for the customer created above
            AgreementAPI agreementAPI = new AgreementAPI(agreementInput.getAgreementName(), customerAPIResponse.getBody(), agreementInput.getAgreementType(), "started");
            ResponseParser agreementAPIResponse = this.makeHttpCalls(agreementAPI, CREATE_AGREEMENT_API, HttpMethod.POST);

            // Sending the agreement to the customer
            LetterServiceAPI letterServiceAPI = new LetterServiceAPI(agreementAPIResponse.getBody(), customerAPIResponse.getBody(), agreementInput.getEmail(), agreementInput.getAgreementType());
            ResponseParser letterServiceAPIResponse = this.makeHttpCalls(letterServiceAPI, SEND_AGREEMENT_TO_CUSTOMER_API, HttpMethod.POST);

            // Updating agreement  status as send
            // Setting ID on the agreementAPI object
            agreementAPI.setId(agreementAPIResponse.getBody());
            agreementAPI.setAgreementStatus("Avtale sendt");
            ResponseParser updateAgreementAPIResponse = this.makeHttpCalls(agreementAPI, CREATE_AGREEMENT_API, HttpMethod.PATCH);
            return new ResponseParser(updateAgreementAPIResponse.getBody(), updateAgreementAPIResponse.getStatusCode());
        }catch (HttpStatusCodeException e){
            return new ResponseParser(e.getMessage(),e.getStatusCode().value());
        }
    }
}
