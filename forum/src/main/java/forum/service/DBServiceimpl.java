package forum.service;

import forum.config.DataConfig;
import forum.dao.DAO;
import forum.dao.TreadDAO;
import forum.dao.UserDAO;
import forum.dataSet.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DBServiceImpl implements DBService {

    private static DBService dbService;
    private SessionFactory sessionfactory;

    private DBServiceImpl() {
        sessionfactory = new DataConfig().getSessionFactory();
    }

    public static DBServiceImpl instance() {
        if (dbService == null)
            dbService = new DBServiceImpl();
        return (DBServiceImpl) dbService;
    }

    @Override
    public void save(Object data) {
        Session session = sessionfactory.openSession();
        Transaction trx = session.beginTransaction();
        DAO dao = new DAO(session);
        dao.save(data);
        trx.commit();
        session.close();
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionfactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        User user = userDAO.getUserByLogin(login);
        if (user != null) {
            Hibernate.initialize(user.getThreads());
            Hibernate.initialize(user.getComments());
        }
        session.close();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = sessionfactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            Hibernate.initialize(user.getThreads());
            Hibernate.initialize(user.getComments());
        }
        session.close();
        return user;
    }

    @Override
    public Object getObjectById(String className, long id) {
        Session session = sessionfactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        Object object = userDAO.getObjectById(className, id);
        session.close();
        return object;
    }

    @Override
    public List getListTreadByTag(String tag) {
        Session session = sessionfactory.openSession();
        TreadDAO treadDAO = new TreadDAO(session);
        List treads = treadDAO.getListTreadByTag(tag);
        session.close();
        return treads;
    }

    @Override
    public void update(String entity, Object object) {
        Session session = sessionfactory.openSession();
        Transaction trx = session.beginTransaction();
        DAO DAO = new DAO(session);
        DAO.update(entity, object);
        trx.commit();
        session.close();
    }

    @Override
    public void delete(Object object) {
        Session session = sessionfactory.openSession();
        Transaction trx = session.beginTransaction();
        DAO DAO = new DAO(session);
        DAO.delete(object);
        trx.commit();
        session.close();
    }

    @Override
    public List getListTread() {
        Session session = sessionfactory.openSession();
        TreadDAO treadDAO = new TreadDAO(session);
        List treads = treadDAO.getListTread();
        session.close();
        return treads;
    }

}
