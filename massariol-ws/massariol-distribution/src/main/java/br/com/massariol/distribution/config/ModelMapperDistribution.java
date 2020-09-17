package br.com.massariol.distribution.config;

import br.com.massariol.distribution.controllers.businessstudents.mapping.BusinessStudentViewModelMapper;
import br.com.massariol.distribution.controllers.companies.mapping.CompanyViewModelMapper;
import br.com.massariol.distribution.controllers.courses.mapping.CourseViewModelMapper;
import br.com.massariol.distribution.controllers.instructors.mapping.InstructorViewModelMapper;
import br.com.massariol.distribution.controllers.signatures.mapping.SignatureViewModelMapper;
import br.com.massariol.distribution.controllers.students.mapping.StudentViewModelMapper;
import br.com.massariol.distribution.controllers.supervisors.mapping.SupervisorViewModelMapper;
import br.com.massariol.distribution.controllers.trainings.mapping.TrainingViewModelMapper;
import br.com.massariol.distribution.controllers.users.mapping.UserViewModelMapper;
import org.modelmapper.ModelMapper;

class ModelMapperDistribution {
    static void register(ModelMapper modelMapper) {
        CompanyViewModelMapper.profile(modelMapper);
        InstructorViewModelMapper.profile(modelMapper);
        SupervisorViewModelMapper.profile(modelMapper);
        CourseViewModelMapper.profile(modelMapper);
        StudentViewModelMapper.profile(modelMapper);
        TrainingViewModelMapper.profile(modelMapper);
        BusinessStudentViewModelMapper.profile(modelMapper);
        SignatureViewModelMapper.profile(modelMapper);
        UserViewModelMapper.profile(modelMapper);
    }
}
