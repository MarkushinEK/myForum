package forum.dao;

import forum.dataSet.Tread;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.List;

public class TreadDAO extends DAO {

    public TreadDAO(Session session) {
        super(session);
    }

    public List getListTreadByTag(String tag) {

        return session.createCriteria(Tread.class).
                add(Restrictions.eq("tag", tag)).
                addOrder(Order.desc("dateOfChange")).setFetchMode("comments", FetchMode.SELECT).
                list();
    }


    public List getListTread() {
        return session.createCriteria(Tread.class).
                list();
    }

}
