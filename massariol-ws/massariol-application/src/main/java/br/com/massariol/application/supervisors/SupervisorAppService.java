package br.com.massariol.application.supervisors;

import br.com.massariol.application.supervisors.commands.SupervisorCreateCommand;
import br.com.massariol.application.supervisors.commands.SupervisorUpdateCommand;
import br.com.massariol.domain.features.supervisors.Supervisor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupervisorAppService {
    Page<Supervisor> findAll(Pageable pageable, String filter);

    Supervisor getById(Long id);

    Supervisor findByCpf(String cpf);

    Long add(SupervisorCreateCommand supervisorCreateCommand);

    void update(SupervisorUpdateCommand supervisorUpdateCommand);

    void signature(Long supervisorId, String signature);
}
