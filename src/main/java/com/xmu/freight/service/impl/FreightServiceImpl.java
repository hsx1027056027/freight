package com.xmu.freight.service.impl;

import com.xmu.freight.dao.DefaultFreightDao;
import com.xmu.freight.dao.SpecialFreightDao;
import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.OrderItemDto;
import com.xmu.freight.domain.SpecialFreightDto;
import com.xmu.freight.service.FreightService;
import com.xmu.freight.standardDomain.Address;
import com.xmu.freight.standardDomain.Goods;
import com.xmu.freight.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FreightServiceImpl implements FreightService {
    @Autowired
    DefaultFreightDao defaultFreightDao;

    @Autowired
    SpecialFreightDao specialFreightDao;

    /**
     * 获得默认运费模板列表
     * @return
     */
    @Override
    public List<DefaultFreightDto> getDefaultFreights() {
        return defaultFreightDao.getDefaultFreights();
    }

    @Override
    public DefaultFreightDto findDefaultFreightById(Integer id) {
        return defaultFreightDao.findDefaultFreightById(id);
    }

    @Override
    public DefaultFreightDto updateDefaultFreight(DefaultFreightDto defaultFreightDto) {
        defaultFreightDto.getDefaultFreight().setGmtModified(LocalDateTime.now());
        defaultFreightDao.UpdateDefaultFreight(defaultFreightDto);
        DefaultFreightDto t=defaultFreightDao.findDefaultFreightById(defaultFreightDto.getDefaultFreight().getId());
        return t;
    }


    /**
     *
     * @param defaultFreightDto
     * @return
     */
    @Override
    public DefaultFreightDto addDefaultFreight(DefaultFreightDto defaultFreightDto) {
        defaultFreightDto.getDefaultFreight().setGmtCreate(LocalDateTime.now());
        defaultFreightDto.getDefaultFreight().setGmtModified(LocalDateTime.now());
        defaultFreightDao.addDefaultFreight(defaultFreightDto);
        DefaultFreightDto t=defaultFreightDao.findDefaultFreightById(defaultFreightDto.getDefaultFreight().getId());
        return t;
    }



    @Override
    public DefaultFreightDto deleteDefaultFreight(Integer id) {
        DefaultFreightDto t=new DefaultFreightDto();
        t.getDefaultFreight().setId(id);
        t.getDefaultFreight().setBeDeleted(true);
        defaultFreightDao.UpdateDefaultFreight(t);
        t=defaultFreightDao.findDefaultFreightById(t.getDefaultFreight().getId());
        return null;
    }

    @Override
    public List<SpecialFreightDto> getSpecialFreights() {
        return specialFreightDao.getSpecialFreights();
    }

    @Override
    public SpecialFreightDto findSpecialFreightById(Integer id) {
        return specialFreightDao.findSpecialFreightById(id);
    }

    @Override
    public SpecialFreightDto updateSpecialFreight(SpecialFreightDto specialFreightDto) {
        specialFreightDto.setGmtModified(LocalDateTime.now());
        specialFreightDao.updateSpecialFreight(specialFreightDto);
        SpecialFreightDto s=specialFreightDao.findSpecialFreightById(specialFreightDto.getId());
        return s;
    }

    @Override
    public SpecialFreightDto addSpecialFreight(SpecialFreightDto specialFreightDto) {
        specialFreightDto.setGmtCreate(LocalDateTime.now());
        specialFreightDto.setGmtModified(LocalDateTime.now());
        specialFreightDao.addSpecialFreight(specialFreightDto);
        SpecialFreightDto s=specialFreightDao.findSpecialFreightById(specialFreightDto.getId());
        return s;
    }

    @Override
    public SpecialFreightDto deleteSpecialFreight(Integer id) {
        SpecialFreightDto s=new SpecialFreightDto();
        s.setId(id);
        s.setBeDeleted(true);
        specialFreightDao.updateSpecialFreight(s);
        s=specialFreightDao.findSpecialFreightById(s.getId());
        return null;
    }

    @Override
    public List<DefaultPieceFreightDto> getDefaultPieceFreight() {
        return specialFreightDao.getDefaultPieceFreight();
    }

    @Override
    public DefaultPieceFreightDto findDefaultPieceFreightById(Integer id) {
        return specialFreightDao.findDefaultPieceFreightById(id);
    }

    @Override
    public DefaultPieceFreightDto updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto) {
        defaultPieceFreightDto.getDefaultPieceFreight().setGmtModified(LocalDateTime.now());
        specialFreightDao.updateDefaultPieceFreight(defaultPieceFreightDto);
        DefaultPieceFreightDto d=specialFreightDao.findDefaultPieceFreightById(defaultPieceFreightDto.getDefaultPieceFreight().getId());
        return d;
    }

    @Override
    public DefaultPieceFreightDto addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto) {
        defaultPieceFreightDto.getDefaultPieceFreight().setGmtCreate(LocalDateTime.now());
        defaultPieceFreightDto.getDefaultPieceFreight().setGmtModified(LocalDateTime.now());
        specialFreightDao.addDefaultPieceFreight(defaultPieceFreightDto);
        DefaultPieceFreightDto d=specialFreightDao.findDefaultPieceFreightById(defaultPieceFreightDto.getDefaultPieceFreight().getId());
        return d;
    }

    @Override
    public DefaultPieceFreightDto deleteDefaultPieceFreight(Integer id) {
        DefaultPieceFreightDto d=new DefaultPieceFreightDto();
        d.getDefaultPieceFreight().setId(id);
        d.getDefaultPieceFreight().setBeDeleted(true);
        specialFreightDao.updateDefaultPieceFreight(d);
        d=specialFreightDao.findDefaultPieceFreightById(id);
        return d;
    }

    /**
     * 获得运费
     * @param orderItemDtoList
     * @param address 目的地
     * @return BigDecimal运费
     */
    @Override
    public BigDecimal getFreight(List<OrderItemDto> orderItemDtoList, Address address) {
        boolean special = false;
        List<BigDecimal> result = new ArrayList<BigDecimal>();
        for (OrderItemDto orderItemDto : orderItemDtoList)//判断是否有特殊商品
        {
            if (orderItemDto.getProductDto().getGoods().getBeSpecial() == true)
            {
                special = true;
                break;
            }
        }
        BigDecimal fee = calculateByDefault(orderItemDtoList,address);//按照默认模板计算运费
        result.add(fee);
        if(special == true) {
            int allGoodsNum = 0;
            for (OrderItemDto orderItemDto : orderItemDtoList)//计算总件数
            {
                allGoodsNum += orderItemDto.getOrderItem().getNumber();
            }
            for (OrderItemDto orderItemDto : orderItemDtoList) {
                if (orderItemDto.getProductDto().getGoods().getBeSpecial() == true) {
                    fee = calculateBySpecial(orderItemDto.getProductDto().getGoods(),address,allGoodsNum);
                    result.add(fee);
                }
            }
        }
        return getMaxResult(result);
    }

    /**
     * 用默认模板计算运费
     * @param orderItemDtoList
     * @param address 目的地
     * @return BigDecimal运费
     */
    public BigDecimal calculateByDefault(List<OrderItemDto> orderItemDtoList, Address address)
    {
        BigDecimal allGoodsWeight = new BigDecimal("0.00");
        for (OrderItemDto orderItemDto : orderItemDtoList)
        {
            allGoodsWeight = allGoodsWeight.add(orderItemDto.getProductDto().getGoods().getWeight().multiply(new BigDecimal(orderItemDto.getOrderItem().getNumber())));
        }
        DefaultFreightDto defaultFreightDto = defaultFreightDao.findDefaultByAddress(address);
        return defaultFreightDto.getDefaultFee(allGoodsWeight);
    }

    /**
     * 用特殊模板获得运费
     * @param good 特殊的商品
     * @param address 目的地
     * @param allGoodsNum 商品总件数
     * @return BigDecimal运费
     */
    public BigDecimal calculateBySpecial(Goods good, Address address,Integer allGoodsNum)
    {
        SpecialFreightDto specialFreightDto = specialFreightDao.findSpecialFreightById(good.getSpecialFreightId());
        BigDecimal rate = specialFreightDao.findRateOfDefaultPieceByAddress(address);
        return specialFreightDto.getSpecialFee(rate,allGoodsNum);
    }

    /**
     * 获得list的最大值
     * @param result
     * @return 最大值
     */
    public BigDecimal getMaxResult(List<BigDecimal> result)
    {
        BigDecimal max = new BigDecimal("0.00");
        for(BigDecimal x: result)
        {
            if(x.compareTo(max) > 0)
            {
                max = x;
            }
        }
        return max;
    }




}
