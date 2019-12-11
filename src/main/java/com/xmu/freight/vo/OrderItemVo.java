package com.xmu.freight.vo;

import com.xmu.freight.standardDomain.Goods;
import com.xmu.freight.standardDomain.OrderItem;

public class OrderItemVo {
    private OrderItem orderItem;

    private Goods goods;

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
