package br.com.massariol.distribution.config;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableConfigurationProperties({ResourceProperties.class})
public class StaticResourcesConfiguration implements WebMvcConfigurer {

    static final String[] STATIC_RESOURCES = new String[]{
            "/**/*.css",
            "/**/*.ico",
            "/**/*.html",
            "/**/*.js",
            "/**/*.json",
            "/**/*.bmp",
            "/**/*.jpeg",
            "/**/*.jpg",
            "/**/*.png",
            "/**/*.ttf",
            "/**/*.eot",
            "/**/*.svg",
            "/**/*.woff",
            "/**/*.woff2"
    };

    private final ResourceProperties resourceProperties;

    public StaticResourcesConfiguration(ResourceProperties resourceProperties) {
        this.resourceProperties = resourceProperties;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Add all static files
        Integer cachePeriod = 160;
        registry.addResourceHandler(STATIC_RESOURCES)
                .addResourceLocations(resourceProperties.getStaticLocations())
                .setCachePeriod(cachePeriod);

        //Create mapping to index.html for Angular HTML5 mode.
        String[] indexLocations = getIndexLocations();
        registry.addResourceHandler("/**")
                .addResourceLocations(indexLocations)
                .setCachePeriod(cachePeriod)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        return location.exists() && location.isReadable() ? location : null;
                    }
                });
    }

    private String[] getIndexLocations() {
        return Arrays.stream(resourceProperties.getStaticLocations())
                .map((location) -> location + "index.html")
                .toArray(String[]::new);
    }

    public void configurePathMatch(PathMatchConfigurer configure) {
        configure.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
    }
}
