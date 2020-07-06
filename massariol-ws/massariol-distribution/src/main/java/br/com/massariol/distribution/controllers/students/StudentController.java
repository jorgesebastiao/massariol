package br.com.massariol.distribution.controllers.students;

import br.com.massariol.application.students.StudentAppService;
import br.com.massariol.application.students.commands.StudentCreateCommand;
import br.com.massariol.application.students.commands.StudentUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.students.viewmodels.StudentDetailViewModel;
import br.com.massariol.distribution.controllers.students.viewmodels.StudentResumeViewModel;
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
@RequestMapping("/students")
@Api(value="students" ,description="Students")
public class StudentController extends ApiBaseController {
    private  final StudentAppService studentAppService;

    public StudentController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, StudentAppService studentAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.studentAppService = studentAppService;
    }
    @ApiOperation(value = "View a list of students ", response = StudentResumeViewModel.class)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public Page<StudentResumeViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        var students = studentAppService.findAll(pageable, filter);
        return handlePageResult(pageable, students, StudentResumeViewModel.class);
    }

    @ApiOperation(value = "View a student by id", response = StudentDetailViewModel.class)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<StudentDetailViewModel> getById(@PathVariable Long id) {
        return  ok(sourceToDestination(studentAppService.getById(id), StudentDetailViewModel.class));
    }

    @ApiOperation(value = "Create a student")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity post(@RequestBody StudentCreateCommand studentCreateCommand) {
        return  status(HttpStatus.CREATED).body(studentAppService.add(studentCreateCommand));
    }

    @ApiOperation(value = "Update a student")
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody StudentUpdateCommand studentUpdateCommand) {
        studentAppService.update(studentUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }

/*
    @ApiOperation(value = "View a list of students for company", response = StudentResumeQuery.class)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_COMPANY_CERTIFICATE') and #oauth2.hasScope('read')")
    public Page<StudentResumeQuery> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        var studentDtos = studentAppService.findAllLegacy(pageable, getLegacyId(), filter);
        return new PageImpl<>(studentDtos.stream()
                .map(this::studentDtoToStudentResumeQuery)
                .collect(Collectors.toList()), pageable, studentDtos.getTotalElements());
    }
*/
}
