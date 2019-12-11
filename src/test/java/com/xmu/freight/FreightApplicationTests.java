package com.xmu.freight;

import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.SpecialFreightDto;
import com.xmu.freight.service.FreightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class FreightApplicationTests {
    @Autowired
    private FreightService freightService;

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
        t1.getDefaultFreight().setBeDeleted(false);
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
        t3.getDefaultPieceFreight().setBeDeleted(false);
        t3=freightService.updateDefaultPieceFreight(t3);
        System.out.println("回来了"+t3);
    }

}
