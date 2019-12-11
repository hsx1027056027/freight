package com.xmu.freight.domain;


import com.xmu.freight.standardDomain.Address;
import com.xmu.freight.standardDomain.DefaultFreight;
import com.xmu.freight.util.JacksonUtil;
import com.xmu.freight.vo.OrderItemVo;
import org.codehaus.jettison.json.JSONArray;
import org.mortbay.util.ajax.JSON;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


/**
 * @Author: hsx
 * @Description: 默认运费模板
 * @Date: Created in
 * @Modified By:
 **/
public class DefaultFreightDto  {

    private DefaultFreight defaultFreight;

    /**
     * 通过默认模板来计算运费
     * @param allGoodsWeight 商品总重量
     * @return BigDecimal运费
     */
    public BigDecimal getDefaultFee(BigDecimal allGoodsWeight)
    {
        //重量门槛
        String l1 = "0.5";
        String l2 = "10";
        String l3 = "50";
        String l4 = "100";
        String l5 = "300";

        BigDecimal fee = BigDecimal.ZERO;
        if(allGoodsWeight.compareTo(BigDecimal.ZERO)<0)
        {
            return new BigDecimal(-1);
        }
        else if(allGoodsWeight.compareTo(BigDecimal.ZERO) == 0)
        {
            return  BigDecimal.ZERO;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l1))<=0)
        {
            return  defaultFreight.getFirstHeavyPrice();
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l2))<=0)
        {
            //首重
            fee = defaultFreight.getFirstHeavyPrice();
            //0.5到10
            BigDecimal temp = allGoodsWeight.divideAndRemainder(new BigDecimal(0.5))[0].add(new BigDecimal(1));
            fee.add((temp.multiply(defaultFreight.getContinueHeavyPrice())));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l3))<=0)
        {
            //首重
            fee = defaultFreight.getFirstHeavyPrice();
            //10内
            fee.add(defaultFreight.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l2));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(defaultFreight.getOver10Price()));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l4))<=0) {
            //首重
            fee = defaultFreight.getFirstHeavyPrice();
            //10内
            fee.add(defaultFreight.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee.add(defaultFreight.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l3));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(defaultFreight.getOver50Price()));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l5))<=0)
        {
            //首重
            fee = defaultFreight.getFirstHeavyPrice();
            //10内
            fee.add(defaultFreight.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee.add(defaultFreight.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            fee.add(defaultFreight.getOver50Price().multiply(new BigDecimal(50)));
            //100到300
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l4));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(defaultFreight.getOver100Price()));
            return fee;
        }
        else
        {
            //首重
            fee = defaultFreight.getFirstHeavyPrice();
            //10内
            fee.add(defaultFreight.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee.add(defaultFreight.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            fee.add(defaultFreight.getOver50Price().multiply(new BigDecimal(50)));
            //100到300
            fee.add(defaultFreight.getOver100Price().multiply(new BigDecimal(200)));
            //300以上
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l5));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(defaultFreight.getOver300Price()));
            return fee;
        }
    }


    public List<Integer> getDestination() {
        //转换方法
        String jsonString = defaultFreight.getDestination();
        jsonString = org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonString);
        return JacksonUtil.parseIntegerList(jsonString, "rIDs");

    }


    public void setDestination( List<Integer> regionIds) {
        //转换方法
        Map<String,Object> idMap = new HashMap<String, Object>(1);
        idMap.put("rIDs", regionIds);
        defaultFreight.setDestination(JacksonUtil.toJson(idMap));
    }

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


    public DefaultFreight getDefaultFreight() {
        return defaultFreight;
    }

    public void setDefaultFreight(DefaultFreight defaultFreight) {
        this.defaultFreight = defaultFreight;
    }
}
