package forum.service;

import forum.dataSet.Tread;

import java.util.List;
import java.util.Map;

public interface ForumService {

    public boolean emailValidator(String email);

    public boolean loginValidator(String login);

    public boolean passValidator(String pass);

    public boolean subjectValidator(String subject);

    public boolean commentValidator(String comment);

    public void sendMessageOnMail(String mail, String subject, String comment);

    public boolean checkLimitThreads(String tag);

    public Map<String, Integer> getNumOfThreads();

    public void setNumOfThreads(List<Tread> treads);

    public void increaseNumOfThread(String tag);

    public int getMaxNumberOfThreads();

}
