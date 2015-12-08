package com.irufus.tradingalertbot.core.services;

import com.irufus.tradingalertbot.core.bo.Watch;
import com.irufus.tradingalertbot.core.dao.interfaces.IWatchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by irufus on 11/21/15.
 */
@Repository("requestService")
public class RequestService {
    @Autowired
    IWatchDAO watchDAO;

    public void saveWatch(Watch watch){
        watchDAO.saveWatch(watch);
    }
    public List<Watch> retrieveAllWatches(){
        return watchDAO.retrieveAllWatches();
    }
    public void removeWatch(Watch watch){
        watchDAO.deleteWatch(watch);
    }
}
