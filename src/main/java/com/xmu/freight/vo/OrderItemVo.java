package com.xmu.freight.vo;

import com.xmu.freight.standardDomain.*;

public class OrderItemVo {
    private OrderItemPo orderItemPo;

    private GoodsPo goodsPo;

    public OrderItemPo getOrderItemPo() {
        return orderItemPo;
    }

    public void setOrderItemPo(OrderItemPo orderItemPo) {
        this.orderItemPo = orderItemPo;
    }

    public GoodsPo getGoodsPo() {
        return goodsPo;
    }

    public void setGoodsPo(GoodsPo goodsPo) {
        this.goodsPo = goodsPo;
    }
}
