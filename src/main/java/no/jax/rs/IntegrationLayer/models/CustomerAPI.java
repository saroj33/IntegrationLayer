package no.jax.rs.IntegrationLayer.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerAPI {
    @NotBlank(message= "First name is required")
    private String firstName;
    @NotBlank(message= "last name is required")
    private String lastName;
    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "norwegianID must only contain numbers  0-9")
    private String norwegianID;
    @Email(message = "Email should be valid")
    private String email;
    public CustomerAPI(String firstName, String lastName, String norwegianID, String email){
        super();
        this.firstName=firstName;
        this.lastName=lastName;
        this.norwegianID=norwegianID;
        this.email=email;
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
}
