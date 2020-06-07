package br.com.massariol.application.courses;

import br.com.massariol.application.courses.commands.CourseCreateCommand;
import br.com.massariol.application.courses.commands.CourseUpdateCommand;
import br.com.massariol.domain.features.courses.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseAppService {
    Page<Course> findAll(Pageable pageable, String filter);

    Course getById(Long id);

    Long add(CourseCreateCommand courseCreateCommand);

    void update(CourseUpdateCommand courseUpdateCommand);
}
