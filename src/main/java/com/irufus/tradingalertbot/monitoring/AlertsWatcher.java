package com.irufus.tradingalertbot.monitoring;

import com.irufus.tradingalertbot.core.bo.Watch;
import com.irufus.tradingalertbot.core.services.RequestService;
import com.irufus.tradingalertbot.data.wrapper.GoogleQuote;
import com.mysql.jdbc.log.LogFactory;
import twitter4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by irufus on 11/30/15.
 */
public class AlertsWatcher implements Runnable {
    private static Logger log = Logger.getLogger(AlertsWatcher.class);
    private RequestService requestService;
    private boolean isRunning;

    private Map<Integer, Watch> watches;

    public AlertsWatcher(){
        isRunning = false;
        List<Watch> listOfWatches = requestService.retrieveAllWatches();
        if(listOfWatches != null){
            log.info("Total of " + listOfWatches.size() + " watches");
            for(Watch watch : listOfWatches){
                watches.put(watch.getWatchID(), watch);
            }
        }
    }
    public synchronized void start(){
        if(!isRunning){
            run();
        }
        else{
            log.warn("AlertsWatcher is already running");
        }

    }
    public synchronized void stop(){
        isRunning = false;
    }
    public void run() {

        while(isRunning){
            Map<String, GoogleQuote> quotes = new HashMap<String, GoogleQuote>();
            for(Map.Entry<Integer, Watch> entry : watches.entrySet()){
                Watch watch = entry.getValue();
                //check to see if the high or low has been met
                String symbol = watch.getSymbol();
                GoogleQuote quote = quotes.get(symbol);
                if(quote == null)
                {

                }
                //send out message accordingly

                //check to see if it is expired
                if(watch.getExpiration() != null){
                    Date now = new Date();
                    if(watch.getExpiration().compareTo(now) < 0){

                    }
                }


            }

        }
    }
}
