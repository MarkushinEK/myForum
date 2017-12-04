package forum.dao;

import forum.dataSet.User;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;

public class UserDAO extends DAO {

    public UserDAO(Session session) {
        super(session);
    }

    public User getUserByLogin(String login) {
        return (User) session.createCriteria(User.class).
                add(Restrictions.eq("login", login)).
                uniqueResult();
    }

    public User getUserByEmail(String email) {
        return (User) session.createCriteria(User.class).
                add(Restrictions.eq("email", email)).
                uniqueResult();
    }

}
