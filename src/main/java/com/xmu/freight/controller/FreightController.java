package com.xmu.freight.controller;


import com.github.pagehelper.PageHelper;
import com.xmu.freight.domain.*;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.util.ResponseUtil;
import com.xmu.freight.standardDomain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.PUT;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("")
public class FreightController {
    @Autowired
    FreightService freightService;

//    @Autowired
//    RestTemplate restTemplate;

    /**
     * 获得运费
     * @param order
     * @return 运费
     */
    @GetMapping("/freightPrice")
    public Object getFreight(@RequestBody Order order) {
        if(order != null)
        {
            List<OrderItemDto> OrderItemDtoList =new ArrayList<OrderItemDto>();
            for (OrderItem orderItem:order.getOrderItemList())
            {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setOrderItemPo(orderItem);
                orderItemDto.getProductDto().setGoodsPo(orderItem.getProduct().getGoodsPo());
                OrderItemDtoList.add(orderItemDto);
            }
            Object retObj = ResponseUtil.ok(freightService.getFreight(OrderItemDtoList,order.getAddressObj()));
            return retObj;
        }
        else
        {
            return ResponseUtil.fail();
        }
    }

    /**
     * 管理员查看默认模板
     * @return
     */
    @GetMapping("/defaultFreight")
    public Object getDefaultFreights(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        List<DefaultFreight> defaultFreightList = new ArrayList<>();
        List<DefaultFreightDto> defaultFreightDtoList = freightService.getDefaultFreights(page,limit);
        for(DefaultFreightDto defaultFreightDto:defaultFreightDtoList)
        {
            DefaultFreight defaultFreight = new DefaultFreight(defaultFreightDto);
            defaultFreightList.add(defaultFreight);
        }
        Object retObj = ResponseUtil.ok(defaultFreightList);
        return retObj;
    }

    /**
     * 管理员新增默认模板
     * @param defaultFreightPo
     * @return
     */
    @PostMapping("/defaultFreight")
    public Object addDefaultFreights(@RequestBody DefaultFreightPo defaultFreightPo) {
        if(defaultFreightPo.validate()==true) {
            DefaultFreightDto defaultFreightDto = new DefaultFreightDto(defaultFreightPo);
            DefaultFreightDto result = freightService.addDefaultFreight(defaultFreightDto);
            if (result != null) {
                DefaultFreight temp = new DefaultFreight(result);
                Object retObj = ResponseUtil.ok(temp);
                return retObj;
            } else {
                return ResponseUtil.serious();
            }
        }
        else
        {
            return  ResponseUtil.badArgument();
        }
    }

    /**
     * 管理员修改默认模板
     * @param id
     * @param defaultFreightPo
     * @return
     */
    @PutMapping("/defaultFreight/{id}")
    public Object updateDefaultFreight(@PathVariable Integer id, @RequestBody DefaultFreightPo defaultFreightPo) {
        if(defaultFreightPo.validate()==true) {
            defaultFreightPo.setId(id);
            DefaultFreightDto defaultFreightDto = new DefaultFreightDto(defaultFreightPo);
            DefaultFreightDto result = freightService.updateDefaultFreight(defaultFreightDto);
            if (result != null) {
                DefaultFreight temp = new DefaultFreight(result);
                Object retObj = ResponseUtil.ok(temp);
                return retObj;
            } else {
                return ResponseUtil.serious();
            }
        }
        else
        {
            return ResponseUtil.badArgument();
        }
    }

    /**
     * 管理员删除默认模板
     * @param id
     * @return
     */
    @DeleteMapping("/defaultFreight/{id}")
    public Object deleteDefaultFreight(@PathVariable Integer id) {
        freightService.deleteDefaultFreight(id);
        return ResponseUtil.ok();
    }

    /**
     * 管理员查看特殊运费模板
     * @return
     */
    @GetMapping("/specialFreight")
    public Object getSpecialFreight(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        List<SpecialFreight> specialFreightList = new ArrayList<>();
        List<SpecialFreightDto> specialFreightDtoList = freightService.getSpecialFreights(page,limit);
        for(SpecialFreightDto specialFreightDto: specialFreightDtoList)
        {
            SpecialFreight specialFreight = new SpecialFreight(specialFreightDto);
            specialFreightList.add(specialFreight);
        }
        Object retObj = ResponseUtil.ok(specialFreightList);
        return retObj;
    }


