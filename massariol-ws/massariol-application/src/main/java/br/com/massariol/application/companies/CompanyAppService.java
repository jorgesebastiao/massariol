package br.com.massariol.application.companies;

import br.com.massariol.application.companies.commands.CompanyCreateCommand;
import br.com.massariol.application.companies.commands.CompanyUpdateCommand;
import br.com.massariol.domain.features.companies.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyAppService  {
    Page<Company> findAll(Pageable pageable, String filter);
    Company getById(Long id);
    Long add(CompanyCreateCommand companyCreateCommand);
    void update(CompanyUpdateCommand companyUpdateCommand);
    boolean cnpjInUse(String cnpj);
}
