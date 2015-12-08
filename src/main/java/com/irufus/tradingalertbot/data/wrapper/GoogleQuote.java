package com.irufus.tradingalertbot.data.wrapper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.irufus.tradingalertbot.data.raw.GoogleQuoteJSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by irufus on 11/24/15.
 */
public class GoogleQuote {
    private String id;
    private BigDecimal currentPrice;
    private Date currentTime;
    private BigDecimal openingPrice;
    private String exchange;
    private String symbol;
    private BigDecimal changePercentage;
    private BigDecimal change;

    public GoogleQuote(String rawData) throws ParseException {
        String json = rawData.replace("//", "");
        json = json.replace("[", "");
        json = json.replace("]", "");
        Gson gson = new Gson();
        JsonElement mJson = new JsonParser().parse(json);
        GoogleQuoteJSONObject quote = gson.fromJson(mJson, GoogleQuoteJSONObject.class);
        id = quote.getId();
        currentPrice = quote.getL();

        String rawDate = quote.getLt_dts();
        rawDate = rawDate.replace("T", " ");
        rawDate = rawDate.replace("Z", "");
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        currentTime = df.parse(rawDate);
        symbol = quote.getT();
        openingPrice = quote.getPcls_fix();
        exchange = quote.getE();
        String changewithSymbol = quote.getC();
        BigDecimal factor = (changewithSymbol.contains("-"))? new BigDecimal("-1") : new BigDecimal("1");
        changePercentage = quote.getCp_fix().multiply(factor);
        change = quote.getC_fix().multiply(factor);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getChangePercentage() {
        return changePercentage;
    }

    public void setChangePercentage(BigDecimal changePercentage) {
        this.changePercentage = changePercentage;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }


}
