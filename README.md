# IntegrationLayer
Spring boot API with mockserver  and systemtest

# About
This project is built in spring boot using jersey (as jax-rs implementation).
This API has only one endpoint /agreement located at /web/AgreementController. The main objective of this endpoint is it consumes 
4 different APIs from 2 different servers. And outputs success if all the calls succeeds. The business logic of this project i.e consuming 3rd party APIs is located at
services/ResClientjava.

# System Integration Testing
System integration test is written in a file called SystemIT.java located at test/java/no.jax.rs.IntegrationLayer/SystemIT.java.
It uses mockserver to create two mock servers with 4 expectations so that the test could be run without 3rd party APIs.
Test can be run using the command "mvn clean test".

