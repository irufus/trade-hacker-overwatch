package com.irufus.tradingalertbot.core.dao.implementations;

import com.irufus.tradingalertbot.core.bo.Watch;
import com.irufus.tradingalertbot.core.dao.interfaces.IWatchDAO;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import twitter4j.Logger;

import java.util.List;

/**
 * Created by irufus on 11/21/15.
 */
@Repository("watchDAO")
@Transactional
public class WatchDAO implements IWatchDAO {
    private HibernateTemplate hibernateTemplate;
    private static Logger log = Logger.getLogger(WatchDAO.class);

    public void saveWatch(Watch watch) {
        try {
            hibernateTemplate.saveOrUpdate(watch);
            log.debug("Save successful");
        } catch(DataAccessException dae){
            log.error("Unable to save watch", dae);
        }

    }
    public void saveWatches(List<Watch> watches){
        StatelessSession sSession = hibernateTemplate.getSessionFactory().openStatelessSession();
        Transaction transaction = sSession.beginTransaction();
        for(Watch watch : watches){
            sSession.update(watch);
        }
        transaction.commit();
        sSession.close();
        log.debug("Saved " + watches.size() + " watches");
    }

    public void deleteWatch(Watch watch) {
        log.debug("Removing Watch#" + watch.getWatchID());
        hibernateTemplate.delete(watch);
        log.debug("Remove successful");
    }

    public List<Watch> retrieveAllWatches() {
        log.debug("Retrieving all watches");
        List<Watch> watches = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Watch.class).list();
        log.debug("Retrieval successful");
        return watches;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
        hibernateTemplate.setCacheQueries(true);
    }
}
