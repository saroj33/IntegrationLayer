package no.jax.rs.IntegrationLayer.config;

import no.jax.rs.IntegrationLayer.provider.GenericExceptionMapper;
import no.jax.rs.IntegrationLayer.web.AgreementController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {
    @PostConstruct
    public void init(){
        register(GenericExceptionMapper.class);
        register(AgreementController.class);
    }
}
