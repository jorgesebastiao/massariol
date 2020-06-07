package br.com.massariol.application.instructors;

import br.com.massariol.application.instructors.commands.InstructorCreateCommand;
import br.com.massariol.application.instructors.commands.InstructorUpdateCommand;
import br.com.massariol.domain.features.exceptions.ExceptionCpfInUse;
import br.com.massariol.domain.features.instructors.Instructor;
import br.com.massariol.infrastructure.repositories.instructors.InstructorRepository;
import br.com.massariol.infrastructure.repositories.instructors.InstructorSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstructorAppAppServiceImpl implements InstructorAppService {
    private final ModelMapper modelMapper;
    private  final InstructorRepository instructorRepository;
    public InstructorAppAppServiceImpl(ModelMapper modelMapper, InstructorRepository instructorRepository) {
        this.modelMapper = modelMapper;
        this.instructorRepository = instructorRepository;
    }

    public Page<Instructor> findAll(Pageable pageable, String filter) {
        return instructorRepository.findAll(InstructorSpecification.filter(filter), pageable);
    }

    public Instructor getById(Long id) {
        return this.instructorRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Long add(InstructorCreateCommand instructorCreateCommand) {
        var instructor = modelMapper.map(instructorCreateCommand, Instructor.class);

        if(instructorRepository.existsByCpf(instructor.getCpf()))
            throw  new ExceptionCpfInUse();

        instructorRepository.save(instructor);
        return  instructor.getId();
    }

    public  void update(InstructorUpdateCommand instructorUpdateCommand){
        var instructorDatabase = instructorRepository.findById(instructorUpdateCommand.getId())
                                                     .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(instructorUpdateCommand,instructorDatabase);

        instructorRepository.save(instructorDatabase);
    }

}
