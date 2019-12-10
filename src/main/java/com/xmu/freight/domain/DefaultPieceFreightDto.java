package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.DefaultPieceFreight;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DefaultPieceFreightDto extends DefaultPieceFreight {

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
    public BigDecimal getUnitRate() {
        return super.getUnitRate();
    }

    @Override
    public void setUnitRate(BigDecimal unitRate) {
        super.setUnitRate(unitRate);
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

    @Override
    public String getRequireDays() {
        return super.getRequireDays();
    }

    @Override
    public void setRequireDays(String requireDays) {
        super.setRequireDays(requireDays);
    }
}
