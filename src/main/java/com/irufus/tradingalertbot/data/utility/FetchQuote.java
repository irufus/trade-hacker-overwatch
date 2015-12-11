package com.irufus.tradingalertbot.data.utility;

import com.irufus.tradingalertbot.data.wrapper.GoogleQuote;
import twitter4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

/**
 * Created by irufus on 12/3/15.
 */
public class FetchQuote {
    private static Logger log = Logger.getLogger(FetchQuote.class);
    private final static String googlePrefix = "http://www.google.com/finance/info?q=";

    public static GoogleQuote getGoogleQuote(String symbol){
        GoogleQuote quote = null;
        String urlRequest = googlePrefix + symbol;
        try {
            URL url = new URL(urlRequest);
            URLConnection connection = url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String rawJson = "";
            String line = "";
            while((line = input.readLine()) != null){
                rawJson += line;
            }
            quote = new GoogleQuote(rawJson);
        } catch(MalformedURLException urlex){
            log.error("Invalid url: " + urlRequest, urlex);
        } catch (ParseException e) {
            log.error("Unable to parse", e);
        } catch (IOException e) {
            log.error("IOException thrown", e);
        }
        return quote;
    }
}
