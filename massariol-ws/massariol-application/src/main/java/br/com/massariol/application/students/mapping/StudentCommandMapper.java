package br.com.massariol.application.students.mapping;

import br.com.massariol.application.students.commands.StudentCreateCommand;
import br.com.massariol.application.students.commands.StudentUpdateCommand;
import br.com.massariol.domain.features.students.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class StudentCommandMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(StudentCreateCommand.class, Student.class);
        modelMapper.addMappings(studentCreateCommandStudentPropertyMap());

        modelMapper.createTypeMap(StudentUpdateCommand.class, Student.class);
        modelMapper.addMappings(studentUpdateCommandStudentPropertyMap());
    }

     static PropertyMap<StudentCreateCommand, Student> studentCreateCommandStudentPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getId());
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getSignaturePicture());
                skip( destination.getProfilePicture());
                skip(destination.getBusinessstudents());
            }
        };
    }

    static PropertyMap<StudentUpdateCommand, Student> studentUpdateCommandStudentPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getSignaturePicture());
                skip( destination.getProfilePicture());
                skip( destination.getCpf());
                skip(destination.getBusinessstudents());
            }
        };
    }
}
