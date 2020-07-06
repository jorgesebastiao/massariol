package br.com.massariol.domain.features.courses;

import br.com.massariol.domain.common.EntityBaseImpl;
import br.com.massariol.domain.features.trainings.Training;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "courses")
public class Course extends EntityBaseImpl<Long> {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String guideline;
    @Column(nullable = false, length = 5000)
    private String courseIdentification;
    @Column(nullable = false)
    private int workload;
    @Column(nullable = false)
    private int validityInYears;
    @OneToMany(mappedBy = "course")
    private List<Training> trainings;
}
