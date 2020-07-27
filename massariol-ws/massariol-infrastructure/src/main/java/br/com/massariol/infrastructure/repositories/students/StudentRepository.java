package br.com.massariol.infrastructure.repositories.students;

import br.com.massariol.domain.features.students.Student;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends RepositoryBase<Student, Long> {

    boolean existsByPersonCpf(String cpf);

    Optional<Student> findByPersonCpf(String cpf);
}