    /**
     * 管理员新增特殊运费模板
     * @param specialFreightPo
     * @return
     */
    @PostMapping("/specialFreight")
    public Object addSpecialFreight(@RequestBody SpecialFreight specialFreightPo) {
        if(specialFreightPo.validate()==true) {
            SpecialFreightDto specialFreightDto = new SpecialFreightDto(specialFreightPo);
            SpecialFreightDto result = freightService.addSpecialFreight(specialFreightDto);
            if (result != null) {
                SpecialFreight temp = new SpecialFreight(result);
                Object retObj = ResponseUtil.ok(temp);
                return retObj;
            } else {
                return ResponseUtil.serious();
            }
        }
        else
        {
            return ResponseUtil.badArgument();
        }
    }

    /**
     * 管理员修改特殊运费模板
     * @param id
     * @param specialFreightPo
     * @return
     */
    @PutMapping("/specialFreight/{id}")
    public Object updateSpecialFreight(@PathVariable Integer id, @RequestBody SpecialFreight specialFreightPo) {
        if(specialFreightPo.validate()==true) {
            specialFreightPo.setId(id);
            SpecialFreightDto specialFreightDto = new SpecialFreightDto(specialFreightPo);
            SpecialFreightDto result = freightService.updateSpecialFreight(specialFreightDto);
            if (result != null) {
                SpecialFreight temp = new SpecialFreight(result);
                Object retObj = ResponseUtil.ok(temp);
                return retObj;
            } else {
                return ResponseUtil.serious();
            }
        }
        else
        {
            return ResponseUtil.badArgument();
        }
    }

    /**
     * 管理员删除特殊运费模板
     * @param id
     * @return
     */
    @DeleteMapping("/specialFreight/{id}")
    public Object deleteSpecialFreight(@PathVariable Integer id) {
        freightService.deleteSpecialFreight(id);
        return ResponseUtil.ok();
    }

    /**
     * 管理员查看默认特殊模板
     * @return
     */
    @GetMapping("/defaultPieceFreight")
    public Object getDefaultPieceFreights(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        List<DefaultPieceFreight> defaultPieceFreightList = new ArrayList<>();
        List<DefaultPieceFreightDto> defaultPieceFreightDtoList = freightService.getDefaultPieceFreight(page,limit);
        for(DefaultPieceFreightDto defaultPieceFreightDto:defaultPieceFreightDtoList)
        {
            DefaultPieceFreight defaultPieceFreight = new DefaultPieceFreight(defaultPieceFreightDto);
            defaultPieceFreightList.add(defaultPieceFreight);
        }
        Object retObj = ResponseUtil.ok(defaultPieceFreightList);
        return retObj;
    }

    /**
     * 管理员新增默认特殊模板
     * @param defaultPieceFreightPo
     * @return
     */
    @PostMapping("/defaultPieceFreight")
    public Object addDefaultPieceFreights(@RequestBody DefaultPieceFreightPo defaultPieceFreightPo) {
        if (defaultPieceFreightPo.validate()==true) {
            DefaultPieceFreightDto defaultPieceFreightDto = new DefaultPieceFreightDto(defaultPieceFreightPo);
            DefaultPieceFreightDto result = freightService.addDefaultPieceFreight(defaultPieceFreightDto);
            if (result != null) {
                DefaultPieceFreight temp = new DefaultPieceFreight(result);
                Object retObj = ResponseUtil.ok(temp);
                return retObj;
            } else {
                return ResponseUtil.serious();
            }
        }
        else
        {
            return ResponseUtil.badArgument();
        }
    }

    /**
     * 管理员修改默认模板
     * @param id
     * @param defaultPieceFreightPo
     * @return
     */
    @PutMapping("/defaultPieceFreight/{id}")
    public Object updateDefaultPieceFreight(@PathVariable Integer id,@RequestBody DefaultPieceFreightPo defaultPieceFreightPo) {
        if(defaultPieceFreightPo.validate()==true) {
            defaultPieceFreightPo.setId(id);
            DefaultPieceFreightDto defaultPieceFreightDto = new DefaultPieceFreightDto(defaultPieceFreightPo);
            DefaultPieceFreightDto result = freightService.updateDefaultPieceFreight(defaultPieceFreightDto);
            if (result != null) {
                DefaultPieceFreight temp = new DefaultPieceFreight(result);
                Object retObj = ResponseUtil.ok(temp);
                return retObj;
            } else {
                return ResponseUtil.serious();
            }
        }
        else
        {
            return ResponseUtil.badArgument();
        }
    }

    /**
     * 管理员删除默认模板
     * @param id
     * @return
     */
    @DeleteMapping("/defaultPieceFreight/{id}")
    public Object deleteDefaultPieceFreight(@PathVariable Integer id) {
        freightService.deleteDefaultPieceFreight(id);
        return ResponseUtil.ok();
    }


}
