package br.com.massariol.infrastructure.repositories.instructors;

import br.com.massariol.domain.features.instructors.Instructor;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository  extends RepositoryBase<Instructor, Long> {

    boolean existsByPersonCpf(String cpf);

    Optional<Instructor> findByPersonCpf(String cpf);
}
