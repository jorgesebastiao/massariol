package br.com.massariol.infrastructure.repositories.companies;

import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends RepositoryBase<Company, Long> {

    boolean existsByCnpj(String cnpj);

}
