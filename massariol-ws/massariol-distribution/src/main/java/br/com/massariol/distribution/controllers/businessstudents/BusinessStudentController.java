package br.com.massariol.distribution.controllers.businessstudents;

import br.com.massariol.application.businessstudents.BusinessStudentAppService;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.businessstudents.viewmodel.BusinessStudentViewModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-students")
@Api(value = "businessStudents", description = "businessStudents")
public class BusinessStudentController extends ApiBaseController {
    private final BusinessStudentAppService businessStudentAppService;

    public BusinessStudentController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper,
                                     BusinessStudentAppService businessStudentAppService){
    super(resourceServerTokenServices,modelMapper);
        this.businessStudentAppService = businessStudentAppService;
    }

    @ApiOperation(value = "return all businessStudents")
    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_COMPANY_CERTIFICATE') and #oauth2.hasScope('read')")
    public Page<BusinessStudentViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) throws Exception{
        var businessstudents = businessStudentAppService.findAll(getCompanyId(), pageable, filter);
        return handlePageResult(pageable, businessstudents, BusinessStudentViewModel.class);
    }
}
