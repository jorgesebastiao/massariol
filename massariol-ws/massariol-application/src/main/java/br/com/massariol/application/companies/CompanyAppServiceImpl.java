package br.com.massariol.application.companies;

import br.com.massariol.application.companies.commands.CompanyCreateCommand;
import br.com.massariol.application.companies.commands.CompanyUpdateCommand;
import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.domain.features.exceptions.ExceptionCnpjInUse;
import br.com.massariol.infrastructure.repositories.companies.CompanyRepository;
import br.com.massariol.infrastructure.repositories.companies.CompanySpecification;
import br.com.massariol.infrastructure.repositories.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyAppServiceImpl implements CompanyAppService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CompanyAppServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public Page<Company> findAll(Pageable pageable, String filter) {
        return companyRepository.findAll(CompanySpecification.filter(filter), pageable);
    }

    public Company getById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Long add(CompanyCreateCommand companyCreateCommand) {
        var company = modelMapper.map(companyCreateCommand, Company.class);
        if (cnpjInUse(company.getCnpj()))
            throw new ExceptionCnpjInUse();
        companyRepository.save(company);
        return company.getId();
    }

    public void update(CompanyUpdateCommand companyUpdateCommand) {
        var companyDatabase = companyRepository.findById(companyUpdateCommand.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(companyUpdateCommand, companyDatabase);

        companyRepository.save(companyDatabase);
    }

    public boolean cnpjInUse(String cnpj) {
        return companyRepository.existsByCnpj(cnpj);
    }
}
