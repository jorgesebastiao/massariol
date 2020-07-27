package br.com.massariol.distribution.controllers.businessstudents.mapping;

import br.com.massariol.distribution.controllers.businessstudents.viewmodel.BusinessStudentViewModel;
import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class BusinessStudentViewModelMapper {

    public static void profile(ModelMapper modelMapper) {
        modelMapper.createTypeMap(BusinessStudent.class, BusinessStudentViewModel.class);
        modelMapper.addMappings(businessStudentBusinessStudentViewModelPropertyMap());
    }

    static PropertyMap<BusinessStudent, BusinessStudentViewModel> businessStudentBusinessStudentViewModelPropertyMap() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setStudentId(source.getStudent().getId());
                map().setStudentCpf(source.getStudent().getPerson().getCpf());
                map().setStudentName(source.getStudent().getPerson().getName());
                map().setStudentOffice(source.getStudent().getOffice());
            }
        };
    }
}
