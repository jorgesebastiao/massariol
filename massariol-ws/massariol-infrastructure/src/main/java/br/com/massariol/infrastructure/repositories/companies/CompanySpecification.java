package br.com.massariol.infrastructure.repositories.companies;

import br.com.massariol.domain.features.companies.Company;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {
    public static Specification<Company> filter(String filter) {
        return (root, query, builder) -> {
            var corporateNameFilter = builder.like(root.get("corporateName"), "%" + filter + "%");
            var cnpjFilter = builder.like(root.get("cnpj"), "%" + filter + "%");
            return builder.or(corporateNameFilter, cnpjFilter);
        };
    }
}
