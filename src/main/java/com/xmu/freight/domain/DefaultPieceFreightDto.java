package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.DefaultPieceFreight;
import com.xmu.freight.util.JacksonUtil;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DefaultPieceFreightDto extends DefaultPieceFreight  {


    public List<Integer> getDestinationList() {
        //转换方法
        String jsonString = this.getDestination();
        jsonString = org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonString);
        return  JacksonUtil.parseIntegerList(jsonString, "rIDs");

    }


    public void setDestinationList( List<Integer> regionIds) {
        //转换方法
        Map<String,Object> idMap = new HashMap<String, Object>(1);
        idMap.put("rIDs", regionIds);
        this.setDestination(JacksonUtil.toJson(idMap));
    }


}
