package br.com.massariol.infrastructure.repositories.permissions;

import br.com.massariol.domain.features.permissions.Permission;
import br.com.massariol.domain.features.permissions.PermissionType;
import br.com.massariol.infrastructure.repositories.base.RepositoryBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends RepositoryBase<Permission, Long> {

    Optional<Permission> findByPermission(PermissionType  permission);
}
