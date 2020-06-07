package br.com.massariol.distribution.controllers.users.mapping;

import br.com.massariol.distribution.controllers.users.viewmodels.UserCompanyViewModel;
import br.com.massariol.domain.features.users.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(User.class, UserCompanyViewModel.class);
        modelMapper.addMappings(userUserCompanyViewModelPropertyMap());
    }

    private static PropertyMap<User, UserCompanyViewModel> userUserCompanyViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCompanyId(source.getCompany().getId());
            }
        };
    }
}
