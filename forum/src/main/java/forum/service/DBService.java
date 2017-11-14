package forum.service;

import forum.dataSet.User;

import java.util.List;

public interface DBService {

    void save(Object data);

    User getUserByLogin(String login);

    Object getObjectById(String className, long id);

    public List getListTreadByTag(String tag);
}
