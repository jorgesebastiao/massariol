package br.com.massariol.distribution.controllers.supervisors;

import br.com.massariol.application.supervisors.SupervisorAppService;
import br.com.massariol.application.supervisors.commands.SupervisorCreateCommand;
import br.com.massariol.application.supervisors.commands.SupervisorUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.supervisors.viewmodels.SupervisorDetailViewModel;
import br.com.massariol.distribution.controllers.supervisors.viewmodels.SupervisorResumeViewModel;
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
@RequestMapping("/supervisors")
@Api(value = "supervisors", description = "Supervisors")
public class SupervisorController extends ApiBaseController {
    private final SupervisorAppService supervisorAppService;

    public SupervisorController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, SupervisorAppService supervisorAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.supervisorAppService = supervisorAppService;
    }

    @ApiOperation(value = "View a list of supervisors", response = SupervisorResumeViewModel.class)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public Page<SupervisorResumeViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        var supervisors = supervisorAppService.findAll(pageable, filter);
        return handlePageResult(pageable, supervisors, SupervisorResumeViewModel.class);
    }

    @ApiOperation(value = "View a supervisor by id", response = SupervisorDetailViewModel.class)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<SupervisorDetailViewModel> getById(@PathVariable Long id) {
        return  ok(sourceToDestination(supervisorAppService.getById(id), SupervisorDetailViewModel.class));
    }

    @ApiOperation(value = "create of supervisor")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity post(@RequestBody SupervisorCreateCommand supervisorCreateCommand) {
        return  status(HttpStatus.CREATED).body(supervisorAppService.add(supervisorCreateCommand));
    }

    @ApiOperation(value = "update of supervisor")
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody SupervisorUpdateCommand supervisorUpdateCommand) {
        supervisorAppService.update(supervisorUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }

}
