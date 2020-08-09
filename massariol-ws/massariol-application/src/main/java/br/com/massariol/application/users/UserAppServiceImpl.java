package br.com.massariol.application.users;

import br.com.massariol.application.base.ApplicationServiceBaseImpl;
import br.com.massariol.application.companies.CompanyAppService;
import br.com.massariol.application.permissions.PermissionAppService;
import br.com.massariol.application.users.commands.CompanyUserCreateCommand;
import br.com.massariol.application.users.commands.CompanyUserUpdateCommand;
import br.com.massariol.domain.features.exceptions.ExceptionEmailInUse;
import br.com.massariol.domain.features.permissions.PermissionType;
import br.com.massariol.domain.features.users.User;
import br.com.massariol.domain.features.users.UserDomainService;
import br.com.massariol.infrastructure.repositories.companies.CompanyRepository;
import br.com.massariol.infrastructure.repositories.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppServiceImpl extends ApplicationServiceBaseImpl<User, Long> implements UserAppService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyAppService companyAppService;
    private final PermissionAppService permissionAppService;
    private final ModelMapper modelMapper;

    public UserAppServiceImpl(UserDomainService userDomainService, UserRepository userRepository, CompanyRepository companyRepository, CompanyAppService companyAppService, PermissionAppService permissionAppService, ModelMapper modelMapper) {
        super(userRepository);
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.companyAppService = companyAppService;
        this.permissionAppService = permissionAppService;
        this.modelMapper = modelMapper;
    }

    public Page<User> findAll(Pageable pageable, String filter) {
        return userRepository.findAll(pageable);
    }

    public User getByCompanyId(Long companyId) {
        return userRepository.findByCompanyId(companyId)
                             .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public void createUserCompany(CompanyUserCreateCommand companyUserCreateCommand) {
        User user = modelMapper.map(companyUserCreateCommand, User.class);

        if (emailInUse(user.getEmail(), user.getId()))
            throw new ExceptionEmailInUse();

        var company = companyAppService.getById(companyUserCreateCommand.getCompanyId());

        user.setCompany(company);

        var permission = permissionAppService.getByPermission(PermissionType.ROLE_COMPANY_CERTIFICATE);
        user = userDomainService.generateUserForCompany(user, permission);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUserCompany(CompanyUserUpdateCommand companyUserUpdateCommand) {
        var userInDataBase = userRepository.findById(companyUserUpdateCommand.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        if (emailInUse(companyUserUpdateCommand.getEmail(), companyUserUpdateCommand.getId()))
            throw new ExceptionEmailInUse();

        modelMapper.map(companyUserUpdateCommand, userInDataBase);

        if(!companyUserUpdateCommand.getPassword().isEmpty())
            userInDataBase.setPassword(passwordEncoder.encode(companyUserUpdateCommand.getPassword()));

        userRepository.save(userInDataBase);
    }

    public boolean emailInUse(String email, Long userId) {
        Optional<User> userOptional = userRepository.findByEmail(email.toLowerCase());
        if (userId != null)
            return userOptional.isPresent() && (!userOptional.get().getId().equals(userId));
        return userOptional.isPresent();
    }
}