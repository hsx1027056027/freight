package com.xmu.freight.standardDomain;

import com.xmu.freight.domain.DefaultPieceFreightDto;
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

    public DefaultPieceFreight(){}

    public DefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto)
    {
        this.setId(defaultPieceFreightDto.getId());
        this.setRegionIds(defaultPieceFreightDto.getDestinationList());
        this.setDestination(defaultPieceFreightDto.getDestination());
        this.setUnitRate(defaultPieceFreightDto.getUnitRate());
        this.setGmtCreate(defaultPieceFreightDto.getGmtCreate());
        this.setGmtModified(defaultPieceFreightDto.getGmtModified());
        this.setRequireDays(defaultPieceFreightDto.getRequireDays());
        this.setBeDeleted(defaultPieceFreightDto.getBeDeleted());
    }


    public List<Integer> getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(List<Integer> regionIds) {
        this.regionIds = regionIds;
    }
}