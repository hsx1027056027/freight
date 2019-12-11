package com.xmu.freight.vo;

import com.xmu.freight.standardDomain.Address;
import com.xmu.freight.standardDomain.Goods;
import com.xmu.freight.standardDomain.OrderItem;

import java.util.HashMap;
import java.util.List;

public class OrderFreightRequestVo {
    private Address address;

    private List<OrderItemVo> orderItemVoList;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }
}
