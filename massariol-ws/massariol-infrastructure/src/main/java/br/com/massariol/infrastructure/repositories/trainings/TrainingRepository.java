package br.com.massariol.infrastructure.repositories.trainings;

import br.com.massariol.domain.features.trainings.Training;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends RepositoryBase<Training, Long> {

}
