package br.com.massariol.application.base;

import br.com.massariol.infrastructure.repositories.base.RepositoryCompanyBase;
import br.com.massariol.infrastructure.repositories.companies.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public class ApplicationCompanyServiceBaseImpl<T, ID extends Serializable> extends ApplicationServiceBaseImpl<T, ID> implements ApplicationCompanyServiceBase<T, ID> {

    private final RepositoryCompanyBase<T, ID> repositoryCompanyBase;

    protected ApplicationCompanyServiceBaseImpl(RepositoryCompanyBase<T, ID> repositoryCompanyBase) {
        super(repositoryCompanyBase);
        this.repositoryCompanyBase = repositoryCompanyBase;
    }

    public Page<T> findAllByCompanyId(Pageable pageable, String institutionId) {
        return repositoryCompanyBase.findAllByCompanyId(pageable, institutionId);
    }
}
