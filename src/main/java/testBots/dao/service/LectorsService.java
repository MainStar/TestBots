package testBots.dao.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import testBots.dao.entities.LectorsEntity;

import java.util.List;

public class LectorsService {

    private Session session;

    public void setSession(Session sesion){
        this.session = sesion;
    }

    public List<LectorsEntity> getAllLectors(){
        Criteria criteria = session.createCriteria(LectorsEntity.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

}
