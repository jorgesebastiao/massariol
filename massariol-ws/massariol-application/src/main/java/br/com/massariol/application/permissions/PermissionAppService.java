package br.com.massariol.application.permissions;

import br.com.massariol.domain.features.permissions.Permission;
import br.com.massariol.domain.features.permissions.PermissionType;

public interface PermissionAppService {
    Permission getByPermission(PermissionType permission);
}
