package forum.dao;

import forum.dataSet.Tread;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TreadDAO extends DAO {

    public TreadDAO(Session session) {
        super(session);
    }

    public List getListTreadByTag(String tag) {
        return  session.createCriteria(Tread.class).
                add(Restrictions.eq("tag", tag)).
                addOrder(Order.asc("dateOfChange")).list();
    }
}
