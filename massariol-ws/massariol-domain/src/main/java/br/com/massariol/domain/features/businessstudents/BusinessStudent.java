package br.com.massariol.domain.features.businessstudents;

import br.com.massariol.domain.common.EntityBaseImpl;
import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.domain.features.students.Student;
import br.com.massariol.domain.features.trainings.Training;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "businessStudents")
public class BusinessStudent extends EntityBaseImpl<Long> {
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;

    @OneToMany(mappedBy = "businessStudent")
    private List<Training> trainings;

    public  BusinessStudent(){

    }

    public BusinessStudent(Student student, Company company){
        setCreationDate(LocalDateTime.now());
        setLastModification(LocalDateTime.now());
        this.student = student;
        this.company = company;
    }
}
