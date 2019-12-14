package com.xmu.freight.standardDomain;

import com.xmu.freight.domain.SpecialFreightDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: 数据库与对象模型标准组
 * @Description: 特殊运费模板
 * @Date: Created in 16:00 2019/12/11
 **/

@EqualsAndHashCode
public class SpecialFreight {

    private Integer id;
    /**
     * 首几件
     */
    private Integer firstNumPiece;
    /**
     * 首几件价格
     */
    private BigDecimal firstNumPiecePrice;
    /**
     * 续几件
     */
    private Integer continueNumPiece;
    /**
     * 续几件的价格
     */
    private BigDecimal continueNumPiecePrice;

    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

    public SpecialFreight(){}

    public SpecialFreight(SpecialFreightDto specialFreightDto)
    {
            this.setId(specialFreightDto.getId());
            this.setFirstNumPiece(specialFreightDto.getFirstNumPiece());
            this.setFirstNumPiecePrice(specialFreightDto.getFirstNumPiecePrice());
            this.setContinueNumPiece(specialFreightDto.getContinueNumPiece());
            this.setContinueNumPiecePrice(specialFreightDto.getContinueNumPiecePrice());
            this.setGmtCreate(specialFreightDto.getGmtCreate());
            this.setGmtModified(specialFreightDto.getGmtModified());
            this.setBeDeleted(specialFreightDto.getBeDeleted());
    }

    public boolean validate()
    {
        if(this.getContinueNumPiece()==null||this.getContinueNumPiecePrice()==null||this.getFirstNumPiece()==null||this.getFirstNumPiecePrice()==null)
        {
            return false;
        }
        return true;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFirstNumPiece() {
        return firstNumPiece;
    }

    public void setFirstNumPiece(Integer firstNumPiece) {
        this.firstNumPiece = firstNumPiece;
    }

    public BigDecimal getFirstNumPiecePrice() {
        return firstNumPiecePrice;
    }

    public void setFirstNumPiecePrice(BigDecimal firstNumPiecePrice) {
        this.firstNumPiecePrice = firstNumPiecePrice;
    }

    public Integer getContinueNumPiece() {
        return continueNumPiece;
    }

    public void setContinueNumPiece(Integer continueNumPiece) {
        this.continueNumPiece = continueNumPiece;
    }

    public BigDecimal getContinueNumPiecePrice() {
        return continueNumPiecePrice;
    }

    public void setContinueNumPiecePrice(BigDecimal continueNumPiecePrice) {
        this.continueNumPiecePrice = continueNumPiecePrice;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getBeDeleted() {
        return beDeleted;
    }

    public void setBeDeleted(Boolean beDeleted) {
        this.beDeleted = beDeleted;
    }
}
