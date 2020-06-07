package br.com.massariol.application.trainings;

import br.com.massariol.application.supervisors.commands.SupervisorCreateCommand;
import br.com.massariol.application.supervisors.commands.SupervisorUpdateCommand;
import br.com.massariol.application.trainings.commands.TrainingCreateCommand;
import br.com.massariol.application.trainings.commands.TrainingUpdateCommand;
import br.com.massariol.domain.features.supervisors.Supervisor;
import br.com.massariol.domain.features.trainings.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrainingAppService {
    Page<Training> findAll(Pageable pageable, String filter);

    Training getById(Long id);

    Page<Training> findAllByBusinessStudentIdAndCompanyId(Long businessStudentId, Long companyId, Pageable pageable, String filter);

    Long add(TrainingCreateCommand trainingCreateCommand);

    void update(TrainingUpdateCommand trainingUpdateCommand);
}
