package com.xmu.freight.domain;

import com.alibaba.fastjson.JSON;
import com.xmu.freight.standardDomain.DefaultPieceFreight;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("DefaultPieceFreight")
public class DefaultPieceFreightDto extends DefaultPieceFreight  {


    public List<Integer> getDestinationList() {
        //转换方法
        String jsonString = super.getDestination();
        return  JSON.parseArray(jsonString,Integer.class);

    }


    public void setDestinationList( List<Integer> regionIds) {
        //转换方法
        super.setDestination(JSON.toJSONString(regionIds));
    }


}
