package br.com.massariol.application.businessstudents;

import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.domain.features.students.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessStudentAppService {
    Page<BusinessStudent> findAll(Long companyId, Pageable pageable, String filter);
    BusinessStudent manager(Student student, Company company);
}
