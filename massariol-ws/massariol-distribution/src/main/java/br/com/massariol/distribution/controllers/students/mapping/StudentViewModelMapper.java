package br.com.massariol.distribution.controllers.students.mapping;

import br.com.massariol.distribution.controllers.students.viewmodels.StudentResumeViewModel;
import br.com.massariol.domain.features.students.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class StudentViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Student.class, StudentResumeViewModel.class);
        modelMapper.addMappings(studentStudentResumeViewModelPropertyMap());
    }

    static PropertyMap<Student, StudentResumeViewModel> studentStudentResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {

            }
        };
    }
}
