package br.com.massariol.application.companies.mapping;

import br.com.massariol.application.companies.commands.CompanyCreateCommand;
import br.com.massariol.application.companies.commands.CompanyUpdateCommand;
import br.com.massariol.domain.features.companies.Company;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class CompanyCommandMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(CompanyCreateCommand.class, Company.class);
        modelMapper.addMappings(companyCreateCommandCompanyPropertyMap());
        modelMapper.createTypeMap(CompanyUpdateCommand.class, Company.class);
        modelMapper.addMappings(companyUpdateCommandCompanyPropertyMap());

    }

    private static PropertyMap<CompanyCreateCommand, Company> companyCreateCommandCompanyPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip(destination.getId());
                skip(destination.getCreationDate());
                skip(destination.getLastModification());
                skip(destination.getUser());
                skip(destination.getBusinessstudents());
            }
        };
    }

    private static PropertyMap<CompanyUpdateCommand, Company> companyUpdateCommandCompanyPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip(destination.getCnpj());
                skip(destination.getCreationDate());
                skip(destination.getLastModification());
                skip(destination.getUser());
                skip(destination.getBusinessstudents());
            }
        };
    }
}
