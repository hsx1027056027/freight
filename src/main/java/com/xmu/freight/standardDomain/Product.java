package com.xmu.freight.standardDomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: 数据库与对象模型标准组
 * @Description: 产品信息
 * @Date: Created in 16:00 2019/11/29
 * @Modified By:
 **/

public class Product {

    private Integer id;
    /**
     * 产品对应商品的id
     */
    private Integer goodsId;
    /**
     * 组合产品需要存对应产品的id
     */
    private String productIds;
    /**
     * 产品图片的url
     */
    private String picUrl;
    /**
     * sku属性，用于描述特定货品，如红色，41码
     */
    private String specifications;
    /**
     * 产品价格
     */
    private BigDecimal price;
    /**
     * 产品安全库存
     */
    private Integer saftyStock;

    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", productIds='" + productIds + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", specifications='" + specifications + '\'' +
                ", price=" + price +
                ", saftyStock=" + saftyStock +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", beDeleted=" + beDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (defaultFreight == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        defaultFreight.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        defaultFreight.goodsId = goodsId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        defaultFreight.productIds = productIds;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        defaultFreight.picUrl = picUrl;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        defaultFreight.specifications = specifications;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        defaultFreight.price = price;
    }

    public Integer getSaftyStock() {
        return saftyStock;
    }

    public void setSaftyStock(Integer saftyStock) {
        defaultFreight.saftyStock = saftyStock;
    }


    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        defaultFreight.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        defaultFreight.gmtModified = gmtModified;
    }

    public Boolean getBeDeleted() {
        return beDeleted;
    }

    public void setBeDeleted(Boolean beDeleted) {
        defaultFreight.beDeleted = beDeleted;
    }
}
