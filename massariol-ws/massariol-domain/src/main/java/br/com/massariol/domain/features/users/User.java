package br.com.massariol.domain.features.users;

import br.com.massariol.domain.common.EntityBase;
import br.com.massariol.domain.features.companies.Company;
import br.com.massariol.domain.features.permissions.Permission;
import br.com.massariol.domain.features.permissions.PermissionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
public class User extends EntityBase<Long> {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private UserType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Company company;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "userPermissions", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId"))
    private Set<Permission> permissions = new HashSet<>();

    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }

    public PermissionType getPermissionType(){
        return  permissions.stream().findFirst().get().getPermission();
    }
}
