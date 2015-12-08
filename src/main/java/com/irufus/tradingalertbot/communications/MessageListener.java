package com.irufus.tradingalertbot.communications;

import com.irufus.tradingalertbot.core.bo.Watch;
import com.irufus.tradingalertbot.core.services.RequestService;
import org.springframework.context.ApplicationContext;
import twitter4j.*;

/**
 * Created by Ishmael on 11/19/2015.
 */
public class MessageListener implements UserStreamListener {
    private Twitter twitter;
    private String screenName;
    static Logger log = Logger.getLogger(MessageListener.class);
    private RequestService requestService;

    public MessageListener(Twitter twitter, ApplicationContext context) throws TwitterException {
        this.twitter = twitter;
        screenName = twitter.getScreenName();
        requestService = context.getBean(RequestService.class);
    }

    public void onDeletionNotice(long l, long l1) {

    }

    public void onFriendList(long[] longs) {

    }

    public void onFavorite(User user, User user1, Status status) {

    }

    public void onUnfavorite(User user, User user1, Status status) {

    }

    public void onFollow(User user, User user1) {

    }

    public void onUnfollow(User user, User user1) {

    }

    public void onDirectMessage(DirectMessage directMessage) {
        log.info("DM: " + directMessage.getSenderScreenName() + " - " + directMessage.getText());
    }

    public void onUserListMemberAddition(User user, User user1, UserList userList) {

    }

    public void onUserListMemberDeletion(User user, User user1, UserList userList) {

    }

    public void onUserListSubscription(User user, User user1, UserList userList) {

    }

    public void onUserListUnsubscription(User user, User user1, UserList userList) {

    }

    public void onUserListCreation(User user, UserList userList) {

    }

    public void onUserListUpdate(User user, UserList userList) {

    }

    public void onUserListDeletion(User user, UserList userList) {

    }

    public void onUserProfileUpdate(User user) {

    }

    public void onUserSuspension(long l) {

    }

    public void onUserDeletion(long l) {

    }

    public void onBlock(User user, User user1) {

    }

    public void onUnblock(User user, User user1) {

    }

    public void onRetweetedRetweet(User user, User user1, Status status) {

    }

    public void onFavoritedRetweet(User user, User user1, Status status) {

    }

    public void onQuotedTweet(User user, User user1, Status status) {

    }

    public void onStatus(Status status) {
        try {


            if (status.getInReplyToScreenName() != null) {
                String replyTo = "@" + status.getUser().getScreenName();
                log.info("Message: " + replyTo + " - " + status.getText());
                if (status.getInReplyToScreenName().equals(screenName)) {
                    // MessageHandler mhandler = new MessageHandler(status);d
                    String message = status.getText();
                    String trigger = "#remindme ";
                    if (message.contains(trigger)) {

                        int index = message.indexOf(trigger) + trigger.length();
                        if (index > -1) {
                            String command = message.substring(index);
                            log.info("Command given: " + command);
                            try {
                                Watch watch = new Watch(command);
                                watch.setRequestor(replyTo);
                                requestService.saveWatch(watch);
                                String replyMessage = "Got it: " + watch.getSymbol() + " @ ";
                                replyMessage += (watch.getAlertHI() != null)? watch.getAlertHI() + " HI " : "";
                                replyMessage += (watch.getAlertLOW() != null) ? watch.getAlertLOW() + " LOW " : "";
                                updateStatus(replyMessage, replyTo);
                            } catch (IndexOutOfBoundsException iex) {
                                log.error("Improrper request", iex);
                                updateStatus("Couldn't understand you fam", replyTo);
                            }

                        } else {
                            updateStatus("Couldn't quite understand you fam", replyTo);
                        }
                    }
                }
            }
        }
        catch(Exception ex){
            log.error("Issue processing", ex);
        }
    }

    private boolean updateStatus(String msg, String replyTo){
        try {
            twitter.updateStatus(replyTo + " " + msg);
            log.info("--> " + msg);
        } catch (TwitterException e) {
            log.error("Unable to send message", e);
            return false;
        }
        return true;
    }

    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    public void onTrackLimitationNotice(int i) {

    }

    public void onScrubGeo(long l, long l1) {

    }

    public void onStallWarning(StallWarning stallWarning) {
        log.warn("STALL WARNING: " + stallWarning.getMessage());
    }

    public void onException(Exception e) {

    }
}