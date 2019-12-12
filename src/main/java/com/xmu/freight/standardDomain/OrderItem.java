package com.xmu.freight.standardDomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:订单明细对象
 * @Data:Created in 14:50 2019/12/11
 **/

@EqualsAndHashCode(callSuper = true)
public class OrderItem extends OrderItemPo {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
