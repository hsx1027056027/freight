package com.xmu.freight;

import com.xmu.freight.dao.DefaultFreightDao;
import com.xmu.freight.domain.*;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.service.impl.FreightServiceImpl;
import com.xmu.freight.standardDomain.Address;
import com.xmu.freight.standardDomain.Goods;
import com.xmu.freight.standardDomain.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FreightApplicationTests {
    @Autowired
    private FreightService freightService;

    @Autowired
    private DefaultFreightDao defaultFreightDao;
    @Test
    void contextLoads() {
        List<DefaultFreightDto> a= freightService.getDefaultFreights();
        for(DefaultFreightDto s:a)
        {
            System.out.println(s);
        }

        DefaultFreightDto t1=freightService.findDefaultFreightById(1);
        System.out.println(t1);

        freightService.deleteDefaultFreight(1);
        System.out.println("删除了"+freightService.findDefaultFreightById(1));
        t1.setBeDeleted(false);
        freightService.updateDefaultFreight(t1);
        System.out.println("回来了"+t1);


        List<SpecialFreightDto> s=freightService.getSpecialFreights();
        for(SpecialFreightDto s1:s)
        {
            System.out.println(s1);
        }

        SpecialFreightDto t2=freightService.findSpecialFreightById(1);
        System.out.println(t2);

        freightService.deleteSpecialFreight(1);
        System.out.println("删除了"+freightService.findSpecialFreightById(1));
        t2.setBeDeleted(false);
        t2=freightService.updateSpecialFreight(t2);
        System.out.println("回来了"+t2);

//        DefaultFreightDto add1=new DefaultFreightDto();
//        add1.setDestination("[1,2,3]");
//        add1=freightService.addDefaultFreight(add1);
//        System.out.println("添加了"+add1);



        List<DefaultPieceFreightDto> d=freightService.getDefaultPieceFreight();
        for(DefaultPieceFreightDto d1:d)
        {
            System.out.println(d1);
        }

        DefaultPieceFreightDto t3=freightService.findDefaultPieceFreightById(1);
        System.out.println(t3);

        freightService.deleteDefaultPieceFreight(1);
        System.out.println("删除了"+freightService.findDefaultPieceFreightById(1));
        t3.setBeDeleted(false);
        t3=freightService.updateDefaultPieceFreight(t3);
        System.out.println("回来了"+t3);

        List<DefaultFreightDto> ha=defaultFreightDao.getDefaultFreights();

        System.out.println("  "+ha.get(0));
    }

    @Autowired
    private FreightServiceImpl freightServiceImpl;
    @Test
    void testha(){
        OrderItem o1=new OrderItem();
        o1.setNumber(3);
        OrderItem o2=new OrderItem();
        o2.setNumber(2);
        Goods g1=new Goods();
        g1.setWeight(new BigDecimal(3.0));
        Goods g2=new Goods();
        g2.setWeight(new BigDecimal(2));
        ProductDto p1=new ProductDto(g1,null );
        ProductDto p2=new ProductDto(g2,null);
        OrderItemDto od1=new OrderItemDto();
        od1.setOrderItem(o1); od1.setProductDto(p1);
        OrderItemDto od2=new OrderItemDto();
        od2.setOrderItem(o2);
        od2.setProductDto(p2);
        Address address=new Address();
        address.setProvinceId(3);
        address.setCityId(39);
        address.setCountyId(510);
        //DefaultFreightDto defaultFreightDto=defaultFreightDao.findDefaultByAddress(address);

        List<OrderItemDto> ol=new ArrayList<OrderItemDto>();
        ol.add(od1); ol.add(od2);

        BigDecimal bd=freightServiceImpl.calculateByDefault(ol,address);
        System.out.println("zhi:  "+bd);
    }

}
