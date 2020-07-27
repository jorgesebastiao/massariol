package br.com.massariol.distribution.controllers.supervisors.mapping;

import br.com.massariol.distribution.controllers.supervisors.viewmodels.SupervisorDetailViewModel;
import br.com.massariol.distribution.controllers.supervisors.viewmodels.SupervisorResumeViewModel;
import br.com.massariol.domain.features.supervisors.Supervisor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class SupervisorViewModelMapper {
    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Supervisor.class, SupervisorResumeViewModel.class);
        modelMapper.addMappings(supervisorSupervisorResumeViewModelPropertyMap());

        modelMapper.createTypeMap(Supervisor.class, SupervisorDetailViewModel.class);
        modelMapper.addMappings(supervisorSupervisorDetailViewModelPropertyMap());
    }

    static PropertyMap<Supervisor, SupervisorResumeViewModel> supervisorSupervisorResumeViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setCellPhone(source.getPerson().getCellPhone());
                map().setCpf(source.getPerson().getCpf());
                map().setEmail(source.getPerson().getEmail());
                map().setName(source.getPerson().getName());
            }
        };
    }

    static PropertyMap<Supervisor, SupervisorDetailViewModel> supervisorSupervisorDetailViewModelPropertyMap() {
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
