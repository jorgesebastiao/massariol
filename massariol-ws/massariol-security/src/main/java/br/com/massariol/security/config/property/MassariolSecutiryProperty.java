package br.com.massariol.security.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("massariol-security")
public class MassariolSecutiryProperty {

    @Getter
    @Setter
    private List<String> allowedOrigin = new ArrayList<>();

    @Getter
    @Setter
    private Security security;

    public static class Security {

        @Getter
        @Setter
        private boolean enableHttps;

        @Getter
        @Setter
        private String oauthPath;
    }
}
