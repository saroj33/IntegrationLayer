package no.jax.rs.IntegrationLayer.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Agreement {
    @NotBlank(message= "AgreementName is required")
    private String agreementName;
    @NotBlank(message = " CustomerID must be provided.")
    private String customerID;
    @NotNull(message = " AgreementTyoe must be provided.")
    private Integer agreementType;

    public Agreement(String agreementName, String customerID, Integer agreementType){
        super();
        this.agreementName=agreementName;
        this.customerID=customerID;
        this.agreementType=agreementType;
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
}
