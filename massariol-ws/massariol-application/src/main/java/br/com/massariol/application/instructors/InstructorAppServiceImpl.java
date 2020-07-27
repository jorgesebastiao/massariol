package br.com.massariol.application.instructors;

import br.com.massariol.application.instructors.commands.InstructorCreateCommand;
import br.com.massariol.application.instructors.commands.InstructorUpdateCommand;
import br.com.massariol.application.persons.PersonAppService;
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
public class InstructorAppServiceImpl implements InstructorAppService {
    private final ModelMapper modelMapper;
    private  final InstructorRepository instructorRepository;
    private  final PersonAppService personAppService;

    public InstructorAppServiceImpl(ModelMapper modelMapper, InstructorRepository instructorRepository, PersonAppService personAppService) {
        this.modelMapper = modelMapper;
        this.instructorRepository = instructorRepository;
        this.personAppService = personAppService;
    }

    public Page<Instructor> findAll(Pageable pageable, String filter) {
        return instructorRepository.findAll(InstructorSpecification.filter(filter), pageable);
    }

    public Instructor getById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Instructor findByCpf(String cpf) {
        return instructorRepository.findByPersonCpf(cpf)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Long add(InstructorCreateCommand instructorCreateCommand) {
        var instructor = modelMapper.map(instructorCreateCommand, Instructor.class);

        if(instructorRepository.existsByPersonCpf(instructor.getPerson().getCpf()))
            throw  new ExceptionCpfInUse();

        var person =  personAppService.manager(instructor.getPerson());
        instructor.setPerson(person);

        instructorRepository.save(instructor);
        return  instructor.getId();
    }

    public  void update(InstructorUpdateCommand instructorUpdateCommand){
        var instructorDatabase = instructorRepository.findById(instructorUpdateCommand.getId())
                                                     .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(instructorUpdateCommand,instructorDatabase);
        var person =  personAppService.manager(instructorDatabase.getPerson());
        instructorDatabase.setPerson(person);

        instructorRepository.save(instructorDatabase);
    }

    public void signature(Long instructorId, String signature) {
        var instructorDatabase = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        instructorDatabase.getPerson().setSignaturePicture(signature.split(",")[1].getBytes());
        instructorRepository.save(instructorDatabase);
    }

}
