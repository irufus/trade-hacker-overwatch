package com.irufus.tradingalertbot.core.dao.interfaces;

import com.irufus.tradingalertbot.core.bo.Watch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by irufus on 11/21/15.
 */
public interface IWatchDAO extends IBaseDAO {

    @Transactional(readOnly = false)
    public void saveWatch(Watch watch);

    @Transactional(readOnly = false)
    public void deleteWatch(Watch watch);

    @Transactional(readOnly = false)
    public List<Watch> retrieveAllWatches();
}
