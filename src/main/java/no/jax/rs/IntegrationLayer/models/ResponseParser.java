package no.jax.rs.IntegrationLayer.models;

import org.mockserver.model.HttpStatusCode;
import org.springframework.http.HttpStatus;

public class ResponseParser {
    private String body;
    private Integer statusCode;

    public ResponseParser(String body, Integer statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
