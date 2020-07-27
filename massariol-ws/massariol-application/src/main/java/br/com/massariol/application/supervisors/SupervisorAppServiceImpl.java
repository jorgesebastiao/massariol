package br.com.massariol.application.supervisors;

import br.com.massariol.application.persons.PersonAppService;
import br.com.massariol.application.supervisors.commands.SupervisorCreateCommand;
import br.com.massariol.application.supervisors.commands.SupervisorUpdateCommand;
import br.com.massariol.domain.features.exceptions.ExceptionCpfInUse;
import br.com.massariol.domain.features.supervisors.Supervisor;
import br.com.massariol.infrastructure.repositories.supervisors.SupervisorRepository;
import br.com.massariol.infrastructure.repositories.supervisors.SupervisorSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SupervisorAppServiceImpl implements SupervisorAppService {
    private  final ModelMapper modelMapper;
    private  final SupervisorRepository supervisorRepository;
    private  final PersonAppService personAppService;

    public SupervisorAppServiceImpl(ModelMapper modelMapper, SupervisorRepository supervisorRepository, PersonAppService personAppService) {
        this.modelMapper = modelMapper;
        this.supervisorRepository = supervisorRepository;
        this.personAppService = personAppService;
    }

    public Page<Supervisor> findAll(Pageable pageable, String filter) {
        return supervisorRepository.findAll(SupervisorSpecification.filter(filter), pageable);
    }

    public Supervisor getById(Long id) {
        return supervisorRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Supervisor findByCpf(String cpf) {
        return supervisorRepository.findByPersonCpf(cpf)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Transactional
    public Long add(SupervisorCreateCommand supervisorCreateCommand) {
        var supervisor = modelMapper.map(supervisorCreateCommand, Supervisor.class);

        if(supervisorRepository.existsByPersonCpf(supervisor.getPerson().getCpf()))
            throw  new ExceptionCpfInUse();

        var person =  personAppService.manager(supervisor.getPerson());
        supervisor.setPerson(person);

        supervisorRepository.save(supervisor);
        return  supervisor.getId();
    }

    @Transactional
    public void update(SupervisorUpdateCommand supervisorUpdateCommand) {
        var supervisorDatabase = supervisorRepository.findById(supervisorUpdateCommand.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(supervisorUpdateCommand,supervisorDatabase);

        var person =  personAppService.manager(supervisorDatabase.getPerson());
        supervisorDatabase.setPerson(person);

        supervisorRepository.save(supervisorDatabase);
    }

    public void signature(Long supervisorId, String signature){
        var supervisorDatabase = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        supervisorDatabase.getPerson().setSignaturePicture(signature.split(",")[1].getBytes());
        supervisorRepository.save(supervisorDatabase);
    }
}
