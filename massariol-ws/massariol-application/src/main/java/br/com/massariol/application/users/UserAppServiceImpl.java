package br.com.massariol.application.users;

import br.com.massariol.application.base.ApplicationServiceBaseImpl;
import br.com.massariol.application.companies.CompanyAppService;
import br.com.massariol.application.permissions.PermissionAppService;
import br.com.massariol.application.users.commands.UserCreateCommand;
import br.com.massariol.application.users.commands.UserUpdateCommand;
import br.com.massariol.application.users.utils.RandomUtil;
import br.com.massariol.domain.features.exceptions.ExceptionEmailInUse;
import br.com.massariol.domain.features.permissions.PermissionType;
import br.com.massariol.domain.features.users.User;
import br.com.massariol.domain.features.users.UserDomainService;
import br.com.massariol.domain.features.users.UserType;
import br.com.massariol.infrastructure.repositories.companies.CompanyRepository;
import br.com.massariol.infrastructure.repositories.users.UserRepository;
import br.com.massariol.mail.features.users.UserEmailService;
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
    private final UserEmailService userEmailService;

    public UserAppServiceImpl(UserDomainService userDomainService, UserRepository userRepository, CompanyRepository companyRepository, CompanyAppService companyAppService, PermissionAppService permissionAppService, ModelMapper modelMapper, UserEmailService userEmailService) {
        super(userRepository);
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.companyAppService = companyAppService;
        this.permissionAppService = permissionAppService;
        this.modelMapper = modelMapper;
        this.userEmailService = userEmailService;
    }

    public Page<User> findAll(Pageable pageable, String filter) {
        return userRepository.findAll(pageable);
    }

    public void add(UserCreateCommand command) {
        User user = modelMapper.map(command, User.class);

        if (emailInUse(user.getEmail(), user.getId()))
            throw new ExceptionEmailInUse();

        if(command.getCompanyId() != null) {
            var company = companyAppService.getById(command.getCompanyId());
            user.setCompany(company);
            var permission = permissionAppService.getByPermission(PermissionType.ROLE_COMPANY_CERTIFICATE);
            user = userDomainService.createUser(user, permission);
            user.setType(UserType.COMPANY);
        }else{
            user.setType(UserType.MASSARIOL);
            var permission = permissionAppService.getByPermission(command.getProfile());
            user = userDomainService.createUser(user, permission);
        }
        String newPassword = RandomUtil.getNewPassword();
        userEmailService.sendNewPassword(newPassword, user.getEmail(), user.getName());
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void update(UserUpdateCommand command) {
        var userInDataBase = userRepository.findById(command.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        if (emailInUse(command.getEmail(), command.getId()))
            throw new ExceptionEmailInUse();

        modelMapper.map(command, userInDataBase);

        if(command.isResendPassword()){
            String newPassword = RandomUtil.getNewPassword();
            userEmailService.sendNewPassword(newPassword, userInDataBase.getEmail(), userInDataBase.getName());
            userInDataBase.setPassword(passwordEncoder.encode(userInDataBase.getPassword()));
        }
        userRepository.save(userInDataBase);
    }

    public boolean emailInUse(String email, Long userId) {
        Optional<User> userOptional = userRepository.findByEmail(email.toLowerCase());
        if (userId != null)
            return userOptional.isPresent() && (!userOptional.get().getId().equals(userId));
        return userOptional.isPresent();
    }
}