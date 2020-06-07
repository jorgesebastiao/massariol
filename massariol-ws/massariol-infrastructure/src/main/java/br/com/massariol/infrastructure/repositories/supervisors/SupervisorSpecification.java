package br.com.massariol.infrastructure.repositories.supervisors;

import br.com.massariol.domain.features.supervisors.Supervisor;
import org.springframework.data.jpa.domain.Specification;

public class SupervisorSpecification {
    public static Specification<Supervisor> filter(String filter) {
        return (root, query, builder) -> {
            var nameFilter = builder.like(root.get("name"), "%" + filter + "%");
            var cpfFilter = builder.like(root.get("cpf"), "%" + filter + "%");
            return builder.or(nameFilter, cpfFilter);
        };
    }
}
