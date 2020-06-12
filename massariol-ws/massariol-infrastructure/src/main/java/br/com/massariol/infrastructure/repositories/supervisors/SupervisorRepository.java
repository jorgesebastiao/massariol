package br.com.massariol.infrastructure.repositories.supervisors;

import br.com.massariol.domain.features.supervisors.Supervisor;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupervisorRepository  extends RepositoryBase<Supervisor, Long> {

    boolean existsByCpf(String cpf);

    Optional<Supervisor> findByCpf(String cpf);
}
