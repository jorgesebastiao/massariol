package br.com.massariol.application.trainings.mapping;

import br.com.massariol.application.trainings.commands.TrainingCreateCommand;
import br.com.massariol.application.trainings.commands.TrainingUpdateCommand;
import br.com.massariol.domain.features.trainings.Training;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class TrainingCommandMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(TrainingCreateCommand.class, Training.class);
        modelMapper.addMappings(trainingCreateCommandTrainingPropertyMap());

        modelMapper.createTypeMap(TrainingUpdateCommand.class, Training.class);
        modelMapper.addMappings(trainingUpdateCommandTrainingPropertyMap());
    }

    static PropertyMap<TrainingCreateCommand, Training> trainingCreateCommandTrainingPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getId());
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getCourse());
                skip( destination.getSupervisor());
                skip( destination.getInstructor());
                skip( destination.getBusinessStudent());
            }
        };
    }

    static PropertyMap<TrainingUpdateCommand, Training> trainingUpdateCommandTrainingPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip( destination.getCreationDate());
                skip( destination.getLastModification());
                skip( destination.getCourse());
                skip( destination.getSupervisor());
                skip( destination.getInstructor());
                skip( destination.getBusinessStudent());
            }
        };
    }
}
