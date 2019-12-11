package com.xmu.freight.domain;

import com.xmu.freight.standardDomain.OrderItem;
import com.xmu.freight.standardDomain.Product;

/**
 * @author hsx
 * @version 1.0
 * @date 2019/12/11 14:48
 */
public class OrderItemDto {
    private OrderItem orderItem;

    private ProductDto productDto;

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

}
