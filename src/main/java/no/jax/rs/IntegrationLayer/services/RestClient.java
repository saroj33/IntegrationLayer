package no.jax.rs.IntegrationLayer.services;

import no.jax.rs.IntegrationLayer.models.*;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestClient {
    // Defining endpoints here
    private static final  String CREATE_CUSTOMER_API = "http://localhost:8111/api2/customer";
    private static final  String CREATE_AGREEMENT_API = "http://localhost:8111/api2/agreement";
    private static final  String SEND_AGREEMENT_TO_CUSTOMER_API = "http://localhost:9111/post-letter";
    static RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public ResponseParser callCreateCustomerAPI(CustomerAPI customerAPI){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<CustomerAPI> entity = new HttpEntity<>(customerAPI, headers);
        ResponseEntity<String> response= restTemplate.postForEntity(CREATE_CUSTOMER_API, entity,String.class);
        return new ResponseParser(response.getBody(),response.getStatusCodeValue());

    }
    public  ResponseParser callCreateAgreementAPI(AgreementAPI agreementAPI){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<AgreementAPI> entity = new HttpEntity<>(agreementAPI, headers);
        ResponseEntity<String> response= restTemplate.postForEntity(CREATE_AGREEMENT_API, entity,String.class);
        System.out.println("repsone from agreement");
        System.out.println(response);
        return new ResponseParser(response.getBody(),response.getStatusCodeValue());
    }
    public  ResponseParser callUpdateAgreementAPI(AgreementAPI agreementAPI){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<AgreementAPI> entity = new HttpEntity<>(agreementAPI, headers);
        ResponseEntity<String> response= restTemplate.exchange(CREATE_AGREEMENT_API,HttpMethod.PATCH, entity,String.class);
        System.out.println("repsone from patch");
        System.out.println(response);
        return new ResponseParser(response.getBody(),response.getStatusCodeValue());
    }
    public ResponseParser callSendAgreementToCustomer (LetterServiceAPI letterServiceAPI){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<LetterServiceAPI> entity = new HttpEntity<>(letterServiceAPI, headers);
        ResponseEntity<String> response= restTemplate.postForEntity(SEND_AGREEMENT_TO_CUSTOMER_API, entity,String.class);

        return new ResponseParser(response.getBody(),response.getStatusCodeValue());

    }

    public  ResponseParser createAgreementHandler (AgreementInput agreementInput){
        CustomerAPI customerAPI = new CustomerAPI(agreementInput.getFirstName(),agreementInput.getLastName(),agreementInput.getNorwegianID(),agreementInput.getEmail());
        ResponseParser customerAPIResponse= this.callCreateCustomerAPI(customerAPI);

        AgreementAPI agreementAPI= new AgreementAPI(agreementInput.getAgreementName(),customerAPIResponse.getBody(),agreementInput.getAgreementType(),"started");
        ResponseParser agreementAPIResponse =this.callCreateAgreementAPI(agreementAPI);

        LetterServiceAPI letterServiceAPI= new LetterServiceAPI(agreementAPIResponse.getBody(),customerAPIResponse.getBody(),agreementInput.getEmail(),agreementInput.getAgreementType());
        ResponseParser letterServiceAPIResponse = this.callSendAgreementToCustomer(letterServiceAPI);

        // Setting ID on the agreementAPI object
        agreementAPI.setId(agreementAPIResponse.getBody());
        // Setting status as send
        agreementAPI.setAgreementStatus("avtale Sendt");
        ResponseParser updateAgreementAPIResponse= this.callUpdateAgreementAPI(agreementAPI);
        System.out.println("Latest Body");
        System.out.println(updateAgreementAPIResponse.getBody());
        return new ResponseParser(updateAgreementAPIResponse.getBody(),updateAgreementAPIResponse.getStatusCode());
    }
}
