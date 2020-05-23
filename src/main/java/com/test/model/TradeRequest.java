package com.test.model;

import com.test.Util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TradeRequest implements Serializable {

    private Date maturityDate;
    private boolean expiry;
    private Integer version;
    private String tradeID;
    private String couterPartyId;
    private String bookId;
    private Date createDate;
    private boolean completeProcessing;



    public TradeRequest() {

    }

    public TradeRequest(String tradeID, Integer version, String couterPartyId,  String bookId,String maturityDate, String createDate ) throws Exception {
        this.tradeID = tradeID;
        this.version = version;
        this.couterPartyId = couterPartyId;
        this.bookId = bookId;
        this.maturityDate = Util.formatDate(maturityDate);
        this.createDate = Util.formatDate(createDate);
    }
    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public boolean isExpiry() {
        return expiry;
    }

    public void setExpiry(boolean expiry) {
        this.expiry = expiry;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    public String getCouterPartyId() {
        return couterPartyId;
    }

    public void setCouterPartyId(String couterPartyId) {
        this.couterPartyId = couterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isCompleteProcessing() {
        return completeProcessing;
    }

    public void setCompleteProcessing(boolean completeProcessing) {
        this.completeProcessing = completeProcessing;
    }

}
