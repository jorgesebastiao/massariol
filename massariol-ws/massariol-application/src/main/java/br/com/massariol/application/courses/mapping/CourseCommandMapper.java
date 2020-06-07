package br.com.massariol.application.courses.mapping;

import br.com.massariol.application.courses.commands.CourseCreateCommand;
import br.com.massariol.application.courses.commands.CourseUpdateCommand;
import br.com.massariol.domain.features.courses.Course;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class CourseCommandMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(CourseCreateCommand.class, Course.class);
        modelMapper.addMappings(courseCreateCommandCoursePropertyMap());

        modelMapper.createTypeMap(CourseUpdateCommand.class, Course.class);
        modelMapper.addMappings(courseUpdateCommandCoursePropertyMap());
    }

     static PropertyMap<CourseCreateCommand, Course> courseCreateCommandCoursePropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getId());
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip(destination.getTrainings());
            }
        };
    }

     static PropertyMap<CourseUpdateCommand, Course> courseUpdateCommandCoursePropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getId());
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip(destination.getTrainings());
            }
        };
    }
}
