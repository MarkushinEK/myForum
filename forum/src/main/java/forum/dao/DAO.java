package forum.dao;

import org.hibernate.Session;

public class DAO {

    Session session;

    public DAO(Session session) {
        this.session = session;
    }

    public void save(Object data) {
        session.save(data);
    }

    public Object getObjectById(String className, long id) {
        return session.get(className, id);
    }

    public void update(String entity, Object object) {
        session.update(entity, object);
    }

}
