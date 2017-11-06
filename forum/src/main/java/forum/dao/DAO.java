package forum.dao;

import forum.dataSet.User;
import org.hibernate.Session;

public class DAO {

    Session session;

    public DAO(Session session) {
        this.session = session;
    }

    public void save(Object data) {
        session.save(data);
    }

}
