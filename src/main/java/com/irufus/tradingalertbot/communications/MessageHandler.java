package com.irufus.tradingalertbot.communications;

import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Created by irufus on 11/19/15.
 */
public class MessageHandler {

    private Status status;
    private String reply;
    public MessageHandler(Status status){
        String text = status.getText();

                /*
        if (text.contains("open hatch")) {
            try {
                twitter.updateStatus("I'm sorry " + replyTo + " . I'm afraid I can't do that: https://www.youtube.com/watch?v=7qnd-hdmgfk");
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }*/

    }
}
