package com.xmu.freight.standardDomain;

import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.util.JacksonUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:默认计重模板对象
 * @Data:Created in 14:50 2019/12/11
 **/

@EqualsAndHashCode(callSuper = true)
public class DefaultFreight extends DefaultFreightPo {

    private List<Integer> regionIds;

    public DefaultFreight(){}

    public DefaultFreight(DefaultFreightDto defaultFreightDto)
    {
        this.setId(defaultFreightDto.getId());
        this.setDestination(defaultFreightDto.getDestination());
        this.setRegionIds(defaultFreightDto.getDestinationList());
        this.setFirstHeavyPrice(defaultFreightDto.getFirstHeavyPrice());
        this.setContinueHeavyPrice(defaultFreightDto.getContinueHeavyPrice());
        this.setOver10Price(defaultFreightDto.getOver10Price());
        this.setOver50Price(defaultFreightDto.getOver50Price());
        this.setOver100Price(defaultFreightDto.getOver100Price());
        this.setOver300Price(defaultFreightDto.getOver300Price());
        this.setRequireDays(defaultFreightDto.getRequireDays());
        this.setGmtCreate(defaultFreightDto.getGmtCreate());
        this.setGmtModified(defaultFreightDto.getGmtModified());
        this.setBeDeleted(defaultFreightDto.getBeDeleted());
    }


    public List<Integer> getRegionIds() {
        String jsonString = this.getDestination();
        jsonString = org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonString);
        regionIds = JacksonUtil.parseIntegerList(jsonString, "dest");
        return regionIds;
    }

    public void setRegionIds(List<Integer> regionIds) {
        Map<String,Object> idMap = new HashMap<String, Object>(1);
        idMap.put("dest", regionIds);
        super.setDestination(JacksonUtil.toJson(idMap));
        this.regionIds = regionIds;
    }


}
