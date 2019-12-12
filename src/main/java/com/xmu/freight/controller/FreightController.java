package com.xmu.freight.controller;


import com.xmu.freight.domain.OrderItemDto;
import com.xmu.freight.domain.ProductDto;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.util.ResponseUtil;
import com.xmu.freight.vo.OrderFreightRequestVo;
import com.xmu.freight.vo.OrderItemVo;
import com.xmu.freight.standardDomain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



public class FreightController {
    @Autowired
    FreightService freightService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获得运费
     * @param orderFreightRequestVo
     * @return 运费
     */
    public Object getFreight(@RequestBody OrderFreightRequestVo orderFreightRequestVo) {
        List<OrderItemDto> OrderItemDtoList =new ArrayList<OrderItemDto>();
        for (OrderItemVo orderItemVo:orderFreightRequestVo.getOrderItemVoList())
        {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setOrderItemPo(orderItemVo.getOrderItemPo());
            orderItemDto.setProductDto(new ProductDto(orderItemVo.getGoodsPo()));
            OrderItemDtoList.add(orderItemDto);
        }
        Object retObj = ResponseUtil.ok(freightService.getFreight(OrderItemDtoList,orderFreightRequestVo.getAddressPo()));
        return retObj;
    }

    public Object getDefaultFreights() {
        return null;
    }

    public Object getSpecialFreight() {
        return null;
    }

    public Object addDefaultFreights(DefaultPieceFreightPo defaultPieceFreightPo) {
        return null;
    }

    public Object addSpecialFreight(SpecialFreight specialFreightPo) {
        return null;
    }

    public Object deleteDefaultFreight(Integer id) {
        return null;
    }

    public Object deleteSpecialFreight(Integer id) {
        return null;
    }

    public Object updateSpecialFreight(Integer id, SpecialFreight specialFreightPo) {
        return null;
    }

    public Object updateDefaultFreight(Integer id, DefaultPieceFreightPo defaultPieceFreightPo) {
        return null;
    }
}
