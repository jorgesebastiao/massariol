package br.com.massariol.domain.features.users;

import br.com.massariol.domain.features.permissions.Permission;

public interface UserDomainService {
    User createUser(User user, Permission permission);
}
