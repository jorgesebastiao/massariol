package br.com.massariol.infrastructure.repositories.instructors;

import br.com.massariol.domain.features.instructors.Instructor;
import org.springframework.data.jpa.domain.Specification;

public class InstructorSpecification {
    public static Specification<Instructor> filter(String filter) {
        return (root, query, builder) -> {
            var personJoin = root.join("person");
            var nameFilter = builder.like(personJoin.get("name"), "%" + filter + "%");
            var cpfFilter = builder.like(personJoin.get("cpf"), "%" + filter + "%");
            return builder.or(nameFilter, cpfFilter);
        };
    }
}
