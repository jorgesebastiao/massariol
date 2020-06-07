package br.com.massariol.distribution.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("massariol-distribution")
@Getter
@Setter
public class MassariolApiDistributionProperty {
    @Getter
    @Setter
    private String authServer;

    @Getter
    @Setter
    private String clientId;

    @Getter
    @Setter
    private String clientSecret;
}
