package br.com.massariol.distribution.controllers.users;

import br.com.massariol.application.users.UserAppService;
import br.com.massariol.application.users.commands.CompanyUserCreateCommand;
import br.com.massariol.application.users.commands.CompanyUserUpdateCommand;
import br.com.massariol.application.users.commands.UserCreateCommand;
import br.com.massariol.application.users.commands.UserUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.users.viewmodels.UserCompanyViewModel;
import br.com.massariol.distribution.controllers.users.viewmodels.UserDetailViewModel;
import br.com.massariol.distribution.controllers.users.viewmodels.UserResumeViewModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @ApiOperation(value = "View a list of users", response = UserResumeViewModel.class)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public Page<UserResumeViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        return handlePageResult(pageable, userAppService.findAll(pageable, filter), UserResumeViewModel.class);
    }

    @ApiOperation(value = "View a user by id", response = UserDetailViewModel.class)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<UserDetailViewModel> getById(@PathVariable Long id) {
        return ok(sourceToDestination(userAppService.getById(id), UserDetailViewModel.class));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity post(@RequestBody UserCreateCommand command) {
        userAppService.add(command);
        return status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody UserUpdateCommand command) {
        userAppService.update(command);
        return status(HttpStatus.CREATED).build();
    }
}
