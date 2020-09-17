package br.com.massariol.mail.common;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void send(EmailMessage emailMessage, Map<String, Object> variables, String template) {

        var context = new Context(new Locale("pt", "BR"));

        variables.forEach((key, value) -> context.setVariable(key, value));

        emailMessage.setBody(templateEngine.process(template, context));
        send(emailMessage);
    }

    private void send(EmailMessage emailMessage) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailMessage.getSender());
            helper.setTo(emailMessage.getRecipients().toArray(new String[emailMessage.getRecipients().size()]));
            helper.setSubject(emailMessage.getSubjectMatter());
            helper.setText(emailMessage.getBody(), true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao processar o envio do e-mail!", e);
        }
    }
}
