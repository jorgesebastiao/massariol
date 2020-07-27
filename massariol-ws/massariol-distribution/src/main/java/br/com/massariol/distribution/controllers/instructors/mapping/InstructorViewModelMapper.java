package br.com.massariol.distribution.controllers.instructors.mapping;

import br.com.massariol.distribution.controllers.instructors.viewmodels.InstructorDetailViewModel;
import br.com.massariol.distribution.controllers.instructors.viewmodels.InstructorResumeViewModel;
import br.com.massariol.domain.features.instructors.Instructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class InstructorViewModelMapper  {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Instructor.class, InstructorResumeViewModel.class);
        modelMapper.addMappings(instructorInstructorResumeViewModelPropertyMap());

        modelMapper.createTypeMap(Instructor.class, InstructorDetailViewModel.class);
        modelMapper.addMappings(instructorInstructorDetailViewModelPropertyMap());
    }

     static PropertyMap<Instructor, InstructorResumeViewModel> instructorInstructorResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCellPhone(source.getPerson().getCellPhone());
                map().setCpf(source.getPerson().getCpf());
                map().setEmail(source.getPerson().getEmail());
                map().setName(source.getPerson().getName());
            }
        };
    }

    static PropertyMap<Instructor, InstructorDetailViewModel> instructorInstructorDetailViewModelPropertyMap() {
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
