package br.com.massariol.infrastructure.repositories.supervisors;

import br.com.massariol.domain.features.supervisors.Supervisor;
import org.springframework.data.jpa.domain.Specification;

public class SupervisorSpecification {
    public static Specification<Supervisor> filter(String filter) {
        return (root, query, builder) -> {
            var personJoin = root.join("person");
            var nameFilter = builder.like(personJoin.get("name"), "%" + filter + "%");
            var cpfFilter = builder.like(personJoin.get("cpf"), "%" + filter + "%");
            return builder.or(nameFilter, cpfFilter);
        };
    }
}
