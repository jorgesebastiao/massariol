package br.com.massariol.distribution.controllers.courses.mapping;

import br.com.massariol.distribution.controllers.courses.viewmodels.CourseDetailViewModel;
import br.com.massariol.distribution.controllers.courses.viewmodels.CourseResumeViewModel;
import br.com.massariol.domain.features.courses.Course;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class CourseViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Course.class, CourseResumeViewModel.class);
        modelMapper.addMappings(courseCourseResumeViewModelPropertyMap());

        modelMapper.createTypeMap(Course.class, CourseDetailViewModel.class);
        modelMapper.addMappings(courseCourseDetailViewModelPropertyMap());
    }

    static PropertyMap<Course, CourseResumeViewModel> courseCourseResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {

            }
        };
    }

    static PropertyMap<Course, CourseDetailViewModel> courseCourseDetailViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {

            }
        };
    }
}
