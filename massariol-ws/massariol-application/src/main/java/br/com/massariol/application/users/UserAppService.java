package br.com.massariol.application.users;

import br.com.massariol.application.base.ApplicationServiceBase;
import br.com.massariol.application.users.commands.CompanyUserCreateCommand;
import br.com.massariol.application.users.commands.CompanyUserUpdateCommand;
import br.com.massariol.domain.features.users.User;

public interface UserAppService extends ApplicationServiceBase<User, Long> {

    User getByCompanyId(Long companyId);

    void createUserCompany(CompanyUserCreateCommand companyUserCreateCommand);

    void updateUserCompany(CompanyUserUpdateCommand companyUserUpdateCommand);
}
