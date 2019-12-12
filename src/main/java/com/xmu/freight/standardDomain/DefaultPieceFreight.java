package com.xmu.freight.standardDomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:默认计件模板对象
 * @Data:Created in 14:50 2019/12/11
 **/

@EqualsAndHashCode(callSuper = true)
public class DefaultPieceFreight extends DefaultPieceFreightPo {
    private List<Integer> regionIds;

    public List<Integer> getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(List<Integer> regionIds) {
        this.regionIds = regionIds;
    }
}