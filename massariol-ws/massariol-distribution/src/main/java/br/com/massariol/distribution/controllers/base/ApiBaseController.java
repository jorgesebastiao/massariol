package br.com.massariol.distribution.controllers.base;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.List;
import java.util.stream.Collectors;

public class ApiBaseController {

    private final ResourceServerTokenServices resourceServerTokenServices;
    private final ModelMapper modelMapper;

    public ApiBaseController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper) {
        this.resourceServerTokenServices = resourceServerTokenServices;
        this.modelMapper = modelMapper;
    }

   protected Long getUserId() {
       var userId = (Integer)getAdditionalInformation("user_id");
       return userId.longValue();
    }

    protected Long getCompanyId() {
        var companyId = (Integer)getAdditionalInformation("company_id");
        return companyId.longValue();
    }

    List<String> getPermissions(){
        return (List<String>) getAdditionalInformation("authorities");
    }

    private Object getAdditionalInformation(String additionalInformation) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AccessToken auth2AccessToken = resourceServerTokenServices.readAccessToken(((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue());
        return auth2AccessToken.getAdditionalInformation().get(additionalInformation);
    }

    protected <TSource,TDestination> Page<TDestination> handlePageResult(Pageable pageable, Page<TSource> sources, Class<TDestination> destination){
        return new PageImpl<>(sources.stream()
                .map(source -> sourceToDestination(source,destination))
                .collect(Collectors.toList()), pageable, sources.getTotalElements());
    }

    protected <TSource, TDestination> TDestination sourceToDestination(TSource source, Class<TDestination> destination) {
        return modelMapper.map(source, destination);
    }
}
