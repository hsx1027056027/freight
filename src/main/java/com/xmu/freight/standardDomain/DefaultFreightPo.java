package com.xmu.freight.standardDomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * @Author: 数据库与对象模型标准组
 * @Description: 默认重量模板
 * @Date: Created in 16:00 2019/12/11
 **/


@EqualsAndHashCode
public class DefaultFreightPo {
    private Integer id;
    /**
     * 货物运送的目的地（即收货地址）
     * JSON格式: {"dest": [xxx,xxx,xxx,xxx,xxx]}
     * eg. {"dest": [1, 2, 3, 4, 5]}
     */
    private String destination;
    /**
     * 快递重量模板中快递首重0.5kg的价格
     */
    private BigDecimal firstHeavyPrice;
    /**
     * 续重的邮费价格
     */
    private BigDecimal continueHeavyPrice;
    /**
     * 10kg以上每kg的邮费价格（下一区间以下）
     */
    private BigDecimal over10Price;
    /**
     * 50kg以上每kg的邮费价格
     */
    private BigDecimal over50Price;
    /**
     * 100kg以上每kg的邮费价格
     */
    private BigDecimal over100Price;
    /**
     * 300kg以上每kg的邮费价格
     */
    private BigDecimal over300Price;
    /**
     * 快递送到需要的时间（次日 或者 1-2天等 ）
     */
    private String requireDays;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getFirstHeavyPrice() {
        return firstHeavyPrice;
    }

    public void setFirstHeavyPrice(BigDecimal firstHeavyPrice) {
        this.firstHeavyPrice = firstHeavyPrice;
    }

    public BigDecimal getContinueHeavyPrice() {
        return continueHeavyPrice;
    }

    public void setContinueHeavyPrice(BigDecimal continueHeavyPrice) {
        this.continueHeavyPrice = continueHeavyPrice;
    }

    public BigDecimal getOver10Price() {
        return over10Price;
    }

    public void setOver10Price(BigDecimal over10Price) {
        this.over10Price = over10Price;
    }

    public BigDecimal getOver50Price() {
        return over50Price;
    }

    public void setOver50Price(BigDecimal over50Price) {
        this.over50Price = over50Price;
    }

    public BigDecimal getOver100Price() {
        return over100Price;
    }

    public void setOver100Price(BigDecimal over100Price) {
        this.over100Price = over100Price;
    }

    public BigDecimal getOver300Price() {
        return over300Price;
    }

    public void setOver300Price(BigDecimal over300Price) {
        this.over300Price = over300Price;
    }

    public String getRequireDays() {
        return requireDays;
    }

    public void setRequireDays(String requireDays) {
        this.requireDays = requireDays;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getBeDeleted() {
        return beDeleted;
    }

    public void setBeDeleted(Boolean beDeleted) {
        this.beDeleted = beDeleted;
    }
}
