package br.com.massariol.application.courses;

import br.com.massariol.application.courses.commands.CourseCreateCommand;
import br.com.massariol.application.courses.commands.CourseUpdateCommand;
import br.com.massariol.domain.features.courses.Course;
import br.com.massariol.infrastructure.repositories.courses.CourseRepository;
import br.com.massariol.infrastructure.repositories.courses.CourseSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseAppServiceImpl implements CourseAppService {
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    public CourseAppServiceImpl(ModelMapper modelMapper, CourseRepository courseRepository) {
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
    }

    public Page<Course> findAll(Pageable pageable, String filter) {
        return courseRepository.findAll(CourseSpecification.filter(filter), pageable);
    }

    public Course getById(Long id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Long add(CourseCreateCommand courseCreateCommand) {
        var course = modelMapper.map(courseCreateCommand, Course.class);
        courseRepository.save(course);
        return course.getId();
    }

    public void update(CourseUpdateCommand courseUpdateCommand) {
        var courseDatabase = courseRepository.findById(courseUpdateCommand.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(courseUpdateCommand,courseDatabase);

        courseRepository.save(courseDatabase);
    }
}
