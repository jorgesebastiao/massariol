package br.com.massariol.infrastructure.repositories.businessstudents;

import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

public class BusinessStudentSpecification {
    public  static Specification<BusinessStudent> findAllByCompanyId(Long companyId, String filter){
        return (root, query, builder) -> {
            return where(companyIdFilter(companyId))
                    .and(studentfilter(filter))
                    .toPredicate(root, query, builder);
        };
    }

    static Specification<BusinessStudent> studentfilter(String filter) {
        return (root, query, builder) -> {
            var studentJoin = root.join("student");
            var nameFilter = builder.like(studentJoin.get("name"), "%" + filter + "%");
            var cpfFilter = builder.like(studentJoin.get("cpf"), "%" + filter + "%");
            return builder.or(nameFilter, cpfFilter);
        };
    }

    static Specification<BusinessStudent> companyIdFilter(Long companyId) {
        return (root, query, builder) -> {
            var companyJoin = root.join("company");
            return builder.equal(companyJoin.get("id"), companyId);
        };
    }
}
