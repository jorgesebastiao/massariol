package br.com.massariol.application.users.mapping;

import br.com.massariol.application.users.commands.CompanyUserCreateCommand;
import br.com.massariol.application.users.commands.CompanyUserUpdateCommand;
import br.com.massariol.domain.features.users.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserCommandMapper {
    public static void profile(ModelMapper modelMapper) {
    /*    modelMapper.createTypeMap(CompanyUserCreateCommand.class, User.class);
        modelMapper.addMappings(companyUserCommandUserPropertyMap());

        modelMapper.createTypeMap(CompanyUserUpdateCommand.class, User.class);
        modelMapper.addMappings(companyUserUpdateCommandUserPropertyMap());*/
    }

    static PropertyMap<CompanyUserCreateCommand, User> companyUserCommandUserPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip(destination.getId());
                skip(destination.getPermissions());
                skip(destination.getCreationDate());
                skip(destination.getLastModification());
                skip(destination.isActive());
                skip(destination.getCompany());
            }
        };
    }

    static PropertyMap<CompanyUserUpdateCommand, User> companyUserUpdateCommandUserPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip(destination.getPermissions());
                skip(destination.getCreationDate());
                skip(destination.getLastModification());
                skip(destination.isActive());
                skip(destination.getCompany());
                skip(destination.getPassword());
            }
        };
    }
}
