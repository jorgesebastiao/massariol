package br.com.massariol.application.users;

import br.com.massariol.application.base.ApplicationServiceBase;
import br.com.massariol.application.users.commands.CompanyUserCreateCommand;
import br.com.massariol.application.users.commands.CompanyUserUpdateCommand;
import br.com.massariol.application.users.commands.UserCreateCommand;
import br.com.massariol.application.users.commands.UserUpdateCommand;
import br.com.massariol.domain.features.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAppService extends ApplicationServiceBase<User, Long> {

    Page<User> findAll(Pageable pageable, String filter);

    void add(UserCreateCommand command);

    void update(UserUpdateCommand command);
}
