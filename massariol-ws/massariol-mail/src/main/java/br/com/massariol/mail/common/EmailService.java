package br.com.massariol.mail.common;

import java.util.Map;

public interface EmailService {
    void send(EmailMessage emailMessage, Map<String, Object> variables, String template);
}
