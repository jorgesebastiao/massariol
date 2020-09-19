package br.com.massariol.distribution.controllers.users.mapping;

import br.com.massariol.distribution.controllers.users.viewmodels.UserCompanyViewModel;
import br.com.massariol.distribution.controllers.users.viewmodels.UserDetailViewModel;
import br.com.massariol.distribution.controllers.users.viewmodels.UserResumeViewModel;
import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.domain.features.permissions.PermissionType;
import br.com.massariol.domain.features.users.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Company.class, UserCompanyViewModel.class);
        modelMapper.addMappings(companyUserCompanyViewModelPropertyMap());

        modelMapper.createTypeMap(User.class, UserResumeViewModel.class);
        modelMapper.addMappings(userUserResumeViewModelPropertyMap());

        modelMapper.createTypeMap(User.class, UserDetailViewModel.class);
        modelMapper.addMappings(userUserDetailViewModelPropertyMap());
    }

    private static PropertyMap<Company, UserCompanyViewModel> companyUserCompanyViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {

            }
        };
    }

    private static PropertyMap<User, UserResumeViewModel> userUserResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {

            }
        };
    }

    private static PropertyMap<User, UserDetailViewModel> userUserDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCompanyId(source.getCompany().getId());
                map(source.getCompany(), destination.getCompany());
                map().setProfile(source.getPermissionType());
            }
        };
    }
}
