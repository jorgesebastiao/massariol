package br.com.massariol.infrastructure.repositories.trainings;

import br.com.massariol.domain.features.trainings.Training;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

public class TrainingSpecification {

    public static Specification<Training> findAllByCompanyId(Long businessStudentId, Long companyId, String filter) {
        return (root, query, builder) -> {
            return where(findByBusinessStudentIdAndCompanyId(businessStudentId,companyId))
                    .and(courseFilter(filter))
                    .toPredicate(root, query, builder);
        };
    }

    public static Specification<Training> findByIdAndBusinessStudentIdAndCompanyId(Long id, Long businessStudentId,Long companyId) {
        return (root, query, builder) -> {
            return where(findById(id))
                    .and(findByBusinessStudentIdAndCompanyId(businessStudentId, companyId))
                    .toPredicate(root, query, builder);
        };
    }

    public static  Specification<Training> filterByCompanyOrCourseOrStudent(String filter){
        return (root, query, builder) -> {
            return where(courseFilter(filter))
                    .or(studentFilter(filter))
                    .or(companyFilter(filter))
                    .toPredicate(root, query, builder);
        };
    }

    static Specification<Training> studentFilter(String filter){
        return (root, query, builder) -> {
            var businessStudentJoin = root.join("businessStudent");
            var studentJoin = businessStudentJoin.join("student");
            return builder.like(studentJoin.get("name"), "%" + filter + "%");
        };
    }

    static Specification<Training> courseFilter(String filter) {
        return (root, query, builder) -> {
            var courseJoin = root.join("course");
            return builder.like(courseJoin.get("name"), "%" + filter + "%");
        };
    }
    
    static Specification<Training> companyFilter(String filter){
        return (root, query, builder) -> {
            var businessStudentJoin = root.join("businessStudent");
            var companyJoin = businessStudentJoin.join("company");
            return builder.like(companyJoin.get("corporateName"), "%" + filter + "%");
        };
    }

    static Specification<Training> findById(Long trainingId) {
        return (root, query, builder) -> {
            return builder.equal(root.get("id"), trainingId);
        };
    }

    static Specification<Training> findByBusinessStudentIdAndCompanyId(Long businessStudentId, Long companyId) {
        return (root, query, builder) -> {
            var businessStudentJoin = root.join("businessStudent");
            var businessStudentEqual = builder.equal(businessStudentJoin.get("id"), businessStudentId);
            var companyJoin = businessStudentJoin.join("company");
            var companyEqual = builder.equal(companyJoin.get("id"), companyId);
            return builder.and(businessStudentEqual, companyEqual);
        };
    }
}
