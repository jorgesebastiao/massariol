package br.com.massariol.domain.features.users;

import br.com.massariol.domain.features.permissions.Permission;

public interface UserDomainService {
    User generateUserForCompany(User user, Permission permission);
}
