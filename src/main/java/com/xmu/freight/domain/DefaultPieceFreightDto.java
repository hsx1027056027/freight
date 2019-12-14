package com.xmu.freight.domain;

import com.alibaba.fastjson.JSON;
import com.xmu.freight.util.JacksonUtil;
import org.apache.ibatis.type.Alias;
import com.xmu.freight.standardDomain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Alias("DefaultPieceFreight")
public class DefaultPieceFreightDto extends DefaultPieceFreightPo {

    public DefaultPieceFreightDto(){}

    public DefaultPieceFreightDto(DefaultPieceFreightPo defaultPieceFreightPo)
    {
        this.setId(defaultPieceFreightPo.getId());
        this.setDestination(defaultPieceFreightPo.getDestination());
        this.setUnitRate(defaultPieceFreightPo.getUnitRate());
        this.setGmtCreate(defaultPieceFreightPo.getGmtCreate());
        this.setGmtModified(defaultPieceFreightPo.getGmtModified());
        this.setRequireDays(defaultPieceFreightPo.getRequireDays());
        this.setBeDeleted(defaultPieceFreightPo.getBeDeleted());
    }



    public List<Integer> getDestinationList() {
        //转换方法
        String jsonString = this.getDestination();
        jsonString = org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonString);
        return JacksonUtil.parseIntegerList(jsonString, "dest");

    }


    public void setDestinationList( List<Integer> regionIds) {
        //转换方法
        Map<String,Object> idMap = new HashMap<String, Object>(1);
        idMap.put("dest", regionIds);
        super.setDestination(JacksonUtil.toJson(idMap));

    }


}
