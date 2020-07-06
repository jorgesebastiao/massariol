package br.com.massariol.distribution.controllers.signatures.mapping;

import br.com.massariol.distribution.controllers.signatures.viewmodels.SignatureViewModel;
import br.com.massariol.domain.features.instructors.Instructor;
import br.com.massariol.domain.features.signatures.SignatureType;
import br.com.massariol.domain.features.students.Student;
import br.com.massariol.domain.features.supervisors.Supervisor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class SignatureViewModelMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Instructor.class, SignatureViewModel.class);
        modelMapper.addMappings(instructorSignatureViewModelPropertyMap());

        modelMapper.createTypeMap(Supervisor.class, SignatureViewModel.class);
        modelMapper.addMappings(supervisorSignatureViewModelPropertyMap());

        modelMapper.createTypeMap(Student.class, SignatureViewModel.class);
        modelMapper.addMappings(studentSignatureViewModelPropertyMap());
    }

    static PropertyMap<Instructor, SignatureViewModel> instructorSignatureViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setEntityId(source.getId());
                map().setType(SignatureType.INSTRUCTOR);
            }
        };
    }

    static PropertyMap<Supervisor, SignatureViewModel> supervisorSignatureViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setEntityId(source.getId());
                map().setType(SignatureType.SUPERVISOR);
            }
        };
    }

    static PropertyMap<Student, SignatureViewModel> studentSignatureViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setEntityId(source.getId());
                map().setType(SignatureType.STUDENT);
            }
        };
    }

}
