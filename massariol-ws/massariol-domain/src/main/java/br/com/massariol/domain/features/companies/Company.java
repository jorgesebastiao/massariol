package br.com.massariol.domain.features.companies;

import br.com.massariol.domain.common.EntityBase;
import br.com.massariol.domain.features.businessstudents.BusinessStudent;
import br.com.massariol.domain.features.users.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "companies")
public class Company extends EntityBase<Long> {
    private String cnpj;
    private String corporateName;
    private String tradeName;
    private String cellPhone;
    private String email;

    @OneToOne(mappedBy = "company")
    private User user;

    @OneToMany(mappedBy = "company")
    private List<BusinessStudent> businessstudents;

    public boolean hasUser() {
        return user != null;
    }

    public boolean hasActive() {
        return hasUser() && user.isActive();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }
}
