package com.xmu.freight.domain;

import org.apache.ibatis.type.Alias;
import com.xmu.freight.standardDomain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: hsx
 * @Description: 特殊运费模板
 * @Date: Created in
 * @Modified By:
 **/
@Alias("SpecialFreight")
public class SpecialFreightDto extends SpecialFreight {


    /**
     * 通过特殊模板来计算运费
     * @param rate 比率
     * @param allGoodsNum 商品总件数
     * @return BigDecimal运费
     */
    public BigDecimal getSpecialFee(BigDecimal rate,Integer allGoodsNum)
    {
        if(allGoodsNum <0)
        {
            return new BigDecimal(-1);
        }
        else if (allGoodsNum == 0)
        {
            return BigDecimal.ZERO;
        }
        else if(allGoodsNum <= this.getFirstNumPiece())
        {
            return this.getFirstNumPiecePrice().multiply(rate);
        }
        else
        {
            //共几个x件向上取整
            int times = 0 ;
            if((allGoodsNum-this.getFirstNumPiece()) % this.getContinueNumPiece() == 0)
            {
                times = (allGoodsNum-this.getFirstNumPiece()) / this.getContinueNumPiece();
            }
            else
            {
                times = (allGoodsNum-this.getFirstNumPiece()) / this.getContinueNumPiece() + 1;
            }
            BigDecimal nomalFee = this.getFirstNumPiecePrice().add(this.getContinueNumPiecePrice().multiply(new BigDecimal(times)));
            return nomalFee.multiply(rate);
        }
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
