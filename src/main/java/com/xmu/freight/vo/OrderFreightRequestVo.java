package com.xmu.freight.vo;


import com.xmu.freight.standardDomain.*;

import java.util.List;

public class OrderFreightRequestVo {
    private AddressPo addressPo;

    private List<OrderItemVo> orderItemVoList;

    public AddressPo getAddressPo() {
        return addressPo;
    }

    public void setAddressPo(AddressPo addressPo) {
        this.addressPo = addressPo;
    }


    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }
}
