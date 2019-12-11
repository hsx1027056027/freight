package com.xmu.freight.domain;


import com.xmu.freight.standardDomain.Address;
import com.xmu.freight.standardDomain.DefaultFreight;
import com.xmu.freight.util.JacksonUtil;
import com.xmu.freight.vo.OrderItemVo;
import org.apache.ibatis.type.Alias;
import org.codehaus.jettison.json.JSONArray;
import com.alibaba.fastjson.JSON;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


/**
 * @Author: hsx
 * @Description: 默认运费模板
 * @Date: Created in
 * @Modified By:
 **/


public class DefaultFreightDto extends DefaultFreight   {


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
            BigDecimal temp = allGoodsWeight.divideAndRemainder(new BigDecimal(0.5))[0].add(new BigDecimal(1));
            fee.add((temp.multiply(this.getContinueHeavyPrice())));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l3))<=0)
        {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l2));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(this.getOver10Price()));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l4))<=0) {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee.add(this.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l3));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(this.getOver50Price()));
            return fee;
        }
        else if(allGoodsWeight.compareTo(new BigDecimal(l5))<=0)
        {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee.add(this.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            fee.add(this.getOver50Price().multiply(new BigDecimal(50)));
            //100到300
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l4));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(this.getOver100Price()));
            return fee;
        }
        else
        {
            //首重
            fee = this.getFirstHeavyPrice();
            //10内
            fee.add(this.getContinueHeavyPrice().multiply(new BigDecimal(19)));
            //10到50
            fee.add(this.getOver10Price().multiply(new BigDecimal(40)));
            //50到100
            fee.add(this.getOver50Price().multiply(new BigDecimal(50)));
            //100到300
            fee.add(this.getOver100Price().multiply(new BigDecimal(200)));
            //300以上
            BigDecimal temp1 = allGoodsWeight.subtract(new BigDecimal(l5));
            BigDecimal temp2 = temp1.divideAndRemainder(new BigDecimal(1))[0].add(new BigDecimal(1));
            fee.add(temp2.multiply(this.getOver300Price()));
            return fee;
        }
    }


    public List<Integer> getDestinationList() {
        //转换方法
        String jsonString = super.getDestination();
        return  JSON.parseArray(jsonString,Integer.class);
    }

    public void setDestinationList( List<Integer> regionIds) {
        //转换方法
        super.setDestination(JSON.toJSONString(regionIds));
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
