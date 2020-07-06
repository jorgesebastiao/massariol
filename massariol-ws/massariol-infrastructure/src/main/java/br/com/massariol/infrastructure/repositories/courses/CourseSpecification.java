package br.com.massariol.infrastructure.repositories.courses;

import br.com.massariol.domain.features.courses.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {
    public static Specification<Course> filter(String filter) {
        return (root, query, builder) -> {
            var nameFilter = builder.like(root.get("name"), "%" + filter + "%");
            var courseIdentificationFilter = builder.like(root.get("courseIdentification"), "%" + filter + "%");
            return builder.or(nameFilter, courseIdentificationFilter);
        };
    }
}
