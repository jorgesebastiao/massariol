package br.com.massariol.application.supervisors;

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

@Service
public class SupervisorAppServiceImpl implements SupervisorAppService {
    private  final ModelMapper modelMapper;
    private  final SupervisorRepository supervisorRepository;
    public SupervisorAppServiceImpl(ModelMapper modelMapper, SupervisorRepository supervisorRepository) {
        this.modelMapper = modelMapper;
        this.supervisorRepository = supervisorRepository;
    }

    public Page<Supervisor> findAll(Pageable pageable, String filter) {
        return supervisorRepository.findAll(SupervisorSpecification.filter(filter), pageable);
    }

    public Supervisor getById(Long id) {
        return supervisorRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Supervisor findByCpf(String cpf) {
        return supervisorRepository.findByCpf(cpf)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Long add(SupervisorCreateCommand supervisorCreateCommand) {
        var supervisor = modelMapper.map(supervisorCreateCommand, Supervisor.class);

        if(supervisorRepository.existsByCpf(supervisor.getCpf()))
            throw  new ExceptionCpfInUse();

        supervisorRepository.save(supervisor);
        return  supervisor.getId();
    }

    public void update(SupervisorUpdateCommand supervisorUpdateCommand) {
        var supervisorDatabase = supervisorRepository.findById(supervisorUpdateCommand.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        modelMapper.map(supervisorUpdateCommand,supervisorDatabase);

        supervisorRepository.save(supervisorDatabase);
    }

    public void signature(Long supervisorId, String signature){
        var supervisorDatabase = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        supervisorDatabase.setSignaturePicture(signature.split(",")[1].getBytes());
        supervisorRepository.save(supervisorDatabase);
    }
}
