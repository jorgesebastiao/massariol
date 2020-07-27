package br.com.massariol.application.supervisors.mapping;

import br.com.massariol.application.supervisors.commands.SupervisorCreateCommand;
import br.com.massariol.application.supervisors.commands.SupervisorUpdateCommand;
import br.com.massariol.domain.features.supervisors.Supervisor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class SupervisorCommandMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(SupervisorCreateCommand.class, Supervisor.class);
        modelMapper.addMappings(supervisorCreateCommandSupervisorPropertyMap());

        modelMapper.createTypeMap(SupervisorUpdateCommand.class, Supervisor.class);
        modelMapper.addMappings(supervisorUpdateCommandSupervisorPropertyMap());
    }

    static PropertyMap<SupervisorCreateCommand, Supervisor> supervisorCreateCommandSupervisorPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getId());
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getPerson());
                skip(destination.getTrainings());
            }
        };
    }

    static PropertyMap<SupervisorUpdateCommand, Supervisor> supervisorUpdateCommandSupervisorPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getPerson());
                skip(destination.getTrainings());
            }
        };
    }
}
