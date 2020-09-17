package br.com.massariol.mail.features.users;

public interface UserEmailService {
    void sendNewPassword(String newPassword, String email, String name);
}
