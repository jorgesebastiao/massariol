package br.com.massariol.distribution.controllers.instructors;

import br.com.massariol.application.instructors.InstructorAppService;
import br.com.massariol.application.instructors.commands.InstructorCreateCommand;
import br.com.massariol.application.instructors.commands.InstructorUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.instructors.viewmodels.InstructorDetailViewModel;
import br.com.massariol.distribution.controllers.instructors.viewmodels.InstructorResumeViewModel;
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
@RequestMapping("/instructors")
@Api(value="instructors" ,description="instructors")
public class InstructorController extends ApiBaseController {
    private final InstructorAppService instructorAppService;

    public InstructorController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, InstructorAppService instructorAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.instructorAppService = instructorAppService;
    }

    @ApiOperation(value = "View a list of instructors for company", response = InstructorResumeViewModel.class)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public Page<InstructorResumeViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        var instructors = instructorAppService.findAll(pageable, filter);
        return handlePageResult(pageable, instructors, InstructorResumeViewModel.class);
    }

    @ApiOperation(value = "View a instructor by id", response = InstructorDetailViewModel.class)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<InstructorDetailViewModel> getById(@PathVariable Long id) {
        return  ok(sourceToDestination(instructorAppService.getById(id), InstructorDetailViewModel.class));
    }

    @ApiOperation(value = "create of instructor")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity post(@RequestBody InstructorCreateCommand instructorCreateCommand) {
        return  status(HttpStatus.CREATED).body(instructorAppService.add(instructorCreateCommand));
    }

    @ApiOperation(value = "update of instructor")
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody InstructorUpdateCommand instructorUpdateCommand) {
        instructorAppService.update(instructorUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }
}
