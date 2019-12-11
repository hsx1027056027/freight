package com.xmu.freight.service.impl;

import com.netflix.discovery.converters.Auto;
import com.xmu.freight.dao.DefaultFreightDao;
import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.OrderItemDto;
import com.xmu.freight.domain.ProductDto;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.standardDomain.Address;
import com.xmu.freight.standardDomain.Goods;
import com.xmu.freight.standardDomain.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FreightServiceImplTest {

    @Autowired
    private FreightServiceImpl freightServiceImpl;

    @Test
    void getDefaultFreights() {
    }

    @Test
    void findDefaultFreightById() {
    }

    @Test
    void updateDefaultFreight() {
    }

    @Test
    void addDefaultFreight() {
    }

    @Test
    void deleteDefaultFreight() {
    }

    @Test
    void getSpecialFreights() {
    }

    @Test
    void findSpecialFreightById() {
    }

    @Test
    void updateSpecialFreight() {
    }

    @Test
    void addSpecialFreight() {
    }

    @Test
    void deleteSpecialFreight() {
    }

    @Test
    void getDefaultPieceFreight() {
    }

    @Test
    void findDefaultPieceFreightById() {
    }

    @Test
    void updateDefaultPieceFreight() {
    }

    @Test
    void addDefaultPieceFreight() {
    }

    @Test
    void deleteDefaultPieceFreight() {
    }

    @Test
    void getFreight() {
    }

    @Autowired
    private DefaultFreightDao defaultFreightDao;
    @Test
    void calculateByDefault() {
//        OrderItem o1=new OrderItem();
//        o1.setNumber(3);
//        OrderItem o2=new OrderItem();
//        o1.setNumber(2);
//        Goods g1=new Goods();
//        g1.setWeight(new BigDecimal(3.0));
//        Goods g2=new Goods();
//        g2.setWeight(new BigDecimal(2));
//        ProductDto p1=new ProductDto(g1,null );
//        ProductDto p2=new ProductDto(g2,null);
//        OrderItemDto od1=new OrderItemDto();
//        od1.setOrderItem(o1); od1.setProductDto(p1);
//        OrderItemDto od2=new OrderItemDto();
//        od2.setOrderItem(o2);
//        od2.setProductDto(p2);
        Address address=new Address();
        address.setProvinceId(3);
        address.setCityId(39);
        address.setCountyId(510);
       // DefaultFreightDto defaultFreightDto=defaultFreightDao.findDefaultByAddress(address);
        List<DefaultFreightDto> ha=defaultFreightDao.getDefaultFreights();

        System.out.println("  "+ha.get(0));
//        List<OrderItemDto> ol=new ArrayList<OrderItemDto>();
//        ol.add(od1); ol.add(od2);

     //   BigDecimal bd=freightServiceImpl.calculateByDefault(ol,address);
       // System.out.println("zhi:  "+bd);
    }

    @Test
    void calculateBySpecial() {
    }

    @Test
    void getMaxResult() {
    }
}