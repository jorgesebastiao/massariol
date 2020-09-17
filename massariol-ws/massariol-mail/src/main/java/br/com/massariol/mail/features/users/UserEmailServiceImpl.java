package br.com.massariol.mail.features.users;

import br.com.massariol.mail.common.EmailMessage;
import br.com.massariol.mail.common.EmailService;
import br.com.massariol.mail.common.EmailTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserEmailServiceImpl implements UserEmailService {
    private final EmailService emailService;

    public UserEmailServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendNewPassword(String newPassword, String email, String name) {
        var emailMessage = new EmailMessage(EmailTemplate.NO_REPLY_SENDER, Collections.singletonList(email), EmailTemplate.SUBJECT_MATTHER_NEW_PASSWORD);
        Map<String, Object> variables = new HashMap<>();
        variables.put("newPassword", newPassword);
        variables.put("email", email);
        variables.put("name", name);

        emailService.send(emailMessage, variables, EmailTemplate.SEND_NEW_PASSWORD_TEMPLATE);
    }
}
