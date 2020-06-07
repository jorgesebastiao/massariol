package br.com.massariol.infrastructure.repositories.courses;

import br.com.massariol.domain.features.courses.Course;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends RepositoryBase<Course,Long> {

}
