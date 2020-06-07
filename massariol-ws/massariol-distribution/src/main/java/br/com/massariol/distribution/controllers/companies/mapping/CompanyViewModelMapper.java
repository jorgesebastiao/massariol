package br.com.massariol.distribution.controllers.companies.mapping;

import br.com.massariol.distribution.controllers.companies.viewmodels.CompanyDetailViewModel;
import br.com.massariol.distribution.controllers.companies.viewmodels.CompanyResumeViewModel;
import br.com.massariol.domain.features.companies.Company;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class CompanyViewModelMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Company.class, CompanyDetailViewModel.class);
        modelMapper.addMappings(companyCompanyDetailQueryPropertyMap());

        modelMapper.createTypeMap(Company.class, CompanyResumeViewModel.class);
        modelMapper.addMappings(companyCompanyResumeViewModelPropertyMap());
    }

    static PropertyMap<Company, CompanyDetailViewModel> companyCompanyDetailQueryPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {

            }
        };
    }

    static PropertyMap<Company, CompanyResumeViewModel> companyCompanyResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
              map().setHasUser(source.hasUser());
              map().setActive(source.hasActive());
            }
        };
    }
}
