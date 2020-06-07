package br.com.massariol.domain.features.trainings;

import br.com.massariol.domain.common.EntityBaseImpl;
import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.courses.Course;
import br.com.massariol.domain.features.instructors.Instructor;
import br.com.massariol.domain.features.supervisors.Supervisor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "trainings")
public class Training extends EntityBaseImpl<Long> {
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate expirationDate;
    private LocalDate realizationDate;
    @ManyToOne
    @JoinColumn(name = "businessStudentId", referencedColumnName = "id")
    private BusinessStudent businessStudent;
    @ManyToOne
    @JoinColumn(name = "instructorId", referencedColumnName = "id")
    private Instructor instructor;
    @ManyToOne
    @JoinColumn(name = "supervisorId", referencedColumnName = "id")
    private Supervisor supervisor;
    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private Course course;
}
