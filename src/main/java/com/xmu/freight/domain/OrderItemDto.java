package com.xmu.freight.domain;


import com.xmu.freight.standardDomain.*;

/**
 * @author hsx
 * @version 1.0
 * @date 2019/12/11 14:48
 */
public class OrderItemDto {
    private OrderItemPo orderItemPo;

    private ProductDto productDto;

    public OrderItemPo getOrderItemPo() {
        return orderItemPo;
    }

    public void setOrderItemPo(OrderItemPo orderItemPo) {
        this.orderItemPo = orderItemPo;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

}
