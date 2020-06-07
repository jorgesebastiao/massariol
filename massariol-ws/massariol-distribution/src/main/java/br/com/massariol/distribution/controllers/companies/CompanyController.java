package br.com.massariol.distribution.controllers.companies;

import br.com.massariol.application.companies.CompanyAppService;
import br.com.massariol.application.companies.commands.CompanyCreateCommand;
import br.com.massariol.application.companies.commands.CompanyUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.companies.viewmodels.CompanyDetailViewModel;
import br.com.massariol.distribution.controllers.companies.viewmodels.CompanyResumeViewModel;
import br.com.massariol.domain.features.companies.Company;
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
@RequestMapping("/companies")
@Api(value="companies" ,description="Companies")
public class CompanyController extends ApiBaseController {
    private final CompanyAppService companyAppService;

    public CompanyController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, CompanyAppService companyAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.companyAppService = companyAppService;
    }

    @ApiOperation(value = "View a list of companies", response = CompanyResumeViewModel.class)
    @GetMapping
    public Page<CompanyResumeViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        Page<Company> companies = companyAppService.findAll(pageable,filter);
        return  handlePageResult(pageable,companies,CompanyResumeViewModel.class);
    }

    @ApiOperation(value = "View a company by id", response = CompanyDetailViewModel.class)
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDetailViewModel> getById(@PathVariable Long id) {
        return  ok(sourceToDestination(companyAppService.getById(id), CompanyDetailViewModel.class));
    }

    @ApiOperation(value = "Register a company")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity post(@RequestBody CompanyCreateCommand companyCreateCommand) {
        return  status(HttpStatus.CREATED).body(companyAppService.add(companyCreateCommand));
    }

    @ApiOperation(value = "Update a company")
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody CompanyUpdateCommand companyUpdateCommand) {
        companyAppService.update(companyUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }
/*
    @ApiOperation(value = "Block a company")
    @PutMapping("block")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity putBlock(@RequestBody CompanyUpdateCommand companyUpdateCommand) {
        companyAppService.update(companyUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Unlock a company")
    @PutMapping("unlock")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity putUnlock(@RequestBody CompanyUpdateCommand companyUpdateCommand) {
        companyAppService.update(companyUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }*/
}
