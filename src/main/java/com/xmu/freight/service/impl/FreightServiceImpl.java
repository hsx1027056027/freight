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
     * 获得默认运费模板列表（未删除的）
     * @return List<DefaultFreightDto>模板列表
     */
    @Override
    public List<DefaultFreightDto> getDefaultFreights() {
        return defaultFreightDao.getDefaultFreights();
    }

    /**
     * 获得默认运费模板列表（所有的）
     * @return List<DefaultFreightDto>模板列表
     */
    @Override
    public List<DefaultFreightDto> getDefaultFreightsAll() {
        return defaultFreightDao.getDefaultFreightsAll();
    }

    /**
     * 通过id查看运费模板
     * @param id 模板Id
     * @return DefaultFreightDto 默认模板
     */
    @Override
    public DefaultFreightDto findDefaultFreightById(Integer id) {
        return defaultFreightDao.findDefaultFreightById(id);
    }

    /**
     *修改默认运费模板
     * @param defaultFreightDto 新的模板
     * @return  DefaultFreightDto修改后的该模板
     */
    @Override
    public DefaultFreightDto updateDefaultFreight(DefaultFreightDto defaultFreightDto) {
        defaultFreightDto.setGmtModified(LocalDateTime.now());
        defaultFreightDao.UpdateDefaultFreight(defaultFreightDto);
        DefaultFreightDto t=defaultFreightDao.findDefaultFreightById(defaultFreightDto.getId());
        return t;
    }


    /**
     *新增运费模板
     * @param defaultFreightDto 新增的模板
     * @return DefaultFreightDto 新增的模板
     */
    @Override
    public DefaultFreightDto addDefaultFreight(DefaultFreightDto defaultFreightDto) {
        defaultFreightDto.setGmtCreate(LocalDateTime.now());
        defaultFreightDto.setGmtModified(LocalDateTime.now());
        defaultFreightDao.addDefaultFreight(defaultFreightDto);
        DefaultFreightDto t=defaultFreightDao.findDefaultFreightById(defaultFreightDto.getId());
        return t;
    }


    /**
     *  删除运费模板
     * @param id 要删除的模板Id
     * @return DefaultFreightDto 删除后的默认模板
     */
    @Override
    public DefaultFreightDto deleteDefaultFreight(Integer id) {
        DefaultFreightDto t=new DefaultFreightDto();
        t.setId(id);
        t.setBeDeleted(true);
        defaultFreightDao.UpdateDefaultFreight(t);
        t=defaultFreightDao.findDefaultFreightById(t.getId());
        return null;
    }

    /**
     * 获得所有的特殊运费模板（未删除的）
     * @return List<SpecialFreightDto> 所有的运费模板
     */
    @Override
    public List<SpecialFreightDto> getSpecialFreights() {
        return specialFreightDao.getSpecialFreights();
    }

    /**
     * 获得所有的特殊运费模板（所有的）
     * @return List<SpecialFreightDto> 所有的运费模板
     */
    @Override
    public List<SpecialFreightDto> getSpecialFreightsAll() {
        return specialFreightDao.getSpecialFreightsAll();
    }

    /**
     *通过Id获得特殊运费模板
     * @param id
     * @return SpecialFreightDto
     */
    @Override
    public SpecialFreightDto findSpecialFreightById(Integer id) {
        return specialFreightDao.findSpecialFreightById(id);
    }

    /**
     *  修改特殊运费模板
     * @param specialFreightDto 要修改的特殊模板
     * @return SpecialFreightDto
     */
    @Override
    public SpecialFreightDto updateSpecialFreight(SpecialFreightDto specialFreightDto) {
        specialFreightDto.setGmtModified(LocalDateTime.now());
        specialFreightDao.updateSpecialFreight(specialFreightDto);
        SpecialFreightDto s=specialFreightDao.findSpecialFreightById(specialFreightDto.getId());
        return s;
    }

    /**
     * 添加特殊运费模板
     * @param specialFreightDto  要添加的特殊模板
     * @return SpecialFreightDto 添加后的特殊模板
     */
    @Override
    public SpecialFreightDto addSpecialFreight(SpecialFreightDto specialFreightDto) {
        specialFreightDto.setGmtCreate(LocalDateTime.now());
        specialFreightDto.setGmtModified(LocalDateTime.now());
        specialFreightDao.addSpecialFreight(specialFreightDto);
        SpecialFreightDto s=specialFreightDao.findSpecialFreightById(specialFreightDto.getId());
        return s;
    }

    /**
     * 删除特殊模板
     * @param id  要删除的特殊运费模板
     * @return
     */
    @Override
    public SpecialFreightDto deleteSpecialFreight(Integer id) {
        SpecialFreightDto s=new SpecialFreightDto();
        s.setId(id);
        s.setBeDeleted(true);
        specialFreightDao.updateSpecialFreight(s);
        s=specialFreightDao.findSpecialFreightById(s.getId());
        return null;
    }

    /**
     * 获得所有默认特殊模板（未删除的）
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    @Override
    public List<DefaultPieceFreightDto> getDefaultPieceFreight() {
        return specialFreightDao.getDefaultPieceFreight();
    }


    /**
     * 获得所有默认特殊模板（所有的）
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    @Override
    public List<DefaultPieceFreightDto> getDefaultPieceFreightAll() {
        return specialFreightDao.getDefaultPieceFreightAll();
    }

    /**
     * 删除某个默认特殊模板
     * @param id 要删除模板的ID
     * @return DefaultPieceFreightDt
     */
    @Override
    public DefaultPieceFreightDto findDefaultPieceFreightById(Integer id) {
        return specialFreightDao.findDefaultPieceFreightById(id);
    }

    /**
     * 更新默认特殊运费模板
     * @param defaultPieceFreightDto 要更新的模板
     * @return DefaultPieceFreightDt 更新后的模板
     */
    @Override
    public DefaultPieceFreightDto updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto) {
        defaultPieceFreightDto.setGmtModified(LocalDateTime.now());
        specialFreightDao.updateDefaultPieceFreight(defaultPieceFreightDto);
        DefaultPieceFreightDto d=specialFreightDao.findDefaultPieceFreightById(defaultPieceFreightDto.getId());
        return d;
    }


    /**
     * 添加默认特殊模板
     * @param defaultPieceFreightDto 要添加的特殊默认模板
     * @return DefaultPieceFreightDt 添加成功的特殊默认模板
     */
    @Override
    public DefaultPieceFreightDto addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto) {
        defaultPieceFreightDto.setGmtCreate(LocalDateTime.now());
        defaultPieceFreightDto.setGmtModified(LocalDateTime.now());
        specialFreightDao.addDefaultPieceFreight(defaultPieceFreightDto);
        DefaultPieceFreightDto d=specialFreightDao.findDefaultPieceFreightById(defaultPieceFreightDto.getId());
        return d;
    }

    /**
     * 删除默认特殊模板
     * @param id 要删除的模板的Id
     * @return DefaultPieceFreightDt 删除后的默认特殊模板
     */
    @Override
    public DefaultPieceFreightDto deleteDefaultPieceFreight(Integer id) {
        DefaultPieceFreightDto d=new DefaultPieceFreightDto();
        d.setId(id);
        d.setBeDeleted(true);
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
            BigDecimal weightPer = orderItemDto.getProductDto().getGoods().getWeight();
            BigDecimal number = new BigDecimal(orderItemDto.getOrderItem().getNumber());
            allGoodsWeight = allGoodsWeight.add(weightPer.multiply(number));
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
