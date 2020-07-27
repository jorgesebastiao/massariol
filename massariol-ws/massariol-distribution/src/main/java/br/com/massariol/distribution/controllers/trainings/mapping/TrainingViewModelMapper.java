package br.com.massariol.distribution.controllers.trainings.mapping;

import br.com.massariol.distribution.controllers.trainings.viewmodels.*;
import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.courses.Course;
import br.com.massariol.domain.features.instructors.Instructor;
import br.com.massariol.domain.features.supervisors.Supervisor;
import br.com.massariol.domain.features.trainings.Training;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class TrainingViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Training.class, TrainingResumeViewModel.class);
        modelMapper.addMappings(trainingTrainingResumeViewModelPropertyMap());

        modelMapper.createTypeMap(Training.class, TrainingWithCertificateViewModel.class);
        modelMapper.addMappings(trainingTrainingWithCertificateViewModelPropertyMap());

        modelMapper.createTypeMap(Training.class, TrainingDetailViewModel.class);
        modelMapper.addMappings(trainingTrainingDetailViewModelPropertyMap());

        modelMapper.createTypeMap(Course.class, TrainingCourseDetailViewModel.class);
        modelMapper.addMappings(courseTrainingCourseDetailViewModelPropertyMap());

        modelMapper.createTypeMap(Instructor.class, TrainingInstructorDetailViewModel.class);
        modelMapper.addMappings(instructorTrainingInstructorDetailViewModelPropertyMap());

        modelMapper.createTypeMap(Supervisor.class, TrainingSupervisorDetailViewModel.class);
        modelMapper.addMappings(supervisorTrainingSupervisorDetailViewModelPropertyMap());

        modelMapper.createTypeMap(BusinessStudent.class, TrainingStudentDetailViewModel.class);
        modelMapper.addMappings(businessStudentTrainingStudentDetailViewModelPropertyMap());

        modelMapper.createTypeMap(BusinessStudent.class, TrainingCompanyDetailViewModel.class);
        modelMapper.addMappings(businessStudentTrainingCompanyDetailViewModelPropertyMap());
    }

    static PropertyMap<Training, TrainingResumeViewModel> trainingTrainingResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCompany(source.getBusinessStudent().getCompany().getCorporateName());
                map().setStudent(source.getBusinessStudent().getStudent().getPerson().getName());
                map().setCourse(source.getCourse().getName());
            }
        };
    }

    static PropertyMap<Training, TrainingDetailViewModel> trainingTrainingDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setStudentId(source.getBusinessStudent().getStudent().getId());
                map().setCompanyId(source.getBusinessStudent().getCompany().getId());
                map().setInstructorId(source.getInstructor().getId());
                map(source.getInstructor(), destination.getInstructor());
                map().setSupervisorId(source.getSupervisor().getId());
                map(source.getSupervisor(), destination.getSupervisor());
                map().setCourseId(source.getCourse().getId());
                map(source.getBusinessStudent().getCompany(), destination.getCompany());
                map(source.getBusinessStudent().getStudent(), destination.getStudent());
            }
        };
    }

    static PropertyMap<Course, TrainingCourseDetailViewModel> courseTrainingCourseDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {

            }
        };
    }

    static PropertyMap<Instructor, TrainingInstructorDetailViewModel> instructorTrainingInstructorDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setName(source.getPerson().getName());
                map().setCpf(source.getPerson().getCpf());
            }
        };
    }

    static PropertyMap<Supervisor, TrainingSupervisorDetailViewModel> supervisorTrainingSupervisorDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setName(source.getPerson().getName());
                map().setCpf(source.getPerson().getCpf());
            }
        };
    }

    static PropertyMap<BusinessStudent, TrainingStudentDetailViewModel> businessStudentTrainingStudentDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setId(source.getStudent().getId());
                map().setName(source.getStudent().getPerson().getName());
                map().setCpf(source.getStudent().getPerson().getCpf());
            }
        };
    }

    static PropertyMap<BusinessStudent, TrainingCompanyDetailViewModel> businessStudentTrainingCompanyDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setId(source.getCompany().getId());
                map().setCorporateName(source.getCompany().getCorporateName());
                map().setCnpj(source.getCompany().getCnpj());
            }
        };
    }

    static PropertyMap<Training, TrainingWithCertificateViewModel> trainingTrainingWithCertificateViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setBusinessStudentId(source.getBusinessStudent().getId());
                map().setCourseName(source.getCourse().getName());
            }
        };
    }
}
