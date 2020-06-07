package br.com.massariol.infrastructure.repositories.businessstudents;

import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;

import java.util.Optional;

public interface BusinessStudentRepository extends RepositoryBase<BusinessStudent, Long> {
    Optional<BusinessStudent> findByStudentIdAndCompanyId(Long studentId, Long companyId);
}
