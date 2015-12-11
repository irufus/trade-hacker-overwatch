package com.irufus.tradingalertbot.communications;

import twitter4j.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by irufus on 11/19/15.
 */
public class MessageHandler {
    static Logger log = Logger.getLogger(MessageHandler.class);

    public static boolean updateStatusWithReply(Twitter twitter, String msg, String replyTo){
        String fullMessage = replyTo + " " + msg;
        return sendPublicMessage(twitter, fullMessage);
    }
    private static boolean sendPublicMessage(Twitter twitter, String message){
        try{
            twitter.updateStatus(message);
            log.debug("--> " + message);
        } catch(TwitterException e){
            log.error("Unable to send message: " + message, e);
            return false;
        }
        return true;
    }
}
