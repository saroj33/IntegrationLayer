package no.jax.rs.IntegrationLayer.models;

public class LetterServiceAPI {
    private String agreementID;
    private String customerID;
    private String customerEmail;
    private Integer agreementType;

    public LetterServiceAPI(String agreementID, String customerID, String customerEmail, Integer agreementType) {
        this.agreementID = agreementID;
        this.customerID = customerID;
        this.customerEmail = customerEmail;
        this.agreementType = agreementType;
    }

    public String getAgreementID() {
        return agreementID;
    }

    public void setAgreementID(String agreementID) {
        this.agreementID = agreementID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(Integer agreementType) {
        this.agreementType = agreementType;
    }
}
