package com.irufus.tradingalertbot.core.bo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by irufus on 11/19/15.
 */
@Entity
@Table(name="watches", schema="tradingalertbot")
public class Watch {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name="generator", strategy = "identity")
    @Column(name = "watchid", nullable = true)
    private Integer watchID;

    @Column(name="requestor", nullable = false, length=45)
    private String requestor;

    @Column(name="symbol", nullable= false, length=45)
    private String symbol;

    @Column(name="type", nullable=false, length=10)
    private String type;

    @Column(name="ispublic", columnDefinition = "BIT", length = 1)
    private Boolean isPublic;

    @Column(name="alertHI")
    private BigDecimal alertHI;

    @Column(name="alertLOW")
    private BigDecimal alertLOW;

    @Column(name="message", length=45)
    private String message;

    @Column(name="expiration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

    public Watch(){
        watchID = null;
        requestor = "";
        symbol = "";
        type = "";
        isPublic = true;
        alertHI = null;
        alertLOW = null;
        message = "";
        expiration = null;
    }
    public Watch(String message){
        String[] param = message.split(" ");
        String action = param[0];
        String symbol = param[1];
        String position = param[2];
        String price = param[3];

        this.symbol = symbol.replace("$", "");
        this.type = "Stock";
        this.isPublic = true;
        if(position.equals("below")){
            alertLOW = new BigDecimal(price);
        }
        else if(position.equals("above")){
            alertHI = new BigDecimal(price);
        }
    }

    public Integer getWatchID() {
        return watchID;
    }

    public void setWatchID(Integer watchID) {
        this.watchID = watchID;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public BigDecimal getAlertHI() {
        return alertHI;
    }

    public void setAlertHI(BigDecimal alertHI) {
        this.alertHI = alertHI;
    }

    public BigDecimal getAlertLOW() {
        return alertLOW;
    }

    public void setAlertLOW(BigDecimal alertLOW) {
        this.alertLOW = alertLOW;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
