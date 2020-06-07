package br.com.massariol.infrastructure.repositories.students;

import br.com.massariol.domain.features.students.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> filter(String filter) {
        return (root, query, builder) -> {
            var nameFilter = builder.like(root.get("name"), "%" + filter + "%");
            var cpfFilter = builder.like(root.get("cpf"), "%" + filter + "%");
            return builder.or(nameFilter, cpfFilter);
        };
    }
}
