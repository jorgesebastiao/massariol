package br.com.massariol.application.businessstudents;

import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.domain.features.students.Student;
import br.com.massariol.infrastructure.repositories.businessstudents.BusinessStudentRepository;
import br.com.massariol.infrastructure.repositories.businessstudents.BusinessStudentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusinessStudentAppServiceImpl implements BusinessStudentAppService {
    private final BusinessStudentRepository businessStudentRepository;

    public BusinessStudentAppServiceImpl(BusinessStudentRepository businessStudentRepository) {
        this.businessStudentRepository = businessStudentRepository;
    }

    public Page<BusinessStudent> findAll(Long companyId, Pageable pageable, String filter) {
        return businessStudentRepository.findAll(BusinessStudentSpecification.findAllByCompanyId(companyId, filter), pageable);
    }

    public BusinessStudent manager(Student student, Company company) {
        return businessStudentRepository.findByStudentIdAndCompanyId(student.getId(), company.getId())
                .orElseGet(() -> {
                    return businessStudentRepository.save(new BusinessStudent(student, company));
                });
    }
}
