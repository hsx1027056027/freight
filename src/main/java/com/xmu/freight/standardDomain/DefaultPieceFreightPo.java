package com.xmu.freight.standardDomain;

import com.xmu.freight.util.JacksonUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description: 默认件数模板
 * @Date: Created in 16:00 2019/12/11
 **/

@EqualsAndHashCode
public class DefaultPieceFreightPo {

    private Integer id;
    /**
     * 货物运送的目的地（即收货地址）
     * JSON格式: {"dest": [xxx,xxx,xxx,xxx,xxx]}
     * eg. {"dest": [1, 2, 3, 4, 5]}
     */
    private String destination;
    /**
     * 单位比例
     */
    private BigDecimal unitRate;
    /**
     * 快递送到需要的时间（次日 或者 1-2天等 ）
     */
    private String requireDays;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

    public boolean validate()
    {
        String jsonString = this.getDestination();
        jsonString = org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonString);
        List<Integer> regionIds = JacksonUtil.parseIntegerList(jsonString, "dest");
        if(jsonString==null||regionIds==null||regionIds.toString().equals("[]"))
        {
            return false;
        }
        if(this.getRequireDays()==null||this.getUnitRate()==null)
        {
            return false;
        }
        return true;
    }


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

    public BigDecimal getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
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
