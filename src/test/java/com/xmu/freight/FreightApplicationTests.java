package com.xmu.freight;

import com.xmu.freight.dao.DefaultFreightDao;
import com.xmu.freight.domain.*;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.service.impl.FreightServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.xmu.freight.standardDomain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FreightApplicationTests {
    @Autowired
    private FreightServiceImpl freightService;

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

        List<DefaultFreightDto> ha=defaultFreightDao.getDefaultFreightsFromRedis();

        System.out.println("  "+ha.get(0));
        System.out.println("  "+ha.get(0).getDestinationList());
    }

    @Autowired
    private FreightServiceImpl freightServiceImpl;
    @Test
    void testha(){
        OrderItemPo o1=new OrderItemPo();
        o1.setNumber(3);
        OrderItemPo o2=new OrderItemPo();
        o2.setNumber(2);
        GoodsPo g1=new GoodsPo();
        g1.setWeight(new BigDecimal(3.0));
        g1.setBeSpecial(true);
        g1.setSpecialFreightId(2);
        GoodsPo g2=new GoodsPo();
        g2.setWeight(new BigDecimal(2));
        g2.setBeSpecial(true);
        g2.setSpecialFreightId(1);
        ProductDto p1=new ProductDto(g1,null );
        ProductDto p2=new ProductDto(g2,null);
        OrderItemDto od1=new OrderItemDto();
        od1.setOrderItemPo(o1); od1.setProductDto(p1);
        OrderItemDto od2=new OrderItemDto();
        od2.setOrderItemPo(o2);
        od2.setProductDto(p2);
        AddressPo addressPo =new AddressPo();
        addressPo.setProvinceId(3);
        addressPo.setCityId(39);
        addressPo.setCountyId(510);
        //DefaultFreightDto defaultFreightDto=defaultFreightDao.findDefaultByAddress(addressPo);

        List<OrderItemDto> ol=new ArrayList<OrderItemDto>();
        ol.add(od1); ol.add(od2);

        BigDecimal bd=freightServiceImpl.getFreight(ol, addressPo);
        System.out.println("zhi:  "+bd);
    }


    @Test
    void t1()
    {
        AddressPo addressPo =new AddressPo();
        addressPo.setProvinceId(3);
        addressPo.setCityId(39);
        addressPo.setCountyId(510);
        //DefaultFreightDto defaultFreightDto=defaultFreightDao.findDefaultByAddress(addressPo);
        DefaultFreightDto ha=defaultFreightDao.findDefaultByAddress(addressPo);
        System.out.println("1:  "+ha.getDestinationList());
        System.out.println("2:  "+ha.getFirstHeavyPrice());
        System.out.println("3:  "+ha.getRequireDays());
        System.out.println(ha);
    }



}
