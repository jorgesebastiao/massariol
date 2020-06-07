package br.com.massariol.application.instructors;

import br.com.massariol.application.instructors.commands.InstructorCreateCommand;
import br.com.massariol.application.instructors.commands.InstructorUpdateCommand;
import br.com.massariol.domain.features.instructors.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstructorAppService {
    Page<Instructor> findAll(Pageable pageable, String filter);

    Instructor getById(Long id);

    Long add(InstructorCreateCommand instructorCreateCommand);

    void update(InstructorUpdateCommand instructorUpdateCommand);
}
