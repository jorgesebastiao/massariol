package br.com.massariol.distribution.controllers.trainings;

import br.com.massariol.application.trainings.TrainingAppService;
import br.com.massariol.application.trainings.commands.TrainingCreateCommand;
import br.com.massariol.application.trainings.commands.TrainingUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.trainings.viewmodels.TrainingDetailViewModel;
import br.com.massariol.distribution.controllers.trainings.viewmodels.TrainingResumeViewModel;
import br.com.massariol.distribution.controllers.trainings.viewmodels.TrainingWithCertificateViewModel;
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
@RequestMapping("/trainings")
@Api(value="trainings", description="Trainings")
public class TrainingController extends ApiBaseController {
    private  final TrainingAppService trainingAppService;
    public TrainingController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, TrainingAppService trainingAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.trainingAppService = trainingAppService;
    }

    @ApiOperation(value = "View a list of trainings", response = TrainingResumeViewModel.class)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public Page<TrainingResumeViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        var trainings = trainingAppService.findAll(pageable, filter);
        return handlePageResult(pageable, trainings, TrainingResumeViewModel.class);
    }

    @ApiOperation(value = "view a list of trainings by businessStudentId")
    @GetMapping("/business-student/{businessStudentId}")
    @PreAuthorize("hasAuthority('ROLE_COMPANY_CERTIFICATE') and #oauth2.hasScope('read')")
    public Page<TrainingWithCertificateViewModel> getAllByBusinessStudentId(@PathVariable Long businessStudentId, @RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) throws Exception{
        var trainings = trainingAppService.findAllByBusinessStudentIdAndCompanyId(businessStudentId, getCompanyId(), pageable, filter);
        return handlePageResult(pageable, trainings, TrainingWithCertificateViewModel.class);
    }

    @ApiOperation(value = "View a training by id", response = TrainingDetailViewModel.class)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<TrainingDetailViewModel> getById(@PathVariable Long id) {
        return  ok(sourceToDestination(trainingAppService.getById(id), TrainingDetailViewModel.class));
    }

    @ApiOperation(value = "create of training")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity post(@RequestBody TrainingCreateCommand trainingCreateCommand) {
        return  status(HttpStatus.CREATED).body(trainingAppService.add(trainingCreateCommand));
    }

    @ApiOperation(value = "update of training")
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody TrainingUpdateCommand trainingUpdateCommand) {
        trainingAppService.update(trainingUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }
}
