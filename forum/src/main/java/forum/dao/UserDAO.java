package forum.dao;

import forum.dataSet.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends DAO {

    public UserDAO(Session session) {
        super(session);
    }

    public User getUserByLogin(String login) {
        return (User) session.createCriteria(User.class).
                add(Restrictions.eq("login", login)).
                uniqueResult();
    }

}
