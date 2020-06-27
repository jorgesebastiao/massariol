package br.com.massariol.application.certificates.mappings;

import br.com.massariol.application.certificates.dtos.CertificateDto;
import br.com.massariol.domain.features.trainings.Training;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class CertificateDtoMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Training.class, CertificateDto.class);
       modelMapper.addMappings(trainingCertificateDtoPropertyMap());
    }

    private static PropertyMap<Training, CertificateDto> trainingCertificateDtoPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCourseGuideline(source.getCourse().getGuideline());
                map().setCourseName(source.getCourse().getName());
                map().setInstructorCpf(source.getInstructor().getCpf());
                map().setInstructorName(source.getInstructor().getName());
                map().setInstructorSignature(source.getInstructor().getSignature());
                map().setFinishDate(source.getFinishDate());

                map().setSupervisorCpf(source.getSupervisor().getCpf());
                map().setSupervisorName(source.getSupervisor().getName());
                map().setSupervisorSignature(source.getSupervisor().getSignature());

                map().setStartDate(source.getStartDate());

                map().setStudentId(source.getBusinessStudent().getStudent().getId());
                map().setStudentCpf(source.getBusinessStudent().getStudent().getCpf());
                map().setStudentName(source.getBusinessStudent().getStudent().getName());
                map().setStudentSignature(source.getBusinessStudent().getStudent().getSignature());

                map().setWorkload(source.getCourse().getWorkload());
            }
        };
    }
}
