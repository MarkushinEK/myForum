package forum.service;

import forum.dataSet.User;

import java.util.List;

public interface DBService {

    void save(Object data);

    User getUserByLogin(String login);

    public User getUserByEmail(String email);

    Object getObjectById(String className, long id);

    public List getListTreadByTag(String tag);

    public void update(String entity, Object object);
}
