package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.SpecialFreight;
import org.apache.ibatis.type.Alias;

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
            int times = (allGoodsNum-this.getFirstNumPiece()) % this.getContinueNumPiece()+1;
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

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public Integer getFirstNumPiece() {
        return super.getFirstNumPiece();
    }

    @Override
    public void setFirstNumPiece(Integer firstNumPiece) {
        super.setFirstNumPiece(firstNumPiece);
    }

    @Override
    public BigDecimal getFirstNumPiecePrice() {
        return super.getFirstNumPiecePrice();
    }

    @Override
    public void setFirstNumPiecePrice(BigDecimal firstNumPiecePrice) {
        super.setFirstNumPiecePrice(firstNumPiecePrice);
    }

    @Override
    public Integer getContinueNumPiece() {
        return super.getContinueNumPiece();
    }

    @Override
    public void setContinueNumPiece(Integer continueNumPiece) {
        super.setContinueNumPiece(continueNumPiece);
    }

    @Override
    public BigDecimal getContinueNumPiecePrice() {
        return super.getContinueNumPiecePrice();
    }

    @Override
    public void setContinueNumPiecePrice(BigDecimal continueNumPiecePrice) {
        super.setContinueNumPiecePrice(continueNumPiecePrice);
    }

    @Override
    public LocalDateTime getGmtCreate() {
        return super.getGmtCreate();
    }

    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {
        super.setGmtCreate(gmtCreate);
    }

    @Override
    public LocalDateTime getGmtModified() {
        return super.getGmtModified();
    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {
        super.setGmtModified(gmtModified);
    }

    @Override
    public Boolean getBeDeleted() {
        return super.getBeDeleted();
    }

    @Override
    public void setBeDeleted(Boolean beDeleted) {
        super.setBeDeleted(beDeleted);
    }
}
