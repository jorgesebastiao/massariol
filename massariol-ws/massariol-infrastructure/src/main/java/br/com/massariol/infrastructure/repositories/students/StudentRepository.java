package br.com.massariol.infrastructure.repositories.students;

import br.com.massariol.domain.features.students.Student;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends RepositoryBase<Student, Long> {

    boolean existsByCpf(String cpf);

}
