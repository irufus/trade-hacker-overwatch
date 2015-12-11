package com.irufus.tradingalertbot.monitoring;

import com.irufus.tradingalertbot.communications.MessageHandler;
import com.irufus.tradingalertbot.core.bo.Watch;
import com.irufus.tradingalertbot.core.services.RequestService;
import com.irufus.tradingalertbot.data.utility.FetchQuote;
import com.irufus.tradingalertbot.data.wrapper.GoogleQuote;
import com.mysql.jdbc.log.LogFactory;
import twitter4j.Logger;
import twitter4j.Twitter;

import java.math.BigDecimal;
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
    private Twitter twitter;
    private int delay;

    private Map<Integer, Watch> watches;

    public AlertsWatcher(Twitter twitter, int delay){
        this.twitter = twitter;
        this.delay = delay;
        isRunning = true;
    }
    public synchronized void loadWatches(){
        List<Watch> listOfWatches = requestService.retrieveAllWatches();
        if(listOfWatches != null){
            log.info("Total of " + listOfWatches.size() + " watches");
            for(Watch watch : listOfWatches){
                watches.put(watch.getWatchID(), watch);
            }
        }
    }
    public synchronized void stop(){
        isRunning = false;
    }

    public void run() {
        while(isRunning) {
            loadWatches();
            Map<String, GoogleQuote> quotes = new HashMap<String, GoogleQuote>();
            for (Map.Entry<Integer, Watch> entry : watches.entrySet()) {
                Watch watch = entry.getValue();
                //check to see if the high or low has been met
                String symbol = watch.getSymbol();
                GoogleQuote quote = quotes.get(symbol);
                if (quote == null) {
                    quote = FetchQuote.getGoogleQuote(symbol);
                    if (quote != null) {
                        quotes.put(symbol, quote);
                    } else {
                        log.error("Unable to retrieve quote for " + watch.getSymbol());
                    }
                }
                //send out message accordingly
                if (watch.getAlertHI() != null) {
                    BigDecimal highValue = watch.getAlertHI();
                    BigDecimal currentValue = quote.getCurrentPrice();
                    if (highValue.compareTo(currentValue) > 0) {
                        String message = watch.getSymbol() + " is above " + watch.getSymbol();
                        MessageHandler.updateStatusWithReply(twitter, message, watch.getRequestor());
                    }
                } else if (watch.getAlertLOW() != null) {
                    BigDecimal lowValue = watch.getAlertLOW();
                    BigDecimal currentValue = quote.getCurrentPrice();
                    if (lowValue.compareTo(currentValue) > 0) {
                        String message = watch.getSymbol() + " is below " + watch.getSymbol();
                        MessageHandler.updateStatusWithReply(twitter, message, watch.getRequestor());
                    }
                }

                //check to see if it is expired
                if (watch.getExpiration() != null) {
                    Date now = new Date();
                    if (watch.getExpiration().compareTo(now) < 0) {
                        watches.remove(watch);
                        requestService.removeWatch(watch);
                    }
                }
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                log.warn("AlertsWatcher interrupted from sleep");
            }
        }
    }
}
