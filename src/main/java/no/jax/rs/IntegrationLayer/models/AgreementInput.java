package no.jax.rs.IntegrationLayer.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AgreementInput {
    @NotBlank(message= "First name is required")
    private String firstName;
    @NotBlank(message= "last name is required")
    private String lastName;
    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "norwegianID must only contain numbers  0-9")
    private String norwegianID;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message= "AgreementName is required")
    private String agreementName;
    @NotNull(message = " AgreementTyoe must be provided.")
    private Integer agreementType;

    public AgreementInput(@NotBlank(message = "First name is required") String firstName, @NotBlank(message = "last name is required") String lastName, @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "norwegianID must only contain numbers  0-9") String norwegianID, @Email(message = "Email should be valid") String email, @NotBlank(message = "AgreementName is required") String agreementName, @NotNull(message = " AgreementTyoe must be provided.") Integer agreementType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.norwegianID = norwegianID;
        this.email = email;
        this.agreementName = agreementName;
        this.agreementType = agreementType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNorwegianID() {
        return norwegianID;
    }

    public void setNorwegianID(String norwegianID) {
        this.norwegianID = norwegianID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public Integer getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(Integer agreementType) {
        this.agreementType = agreementType;
    }
}
