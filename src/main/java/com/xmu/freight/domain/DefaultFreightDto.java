package com.xmu.freight.domain;


import com.xmu.freight.util.JacksonUtil;
import org.apache.ibatis.type.Alias;
import com.alibaba.fastjson.JSON;
import com.xmu.freight.standardDomain.*;

import java.math.BigDecimal;
import java.util.*;


/**
 * @Author: hsx
 * @Description: 默认运费模板
 * @Date: Created in
 * @Modified By:
 **/

@Alias("DefaultFreight")
public class DefaultFreightDto extends DefaultFreightPo {


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
            return  this.getFirstHeavyPrice();
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l2))<=0)
        {
            //首重
            fee = this.getFirstHeavyPrice();
            //0.5到10
            BigDecimal temp = getRemainder(allGoodsWeight,0.5);
            fee = fee.add((temp.multiply(this.getContinueHeavyPrice())));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l3))<=0)
        {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee = fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l2));
            BigDecimal temp2 = getRemainder(temp1,1);
            fee = fee.add(temp2.multiply(this.getOver10Price()));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l4))<=0) {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee = fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee = fee.add(this.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l3));
            BigDecimal temp2 = getRemainder(temp1,1);
            fee = fee.add(temp2.multiply(this.getOver50Price()));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l5))<=0)
        {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee = fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee = fee.add(this.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            fee = fee.add(this.getOver50Price().multiply(new BigDecimal(50)));
            //100到300
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l4));
            BigDecimal temp2 = getRemainder(temp1,1);
            fee = fee.add(temp2.multiply(this.getOver100Price()));
            return fee;
        }
        else
        {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee = fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee = fee.add(this.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            fee = fee.add(this.getOver50Price().multiply(new BigDecimal(50)));
            //100到300
            fee = fee.add(this.getOver100Price().multiply(new BigDecimal(200)));
            //300以上
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l5));
            BigDecimal temp2 = getRemainder(temp1,1);
            fee = fee.add(temp2.multiply(this.getOver300Price()));
            return fee;
        }
    }

    /**
     * 获得余数
     * @param temp1 重量
     * @param per 单位 0.5还是1
     * @return 余数
     */
    public BigDecimal getRemainder(BigDecimal temp1,double per)
    {
        BigDecimal temp = null;
        BigDecimal[] divideResult = temp1.divideAndRemainder(new BigDecimal(per));
        if(divideResult[1].compareTo(BigDecimal.ZERO)==0)
        {
            temp = divideResult[0];
        }
        else
        {
            temp = divideResult[0].add(new BigDecimal(1));
        }
        return temp;
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


}
