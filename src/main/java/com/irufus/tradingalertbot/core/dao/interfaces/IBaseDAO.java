package com.irufus.tradingalertbot.core.dao.interfaces;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by irufus on 11/21/15.
 */
public interface IBaseDAO {
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory);
}
