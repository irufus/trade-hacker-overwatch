package com.irufus.tradingalertbot.data.raw;

import java.math.BigDecimal;

/**
 * Created by irufus on 11/24/15.
 */
public class GoogleQuoteJSONObject {
    private String id;
    private String t; //symbol
    private String e; //exchange
    private BigDecimal l; // price
    private BigDecimal l_fix;
    private String s;
    private String ltt; // h/mm of quote
    private String lt; //full date and time of quote
    private String lt_dts; //time format of lt
    private String c; //change
    private BigDecimal c_fix; //change fixed (no symbols)
    private BigDecimal cp; //change percentage
    private BigDecimal cp_fix; //change
    private String ccol; //change column name
    private BigDecimal pcls_fix; //opening price fix

    public String getCcol() {
        return ccol;
    }

    public void setCcol(String ccol) {
        this.ccol = ccol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public BigDecimal getL() {
        return l;
    }

    public void setL(BigDecimal l) {
        this.l = l;
    }

    public BigDecimal getL_fix() {
        return l_fix;
    }

    public void setL_fix(BigDecimal l_fix) {
        this.l_fix = l_fix;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getLtt() {
        return ltt;
    }

    public void setLtt(String ltt) {
        this.ltt = ltt;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getLt_dts() {
        return lt_dts;
    }

    public void setLt_dts(String lt_dts) {
        this.lt_dts = lt_dts;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public BigDecimal getC_fix() {
        return c_fix;
    }

    public void setC_fix(BigDecimal c_fix) {
        this.c_fix = c_fix;
    }

    public BigDecimal getCp() {
        return cp;
    }

    public void setCp(BigDecimal cp) {
        this.cp = cp;
    }

    public BigDecimal getCp_fix() {
        return cp_fix;
    }

    public void setCp_fix(BigDecimal cp_fix) {
        this.cp_fix = cp_fix;
    }

    public BigDecimal getPcls_fix() {
        return pcls_fix;
    }

    public void setPcls_fix(BigDecimal pcls_fix) {
        this.pcls_fix = pcls_fix;
    }



}
