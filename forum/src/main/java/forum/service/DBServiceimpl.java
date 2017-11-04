package forum.service;

import forum.config.DataConfig;
import forum.dao.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DBServiceimpl implements DBService {

    private static DBService dbService;
    private SessionFactory sessionfactory;

    private DBServiceimpl() {
        sessionfactory = new DataConfig().getSessionFactory();
    }

    public static DBServiceimpl instance() {
        if (dbService == null)
            dbService = new DBServiceimpl();
        return (DBServiceimpl) dbService;
    }

    public void save(Object data) {
        Session session = sessionfactory.openSession();
        Transaction trx = session.beginTransaction();
        DAO dao = new DAO(session);
        dao.save(data);
        trx.commit();
        session.close();
    }

}
