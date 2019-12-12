package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.*;

/**
 * @author hsx
 * @version 1.0
 * @date 2019/12/11 14:49
 */
public class ProductDto {
    private ProductPo productPo;

    private GoodsPo goodsPo;

    public ProductDto(){ };

    public ProductDto(GoodsPo goodsPo)
    {
        this.goodsPo = goodsPo;
    }

    public ProductDto(GoodsPo goodsPo, ProductPo productPo)
    {
        this.productPo = productPo;
        this.goodsPo = goodsPo;
    }


    public ProductPo getProductPo() {
        return productPo;
    }

    public void setProductPo(ProductPo productPo) {
        this.productPo = productPo;
    }

    public GoodsPo getGoodsPo() {
        return goodsPo;
    }

    public void setGoodsPo(GoodsPo goodsPo) {
        this.goodsPo = goodsPo;
    }
}
