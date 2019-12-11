package com.xmu.freight.controller;


import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.OrderItemDto;
import com.xmu.freight.domain.ProductDto;
import com.xmu.freight.domain.SpecialFreightDto;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.standardController.FreightController;
import com.xmu.freight.standardDomain.DefaultPieceFreight;
import com.xmu.freight.standardDomain.SpecialFreight;
import com.xmu.freight.util.ResponseUtil;
import com.xmu.freight.vo.OrderFreightRequestVo;
import com.xmu.freight.vo.OrderItemVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



public class FreightControllerDto implements FreightController {
    @Autowired
    FreightService freightService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获得运费
     * @param orderFreightRequestVo
     * @return 运费
     */
    @Override
    public Object getFreight(@RequestBody OrderFreightRequestVo orderFreightRequestVo) {
        List<OrderItemDto> OrderItemDtoList =new ArrayList<OrderItemDto>();
        for (OrderItemVo orderItemVo:orderFreightRequestVo.getOrderItemVoList())
        {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setOrderItem(orderItemVo.getOrderItem());
            orderItemDto.setProductDto(new ProductDto(orderItemVo.getGoods()));
            OrderItemDtoList.add(orderItemDto);
        }
        Object retObj = ResponseUtil.ok(freightService.getFreight(OrderItemDtoList,orderFreightRequestVo.getAddress()));
        return retObj;
    }

    @Override
    public Object getDefaultFreights() {
        return null;
    }

    @Override
    public Object getSpecialFreight() {
        return null;
    }

    @Override
    public Object addDefaultFreights(DefaultPieceFreight defaultPieceFreight) {
        return null;
    }

    @Override
    public Object addSpecialFreight(SpecialFreight specialFreight) {
        return null;
    }

    @Override
    public Object deleteDefaultFreight(Integer id) {
        return null;
    }

    @Override
    public Object deleteSpecialFreight(Integer id) {
        return null;
    }

    @Override
    public Object updateSpecialFreight(Integer id, SpecialFreight specialFreight) {
        return null;
    }

    @Override
    public Object updateDefaultFreight(Integer id, DefaultPieceFreight defaultPieceFreight) {
        return null;
    }
}
