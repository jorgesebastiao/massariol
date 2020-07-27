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
                skip(destination.getId());
                skip(destination.getCreationDate());
                skip(destination.getLastModification());
                skip(destination.getPerson());
                map().getPerson().setCpf(source.getCpf());
                map().getPerson().setName(source.getName());
                map().getPerson().setEmail(source.getEmail());
                map().getPerson().setCellPhone(source.getCellPhone());
                skip(destination.getBusinessStudents());
            }
        };
    }

    static PropertyMap<StudentUpdateCommand, Student> studentUpdateCommandStudentPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip(destination.getCreationDate());
                skip(destination.getLastModification());
                skip(destination.getPerson());
                map().getPerson().setEmail(source.getEmail());
                map().getPerson().setName(source.getName());
                map().getPerson().setCellPhone(source.getCellPhone());
                skip(destination.getBusinessStudents());
            }
        };
    }
}
