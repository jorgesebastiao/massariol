package br.com.massariol.domain.features.permissions;

import br.com.massariol.domain.common.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "permissions")
public class Permission extends EntityBase<Long> {
    @Column(nullable = false, unique = true)
    private PermissionType permission;
}
