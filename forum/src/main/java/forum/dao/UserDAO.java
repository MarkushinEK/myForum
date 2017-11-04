package forum.dao;

import org.hibernate.Session;

public class UserDAO extends DAO {

    public UserDAO(Session session) {
        super(session);
    }

}
