package br.com.massariol.application.permissions;

import br.com.massariol.domain.features.permissions.Permission;
import br.com.massariol.domain.features.permissions.PermissionType;
import br.com.massariol.infrastructure.repositories.permissions.PermissionRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PermissionAppServiceImpl implements PermissionAppService {
    private final PermissionRepository permissionRepository;

    public PermissionAppServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission getByPermission(PermissionType permission) {
        return permissionRepository.findByPermission(permission)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
