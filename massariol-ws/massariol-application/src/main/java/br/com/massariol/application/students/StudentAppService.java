package br.com.massariol.application.students;

import br.com.massariol.application.students.commands.StudentCreateCommand;
import br.com.massariol.application.students.commands.StudentUpdateCommand;
import br.com.massariol.domain.features.students.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentAppService {
    Page<Student> findAll(Pageable pageable, String filter);

    Student getById(Long id);

    Student findByCpf(String cpf);

    Long add(StudentCreateCommand studentCreateCommand);

    void update(StudentUpdateCommand studentUpdateCommand);

    void signature(Long studentId, String signature);

}
