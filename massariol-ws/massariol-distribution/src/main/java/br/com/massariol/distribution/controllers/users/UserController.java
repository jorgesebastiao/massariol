package br.com.massariol.distribution.controllers.users;

import br.com.massariol.application.users.UserAppService;
import br.com.massariol.application.users.commands.CompanyUserCreateCommand;
import br.com.massariol.application.users.commands.CompanyUserUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.users.viewmodels.UserCompanyViewModel;
import br.com.massariol.domain.features.users.User;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/users")
@Api(value="users", description="Users")
public class UserController extends ApiBaseController {

    private final UserAppService userAppService;

    public UserController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, UserAppService userAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.userAppService = userAppService;
    }

    @GetMapping("/companies/{companyId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<UserCompanyViewModel> getByCompanyId(@PathVariable Long companyId) {
        User user = userAppService.getByCompanyId(companyId);
        return ok(sourceToDestination(user, UserCompanyViewModel.class));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity postUserCompany(@RequestBody CompanyUserCreateCommand companyUserCreateCommand ) {
        userAppService.createUserCompany(companyUserCreateCommand);
        return status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody CompanyUserUpdateCommand companyUserUpdateCommand) {
        userAppService.updateUserCompany(companyUserUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }
}
