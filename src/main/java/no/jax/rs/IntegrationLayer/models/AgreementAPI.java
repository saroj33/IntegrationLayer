package no.jax.rs.IntegrationLayer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AgreementAPI {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @NotBlank(message= "AgreementName is required")
    private String agreementName;
    @NotBlank(message = " CustomerID must be provided.")
    private String customerID;
    @NotNull(message = " AgreementTyoe must be provided.")
    private Integer agreementType;
    @NotNull(message = " Status must be provided.")
    private String agreementStatus;

    public AgreementAPI(@NotBlank(message = "AgreementName is required") String agreementName, @NotBlank(message = " CustomerID must be provided.") String customerID, @NotNull(message = " AgreementTyoe must be provided.") Integer agreementType, @NotNull(message = " Status must be provided.") String agreementStatus) {
        this.agreementName = agreementName;
        this.customerID = customerID;
        this.agreementType = agreementType;
        this.agreementStatus = agreementStatus;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(Integer agreementType) {
        this.agreementType = agreementType;
    }

    public String getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(String agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
