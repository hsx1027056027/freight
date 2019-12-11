package com.xmu.freight.controller;


import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.OrderItemDto;
import com.xmu.freight.domain.ProductDto;
import com.xmu.freight.domain.SpecialFreightDto;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.service.impl.FreightServiceImpl;
import com.xmu.freight.standardController.FreightController;
import com.xmu.freight.standardDomain.OrderItem;
import com.xmu.freight.util.ResponseUtil;
import com.xmu.freight.vo.OrderFreightRequestVo;
import com.xmu.freight.vo.OrderItemVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin-freight")// /wx/order

public class FreightControllerDto implements FreightController {
    @Autowired
    FreightService freightService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Object getDefaultFreights() {
        return freightService.getDefaultFreights();
    }

    @Override
    public Object getSpecialFreight() {
        return freightService.getSpecialFreights();
    }

    @Override
    public Object addDefaultFreights(@RequestBody DefaultFreightDto defaultFreightDto) {
        return freightService.addDefaultFreight(defaultFreightDto);
    }

    @Override
    public Object addSpecialFreight(@RequestBody SpecialFreightDto specialFreightDto) {
        return freightService.addSpecialFreight(specialFreightDto);
    }

    @Override
    public Object deleteDefaultFreight(String defaultFreightsId) {
        return null;
    }

    @Override
    public Object deleteSpecialFreight(String specialFreightsId) {
        return null;
    }

    @Override
    public Object updateSpecialFreight(String specialFreightsId) {
        return null;
    }

    @Override
    public Object updateDefaultFreight(String defaultFreightsId) {
        return null;
    }

    @Override
    public Object getFreight(@RequestBody OrderFreightRequestVo orderFreightRequestVo) {
//        for (OrderItem item:orderFreightRequestVo.getOrderItemList()) {
//            //TODO:获得goods
//        }
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
}
