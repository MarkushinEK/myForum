package forum.service;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForumServiceImpl implements ForumService {

    private static ForumService forumService;

    public static ForumServiceImpl instance() {
        if (forumService == null)
            forumService = new ForumServiceImpl();
        return (ForumServiceImpl) forumService;
    }

    @Override
    public boolean emailValidator(String email) {
        return new EmailValidator().isValid(email,null);
    }

    @Override
    public boolean loginValidator(String login) {
        return login.length() >= 5 && login.length() < 20 && !login.contains(" ");

    }

    @Override
    public boolean passValidator(String pass) {
        return pass.length() >= 5 && pass.length() < 20 && !pass.contains(" ");
    }

    @Override
    public boolean subjectValidator(String subject) {
        return subject.length() <= 50 && !subject.isEmpty();
    }

    @Override
    public boolean commentValidator(String comment) {
        return comment.length() <= 15000 && !comment.isEmpty();
    }

    @Override
    public void sendMessageOnMail(String mail, String subject, String comment) {
        final String username = "forumekpost@gmail.com";
        final String password = "12345qwerty";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("forumekpost@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail));
            message.setSubject(subject);
            message.setText(comment);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
