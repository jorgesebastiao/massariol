package br.com.massariol.distribution.config;

import br.com.massariol.distribution.config.property.MassariolApiDistributionProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "br.com.massariol.distribution.controllers")
public class SwaggerConfig {

    private final MassariolApiDistributionProperty massariolApiDistributionProperty;

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES;

    static {
        DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Collections.singletonList("application/json"));
    }

    public  SwaggerConfig(MassariolApiDistributionProperty massariolApiDistributionProperty){
        this.massariolApiDistributionProperty = massariolApiDistributionProperty;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.massariol.distribution.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()))
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MASSARIOL-API",
                "MASSARIOL API.",
                "1.0.0-SNAPSHOT",
                "Termos de serviço",
                new Contact("Jorge Sebastião Rodrigues Corrêa", "http://massarioltreinamentos.com.br/", "jorgenes1@hotmail.com"),
                "Licensa de uso", "licença de uso", Collections.emptyList());
    }

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "apiKey", "header");
    }

    @Bean
    public SecurityScheme apiCookieKey() {
        return new ApiKey(HttpHeaders.COOKIE, "apiKey", "cookie");
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(massariolApiDistributionProperty.getClientId())
                .clientSecret(massariolApiDistributionProperty.getClientSecret())
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .appName("Bearer access token")
                .build();
    }

    private OAuth securitySchema() {
        List<GrantType> grantTypes = new ArrayList<>();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(massariolApiDistributionProperty.getAuthServer());
        grantTypes.add(creGrant);

        return new OAuth("oauth2schema", scopes(), grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes()));
    }

    private List<AuthorizationScope> scopes() {
        List<AuthorizationScope> scopes = new ArrayList<>();
        scopes.add( new AuthorizationScope("read", "for read operations"));
        scopes.add( new AuthorizationScope("write", "for write operations"));
        return scopes;
    }

    private    AuthorizationScope[] authorizationScopes(){
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("write", "write all");
        return  authorizationScopes;
    }
}
