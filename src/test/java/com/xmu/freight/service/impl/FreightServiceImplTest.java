package com.xmu.freight.service.impl;

import com.alibaba.fastjson.JSON;
import com.xmu.freight.dao.DefaultFreightDao;
import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.xmu.freight.standardDomain.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
//        OrderItemPo o1=new OrderItemPo();
//        o1.setNumber(3);
//        OrderItemPo o2=new OrderItemPo();
//        o1.setNumber(2);
//        GoodsPo g1=new GoodsPo();
//        g1.setWeight(new BigDecimal(3.0));
//        GoodsPo g2=new GoodsPo();
//        g2.setWeight(new BigDecimal(2));
//        ProductDto p1=new ProductDto(g1,null );
//        ProductDto p2=new ProductDto(g2,null);
//        OrderItemDto od1=new OrderItemDto();
//        od1.setOrderItemPo(o1); od1.setProductDto(p1);
//        OrderItemDto od2=new OrderItemDto();
//        od2.setOrderItemPo(o2);
//        od2.setProductDto(p2);
        AddressPo addressPo =new AddressPo();
        addressPo.setProvinceId(3);
        addressPo.setCityId(39);
        addressPo.setCountyId(510);
        //DefaultFreightDto defaultFreightDto=defaultFreightDao.findDefaultByAddress(addressPo);
        List<DefaultFreightDto> ha=defaultFreightDao.getDefaultFreights();

        System.out.println("  "+ha.get(0));
//        List<OrderItemDto> ol=new ArrayList<OrderItemDto>();
//        ol.add(od1); ol.add(od2);
//
//        BigDecimal bd=freightServiceImpl.calculateByDefault(ol,addressPo);
//        System.out.println("zhi:  "+bd);
    }

    @Test
    void calculateBySpecial() {
    }

    @Test
    void getMaxResult() {

    }
    }

//    @Autowired
//    RedisTemplate redisTemplate;
//    @Test
//    void haha()
//    {
//        List newList = new ArrayList();
//        newList.add("o");
//        newList.add("p");
//        newList.add("q");
//        redisTemplate.opsForList().leftPushAll("list",newList);
//        List list =  redisTemplate.opsForList().range("list",0,-1);
//        System.out.println("通过leftPushAll(K key, Collection<V> values)方法以集合的方式批量添加元素:" + list);
//    }
//}