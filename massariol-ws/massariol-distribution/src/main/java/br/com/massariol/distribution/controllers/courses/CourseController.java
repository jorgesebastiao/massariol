package br.com.massariol.distribution.controllers.courses;

import br.com.massariol.application.courses.CourseAppService;
import br.com.massariol.application.courses.commands.CourseCreateCommand;
import br.com.massariol.application.courses.commands.CourseUpdateCommand;
import br.com.massariol.distribution.controllers.base.ApiBaseController;
import br.com.massariol.distribution.controllers.courses.viewmodels.CourseDetailViewModel;
import br.com.massariol.distribution.controllers.courses.viewmodels.CourseResumeViewModel;
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
@RequestMapping("/courses")
@Api(value = "courses", description = "Courses")
public class CourseController extends ApiBaseController {
    private final CourseAppService courseAppService;

    public CourseController(ResourceServerTokenServices resourceServerTokenServices, ModelMapper modelMapper, CourseAppService courseAppService) {
        super(resourceServerTokenServices, modelMapper);
        this.courseAppService = courseAppService;
    }

    @ApiOperation(value = "View a list of courses", response = CourseResumeViewModel.class)
    @GetMapping
    public Page<CourseResumeViewModel> getAll(@RequestParam(required = false, defaultValue = "") String filter, Pageable pageable) {
        var courses = courseAppService.findAll(pageable, filter);
        return handlePageResult(pageable, courses, CourseResumeViewModel.class);
    }

    @ApiOperation(value = "View a course by id", response = CourseDetailViewModel.class)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('read')")
    public ResponseEntity<CourseDetailViewModel> getById(@PathVariable Long id) {
        return  ok(sourceToDestination(courseAppService.getById(id), CourseDetailViewModel.class));
    }

    @ApiOperation(value = "Create a course")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity post(@RequestBody CourseCreateCommand courseCreateCommand) {
        return  status(HttpStatus.CREATED).body(courseAppService.add(courseCreateCommand));
    }

    @ApiOperation(value = "Update a course")
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN_MASSARIOL') and #oauth2.hasScope('write')")
    public ResponseEntity put(@RequestBody CourseUpdateCommand courseUpdateCommand) {
        courseAppService.update(courseUpdateCommand);
        return status(HttpStatus.CREATED).build();
    }
}
