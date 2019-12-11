package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.Goods;
import com.xmu.freight.standardDomain.Product;

/**
 * @author hsx
 * @version 1.0
 * @date 2019/12/11 14:49
 */
public class ProductDto {
    private Product product;

    private Goods goods;

    public ProductDto(){ };

    public ProductDto(Goods goods)
    {
        this.goods = goods;
    }

    public ProductDto(Goods goods,Product product)
    {
        this.product = product;
        this.goods = goods;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
