package forum.service;

public interface ForumService {

    public boolean emailValidator(String email);

    public boolean loginValidator(String login);

    public boolean passValidator(String pass);

    public boolean subjectValidator(String subject);

    public boolean commentValidator(String comment);

    public void sendMessageOnMail(String mail, String subject, String comment);
}
