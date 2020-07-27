package br.com.massariol.distribution.controllers.students.mapping;

import br.com.massariol.distribution.controllers.instructors.viewmodels.InstructorDetailViewModel;
import br.com.massariol.distribution.controllers.students.viewmodels.StudentDetailViewModel;
import br.com.massariol.distribution.controllers.students.viewmodels.StudentResumeViewModel;
import br.com.massariol.domain.features.instructors.Instructor;
import br.com.massariol.domain.features.students.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class StudentViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Student.class, StudentResumeViewModel.class);
        modelMapper.addMappings(studentStudentResumeViewModelPropertyMap());

        modelMapper.createTypeMap(Student.class, StudentDetailViewModel.class);
        modelMapper.addMappings(studentStudentDetailViewModelPropertyMap());
    }

    static PropertyMap<Student, StudentResumeViewModel> studentStudentResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCellPhone(source.getPerson().getCellPhone());
                map().setCpf(source.getPerson().getCpf());
                map().setEmail(source.getPerson().getEmail());
                map().setName(source.getPerson().getName());
            }
        };
    }

    static PropertyMap<Student, StudentDetailViewModel> studentStudentDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCellPhone(source.getPerson().getCellPhone());
                map().setCpf(source.getPerson().getCpf());
                map().setEmail(source.getPerson().getEmail());
                map().setName(source.getPerson().getName());
            }
        };
    }
}
