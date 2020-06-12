package br.com.massariol.application.trainings;

import br.com.massariol.application.businessstudents.BusinessStudentAppService;
import br.com.massariol.application.companies.CompanyAppService;
import br.com.massariol.application.courses.CourseAppService;
import br.com.massariol.application.instructors.InstructorAppService;
import br.com.massariol.application.students.StudentAppService;
import br.com.massariol.application.supervisors.SupervisorAppService;
import br.com.massariol.application.trainings.commands.TrainingCreateCommand;
import br.com.massariol.application.trainings.commands.TrainingUpdateCommand;
import br.com.massariol.domain.features.trainings.Training;
import br.com.massariol.infrastructure.repositories.trainings.TrainingRepository;
import br.com.massariol.infrastructure.repositories.trainings.TrainingSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class TrainingAppServiceImpl implements TrainingAppService {
    private final ModelMapper modelMapper;
    private final TrainingRepository trainingRepository;
    private final CompanyAppService companyAppService;
    private final CourseAppService courseAppService;
    private final InstructorAppService instructorAppService;
    private final StudentAppService studentAppService;
    private final SupervisorAppService supervisorAppService;
    private  final BusinessStudentAppService businessstudentAppService;
    public TrainingAppServiceImpl(ModelMapper modelMapper, TrainingRepository trainingRepository, CompanyAppService companyAppService, CourseAppService courseAppService, InstructorAppService instructorAppService, StudentAppService studentAppService, SupervisorAppService supervisorAppService, BusinessStudentAppService businessstudentAppService) {
        this.modelMapper = modelMapper;
        this.trainingRepository = trainingRepository;
        this.companyAppService = companyAppService;
        this.courseAppService = courseAppService;
        this.instructorAppService = instructorAppService;
        this.studentAppService = studentAppService;
        this.supervisorAppService = supervisorAppService;
        this.businessstudentAppService = businessstudentAppService;
    }

    public Page<Training> findAll(Pageable pageable, String filter) {
        return trainingRepository.findAll(pageable);
    }

    public Training getById(Long id) {
        return this.trainingRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Page<Training> findAllByBusinessStudentIdAndCompanyId(Long businessStudentId, Long companyId, Pageable pageable, String filter) {
        return trainingRepository.findAll(TrainingSpecification.findAllByCompanyId(businessStudentId, companyId,filter),pageable);
    }

    @Transactional
    public Long add(TrainingCreateCommand trainingCreateCommand) {
        var training = modelMapper.map(trainingCreateCommand, Training.class);

        var company = companyAppService.getById(trainingCreateCommand.getCompanyId());
        var student = studentAppService.getById(trainingCreateCommand.getStudentId());

        var businessStudent = businessstudentAppService.manager(student, company);
        training.setBusinessStudent(businessStudent);

        var course = courseAppService.getById(trainingCreateCommand.getCourseId());
        training.setCourse(course);

        var instructor = instructorAppService.getById(trainingCreateCommand.getInstructorId());
        training.setInstructor(instructor);

        var supervisor = supervisorAppService.getById(trainingCreateCommand.getSupervisorId());
        training.setSupervisor(supervisor);

        training.setExpirationDate(LocalDate.now().plusYears(course.getValidityInYears()));

        trainingRepository.save(training);
        return training.getId();
    }

    @Transactional
    public void update(TrainingUpdateCommand trainingUpdateCommand) {
        var trainingDatabase = trainingRepository.findById(trainingUpdateCommand.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(trainingUpdateCommand,trainingDatabase);

        var company = companyAppService.getById(trainingUpdateCommand.getCompanyId());
        var student = studentAppService.getById(trainingUpdateCommand.getStudentId());

        var businessStudent = businessstudentAppService.manager(student, company);
        trainingDatabase.setBusinessStudent(businessStudent);

        var course = courseAppService.getById(trainingUpdateCommand.getCourseId());
        trainingDatabase.setCourse(course);

        var instructor = instructorAppService.getById(trainingUpdateCommand.getInstructorId());
        trainingDatabase.setInstructor(instructor);

        var supervisor = supervisorAppService.getById(trainingUpdateCommand.getSupervisorId());
        trainingDatabase.setSupervisor(supervisor);

        trainingDatabase.setExpirationDate(LocalDate.now().plusYears(course.getValidityInYears()));

        trainingRepository.save(trainingDatabase);
    }
}
