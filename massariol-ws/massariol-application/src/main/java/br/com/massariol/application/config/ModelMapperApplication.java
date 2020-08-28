package br.com.massariol.application.config;

import br.com.massariol.application.certificates.mappings.CertificateDtoMapper;
import br.com.massariol.application.companies.mapping.CompanyCommandMapper;
import br.com.massariol.application.courses.mapping.CourseCommandMapper;
import br.com.massariol.application.instructors.mapping.InstructorCommandMapper;
import br.com.massariol.application.students.mapping.StudentCommandMapper;
import br.com.massariol.application.supervisors.mapping.SupervisorCommandMapper;
import br.com.massariol.application.trainings.mapping.TrainingCommandMapper;
import br.com.massariol.application.users.mapping.UserCommandMapper;
import org.modelmapper.ModelMapper;

public class ModelMapperApplication {

    public static void register(ModelMapper modelMapper) {
        UserCommandMapper.profile(modelMapper);
        CompanyCommandMapper.profile(modelMapper);
        InstructorCommandMapper.profile(modelMapper);
        SupervisorCommandMapper.profile(modelMapper);
        CourseCommandMapper.profile(modelMapper);
        StudentCommandMapper.profile(modelMapper);
        TrainingCommandMapper.profile(modelMapper);
        CertificateDtoMapper.profile(modelMapper);
    }
}
