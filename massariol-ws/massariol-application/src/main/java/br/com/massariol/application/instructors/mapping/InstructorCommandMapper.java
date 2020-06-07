package br.com.massariol.application.instructors.mapping;

import br.com.massariol.application.instructors.commands.InstructorCreateCommand;
import br.com.massariol.application.instructors.commands.InstructorUpdateCommand;
import br.com.massariol.domain.features.instructors.Instructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class InstructorCommandMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(InstructorCreateCommand.class, Instructor.class);
        modelMapper.addMappings(instructorCreateCommandInstructorDtoPropertyMap());

        modelMapper.createTypeMap(InstructorUpdateCommand.class, Instructor.class);
        modelMapper.addMappings(instructorUpdateCommandInstructorPropertyMap());
    }

     static PropertyMap<InstructorCreateCommand, Instructor> instructorCreateCommandInstructorDtoPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getId());
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getSignaturePicture());
                skip(destination.getTrainings());
            }
        };
    }

     static PropertyMap<InstructorUpdateCommand, Instructor> instructorUpdateCommandInstructorPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getSignaturePicture());
                skip( destination.getCpf());
                skip(destination.getTrainings());
            }
        };
    }
}
