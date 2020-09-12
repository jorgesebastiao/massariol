package br.com.massariol.distribution.controllers.users.mapping;

import br.com.massariol.distribution.controllers.users.viewmodels.UserCompanyViewModel;
import br.com.massariol.distribution.controllers.users.viewmodels.UserDetailViewModel;
import br.com.massariol.distribution.controllers.users.viewmodels.UserResumeViewModel;
import br.com.massariol.domain.features.permissions.PermissionType;
import br.com.massariol.domain.features.users.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(User.class, UserCompanyViewModel.class);
        modelMapper.addMappings(userUserCompanyViewModelPropertyMap());

        modelMapper.createTypeMap(User.class, UserResumeViewModel.class);
        modelMapper.addMappings(userUserResumeViewModelPropertyMap());

        modelMapper.createTypeMap(User.class, UserDetailViewModel.class);
        modelMapper.addMappings(userUserDetailViewModelPropertyMap());
    }

    private static PropertyMap<User, UserCompanyViewModel> userUserCompanyViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCompanyId(source.getCompany().getId());
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
                map().setProfile(PermissionType.ROLE_ADMIN_MASSARIOL);
            }
        };
    }
}
