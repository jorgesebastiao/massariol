package br.com.massariol.mail.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailMessage {

    private String sender;
    private List<String> recipients;
    private String subjectMatter;
    private String body;

    public EmailMessage() {

    }

    public EmailMessage(String sender, List<String> recipients, String subjectMatter) {
        this.sender = sender;
        this.recipients = recipients;
        this.subjectMatter = subjectMatter;
    }
}
