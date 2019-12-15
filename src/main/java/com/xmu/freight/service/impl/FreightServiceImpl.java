package com.xmu.freight.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xmu.freight.dao.DefaultFreightDao;
import com.xmu.freight.dao.SpecialFreightDao;
import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.OrderItemDto;
import com.xmu.freight.domain.SpecialFreightDto;
import com.xmu.freight.service.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import com.xmu.freight.standardDomain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ooad team
 * @Description: 运费业务逻辑
 * @Date: Created in
 * @Modified By:
 **/
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
    public List<DefaultFreightDto> getDefaultFreights(Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
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
        return defaultFreightDao.UpdateDefaultFreight(defaultFreightDto);
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
        defaultFreightDto.setBeDeleted(false);
        return defaultFreightDao.addDefaultFreight(defaultFreightDto);

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
        t.setGmtModified(LocalDateTime.now());
        return defaultFreightDao.UpdateDefaultFreight(t);
    }

    /**
     * 获得所有的特殊运费模板（未删除的）
     * @return List<SpecialFreightDto> 所有的运费模板
     */
    @Override
    public List<SpecialFreightDto> getSpecialFreights(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
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
        return specialFreightDao.updateSpecialFreight(specialFreightDto);

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
        specialFreightDto.setBeDeleted(false);
        return specialFreightDao.addSpecialFreight(specialFreightDto);
    }

    /**
     * 删除特殊模板
     * @param id  要删除的特殊运费模板
     * @return null
     */
    @Override
    public SpecialFreightDto deleteSpecialFreight(Integer id) {
        SpecialFreightDto s=new SpecialFreightDto();
        s.setId(id);
        s.setBeDeleted(true);
        s.setGmtModified(LocalDateTime.now());
        return specialFreightDao.updateSpecialFreight(s);
    }

    /**
     * 获得所有默认特殊模板（未删除的）
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    @Override
    public List<DefaultPieceFreightDto> getDefaultPieceFreight(Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
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
     * Find某个默认特殊模板
     * @param id 要dinf模板的ID
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
        return specialFreightDao.updateDefaultPieceFreight(defaultPieceFreightDto);
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
        defaultPieceFreightDto.setBeDeleted(false);
        return specialFreightDao.addDefaultPieceFreight(defaultPieceFreightDto);
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
        d.setGmtModified(LocalDateTime.now());
        return specialFreightDao.updateDefaultPieceFreight(d);
    }

    /**
     * 获得运费
     * @param orderItemDtoList
     * @param addressPo 目的地
     * @return BigDecimal运费
     */
    @Override
    public BigDecimal getFreight(List<OrderItemDto> orderItemDtoList, AddressPo addressPo) {
        boolean special = false;
        List<BigDecimal> result = new ArrayList<BigDecimal>();
        //判断是否有特殊商品
        for (OrderItemDto orderItemDto : orderItemDtoList)
        {
            if (orderItemDto.getProductDto().getGoodsPo().getBeSpecial() == true)
            {
                special = true;
                break;
            }
        }
        //按照默认模板计算运费
        BigDecimal fee = calculateByDefault(orderItemDtoList, addressPo);
        result.add(fee);
        if(special == true) {
            int allGoodsNum = 0;
            //计算总件数
            for (OrderItemDto orderItemDto : orderItemDtoList)
            {
                allGoodsNum += orderItemDto.getOrderItemPo().getNumber();
            }
            for (OrderItemDto orderItemDto : orderItemDtoList) {
                if (orderItemDto.getProductDto().getGoodsPo().getBeSpecial() == true) {
                    fee = calculateBySpecial(orderItemDto.getProductDto().getGoodsPo(), addressPo,allGoodsNum);
                    result.add(fee);
                }
            }
        }
        return getMaxResult(result);
    }

    /**
     * 用默认模板计算运费
     * @param orderItemDtoList
     * @param addressPo 目的地
     * @return BigDecimal运费
     */
    public BigDecimal calculateByDefault(List<OrderItemDto> orderItemDtoList, AddressPo addressPo)
    {
        BigDecimal allGoodsWeight = new BigDecimal("0.00");
        for (OrderItemDto orderItemDto : orderItemDtoList)
        {
            BigDecimal weightPer = orderItemDto.getProductDto().getGoodsPo().getWeight();
            BigDecimal number = new BigDecimal(orderItemDto.getOrderItemPo().getNumber());
            allGoodsWeight = allGoodsWeight.add(weightPer.multiply(number));
        }
        DefaultFreightDto defaultFreightDto = defaultFreightDao.findDefaultByAddress(addressPo);
        if(defaultFreightDto !=null)
        {
            return defaultFreightDto.getDefaultFee(allGoodsWeight);
        }
        else
        {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 用特殊模板获得运费
     * @param good 特殊的商品
     * @param addressPo 目的地
     * @param allGoodsNum 商品总件数
     * @return BigDecimal运费
     */
    public BigDecimal calculateBySpecial(GoodsPo good, AddressPo addressPo, Integer allGoodsNum)
    {
        SpecialFreightDto specialFreightDto = specialFreightDao.findSpecialFreightByIdFromRedis(good.getSpecialFreightId());
        BigDecimal rate = specialFreightDao.findRateOfDefaultPieceByAddress(addressPo);
        if(rate != null && specialFreightDto!=null)
        {
            return specialFreightDto.getSpecialFee(rate,allGoodsNum);
        }
        else
        {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 获得list的最大值
     * @param result
     * @return 最大值
     */
    public BigDecimal getMaxResult(List<BigDecimal> result)
    {
        BigDecimal max = BigDecimal.ZERO;
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
