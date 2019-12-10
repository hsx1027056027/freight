package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.DefaultFreight;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DefaultFreightDto extends DefaultFreight {
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public DefaultFreightDto() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public String getDestination() {
        return super.getDestination();
    }

    @Override
    public void setDestination(String destination) {
        super.setDestination(destination);
    }

    @Override
    public BigDecimal getFirstHeavyPrice() {
        return super.getFirstHeavyPrice();
    }

    @Override
    public void setFirstHeavyPrice(BigDecimal firstHeavyPrice) {
        super.setFirstHeavyPrice(firstHeavyPrice);
    }

    @Override
    public BigDecimal getContinueHeavyPrice() {
        return super.getContinueHeavyPrice();
    }

    @Override
    public void setContinueHeavyPrice(BigDecimal continueHeavyPrice) {
        super.setContinueHeavyPrice(continueHeavyPrice);
    }

    @Override
    public BigDecimal getOver10Price() {
        return super.getOver10Price();
    }

    @Override
    public void setOver10Price(BigDecimal over10Price) {
        super.setOver10Price(over10Price);
    }

    @Override
    public BigDecimal getOver50Price() {
        return super.getOver50Price();
    }

    @Override
    public void setOver50Price(BigDecimal over50Price) {
        super.setOver50Price(over50Price);
    }

    @Override
    public BigDecimal getOver100Price() {
        return super.getOver100Price();
    }

    @Override
    public void setOver100Price(BigDecimal over100Price) {
        super.setOver100Price(over100Price);
    }

    @Override
    public BigDecimal getOver300Price() {
        return super.getOver300Price();
    }

    @Override
    public void setOver300Price(BigDecimal over300Price) {
        super.setOver300Price(over300Price);
    }

    @Override
    public String getRequireDays() {
        return super.getRequireDays();
    }

    @Override
    public void setRequireDays(String requireDays) {
        super.setRequireDays(requireDays);
    }

    @Override
    public LocalDateTime getGmtCreate() {
        return super.getGmtCreate();
    }

    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {
        super.setGmtCreate(gmtCreate);
    }

    @Override
    public LocalDateTime getGmtModified() {
        return super.getGmtModified();
    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {
        super.setGmtModified(gmtModified);
    }

    @Override
    public Boolean getBeDeleted() {
        return super.getBeDeleted();
    }

    @Override
    public void setBeDeleted(Boolean beDeleted) {
        super.setBeDeleted(beDeleted);
    }
}
