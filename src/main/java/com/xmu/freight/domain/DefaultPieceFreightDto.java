package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.DefaultPieceFreight;
import com.xmu.freight.util.JacksonUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultPieceFreightDto  {
    private DefaultPieceFreight defaultPieceFreight;

    public List<Integer> getDestination() {
        //转换方法
        String jsonString = defaultPieceFreight.getDestination();
        jsonString = org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonString);
        return  JacksonUtil.parseIntegerList(jsonString, "rIDs");

    }


    public void setDestination( List<Integer> regionIds) {
        //转换方法
        Map<String,Object> idMap = new HashMap<String, Object>(1);
        idMap.put("rIDs", regionIds);
        defaultPieceFreight.setDestination(JacksonUtil.toJson(idMap));
    }


    public DefaultPieceFreight getDefaultPieceFreight() {
        return defaultPieceFreight;
    }

    public void setDefaultPieceFreight(DefaultPieceFreight defaultPieceFreight) {
        this.defaultPieceFreight = defaultPieceFreight;
    }
}
