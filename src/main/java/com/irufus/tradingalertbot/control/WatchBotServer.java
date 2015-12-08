package com.irufus.tradingalertbot.control;


import com.irufus.tradingalertbot.communications.MessageListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twitter4j.*;

/**
 * Created by Ishmael on 11/18/2015.
 */
public class WatchBotServer {
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    static Logger log = Logger.getLogger(WatchBotServer.class);

    public static void main(String[] args){

        Twitter twitter = new TwitterFactory().getInstance();
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        MessageListener messageListener = null;
        try {
            messageListener = new MessageListener(twitter, context);
            twitterStream.addListener(messageListener);
            twitterStream.user();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
    private static void startup(){
        log.info("Starting up server");
    }
    private static void shutdown(){
        log.info("Shutting Down");
    }
}