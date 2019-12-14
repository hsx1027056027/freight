package com.xmu.freight.dao;

import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.SpecialFreightDto;
import com.xmu.freight.mapper.SpecialFreightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.xmu.freight.standardDomain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class SpecialFreightDao {
    @Autowired
    SpecialFreightMapper specialFreightMapper;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 根据目的地址获得默认比率
     * @param addressPo 目的地址
     * @return BigDecimal 比率
     */
    public BigDecimal findRateOfDefaultPieceByAddress(AddressPo addressPo)
    {
        List<DefaultPieceFreightDto> defaultPieceFreightDtoList = this.getDefaultPieceFreightFromRedis();
        //redis中无模板或过期
        if(defaultPieceFreightDtoList.toString().equals("[]"))
        {
            System.out.println("redis中无默认特殊模板或过期");
            if(initDefaultPieceInRedisFromDB()==0)
            {
                //DB中无数据
                return null;
            }
            defaultPieceFreightDtoList = this.getDefaultPieceFreightFromRedis();
        }
        else
        {
            System.out.println("从redis中获取默认特殊模板");
        }
        BigDecimal result = this.findRateOfDefaultPieceByRegionId(defaultPieceFreightDtoList, addressPo.getProvinceId());
        if(result != null)
        {
            return result;
        }
        else
        {
            result = this.findRateOfDefaultPieceByRegionId(defaultPieceFreightDtoList, addressPo.getCityId());
            if(result !=null)
            {
                return result;
            }
            else
            {
                result = this.findRateOfDefaultPieceByRegionId(defaultPieceFreightDtoList, addressPo.getCountyId());
                return result;
            }
        }
    }

    /**
     * 根据地区Id获得比率
     * @param defaultPieceFreightDtoList 全部的默认特殊模板
     * @param id 地区id
     * @return BigDecimal 比率
     */
    public BigDecimal findRateOfDefaultPieceByRegionId(List<DefaultPieceFreightDto> defaultPieceFreightDtoList, Integer id)
    {
        for(DefaultPieceFreightDto defaultPieceFreightDto:defaultPieceFreightDtoList)
        {
            for(int regionId : defaultPieceFreightDto.getDestinationList())
            {
                if(regionId == id)
                {
                    return  defaultPieceFreightDto.getUnitRate();
                }

            }
        }
        return null;
    }

    /**
     * 获得所有的特殊运费模板(未删除的）
     * @return List<SpecialFreightDto> 所有的运费模板
     */
    public List<SpecialFreightDto> getSpecialFreights(){
        return specialFreightMapper.getSpecialFreights();
    }

    /**
     * 从redis中获得特殊运费模板(未删除的）
     * @return
     */
    public List<SpecialFreightDto> getSpecialFreightsFromRedis(){
        return redisTemplate.opsForList().range("special",0,-1);
    }

    /**
     * 从DB中放置特殊模板到redis
     */
    public int initSpecialInRedisFromDB(){
        List<SpecialFreightDto> specialFreightDtoList = this.getSpecialFreights();
        if(redisTemplate.hasKey("special") ==true)
        {
            redisTemplate.delete("special");
        }
        if(specialFreightDtoList.toString().equals("[]"))
        {
            System.out.println("DB中没被删除的特殊运费模板为空");
            return 0;
        }
        else
        {
            redisTemplate.opsForList().rightPushAll("special",specialFreightDtoList);
            System.out.println("从DB中放置特殊模板到redis");
            return 1;
        }
    }


    /**
     * 获得所有的特殊运费模板(所有的）
     * @return List<SpecialFreightDto> 所有的运费模板
     */
    public List<SpecialFreightDto> getSpecialFreightsAll(){
        return specialFreightMapper.getSpecialFreightsAll();
    }

    /**
     *通过Id获得特殊运费模板
     * @param id
     * @return SpecialFreightDto
     */
    public SpecialFreightDto findSpecialFreightById(Integer id){
        return specialFreightMapper.findSpecialFreightById(id);
    }

    /**
     * 通过Id从Redis中获得特殊运费模板
     * @param id
     * @return SpecialFreightDto
     */
    public SpecialFreightDto findSpecialFreightByIdFromRedis(Integer id){
        List<SpecialFreightDto> specialFreightDtoList = this.getSpecialFreightsFromRedis();
        //redis中无模板或过期
        if(specialFreightDtoList.toString().equals("[]"))
        {
            System.out.println("redis中无特殊模板或过期");
            if(initSpecialInRedisFromDB() == 0)
            {
                //DB中无数据
                return null;
            }
            specialFreightDtoList = this.getSpecialFreightsFromRedis();
        }
        else
        {
            System.out.println("从redis中获取特殊模板");
        }
        for(SpecialFreightDto specialFreightDto:specialFreightDtoList)
        {
            if(id.equals(specialFreightDto.getId()) )
            {
                return specialFreightDto;
            }
        }
        return null;
    }

    /**
     *  修改特殊运费模板
     * @param specialFreightDto 要修改的特殊模板
     * @return SpecialFreightDto
     */
    public SpecialFreightDto updateSpecialFreight(SpecialFreightDto specialFreightDto) {
        int success = specialFreightMapper.updateSpecialFreight(specialFreightDto);
        if(success ==0)
        {
            return null;
        }
        else
        {
            System.out.print("同步修改redis中的特殊运费模板：   ");
            initSpecialInRedisFromDB();
            return specialFreightMapper.findSpecialFreightById(specialFreightDto.getId());
        }
    }

    /**
     * 添加特殊运费模板
     * @param specialFreightDto  要添加的特殊模板
     * @return SpecialFreightDto 添加后的特殊模板
     */
    public SpecialFreightDto addSpecialFreight(SpecialFreightDto specialFreightDto){
        int success = specialFreightMapper.addSpecialFreight(specialFreightDto);
        if(success ==0)
        {
            return null;
        }
        else
        {
            System.out.print("同步添加redis中的特殊运费模板");
            initSpecialInRedisFromDB();
            return specialFreightMapper.findSpecialFreightById(specialFreightDto.getId());
        }

    }

    /**
     * 获得所有默认特殊模板（未删除的）
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    public List<DefaultPieceFreightDto> getDefaultPieceFreight(){
        return specialFreightMapper.getDefaultPieceFreight();
    }

    /**
     * 从Redis中获得默认特殊模板（未删除的）
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    public List<DefaultPieceFreightDto> getDefaultPieceFreightFromRedis(){
        return redisTemplate.opsForList().range("defaultPiece",0,-1);
    }

    /**
     * 从DB中放置默认特殊运费模板到redis
     * @return 0 DB空 1 DB没空
     */
    public int initDefaultPieceInRedisFromDB() {
        List<DefaultPieceFreightDto> defaultPieceFreightDtoList = getDefaultPieceFreight();
        if(redisTemplate.hasKey("defaultPiece")==true)
        {
            redisTemplate.delete("defaultPiece");
        }
        if(defaultPieceFreightDtoList.toString().equals("[]"))
        {
            System.out.println("DB中没被删除的默认运费模板为空");
            return 0;
        }
        else
        {
            redisTemplate.opsForList().rightPushAll("defaultPiece",defaultPieceFreightDtoList);
            System.out.println("从DB中放置默认特殊运费模板到redis");
            return 1;
        }
    }


    /**
     * 获得所有默认特殊模板（所有的)
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    public List<DefaultPieceFreightDto> getDefaultPieceFreightAll(){
        return specialFreightMapper.getDefaultPieceFreightAll();
    }

    /**
     *获得某个默认特殊模板
     * @param id 要获得模板的ID
     * @return DefaultPieceFreightDt
     */
    public DefaultPieceFreightDto findDefaultPieceFreightById(Integer id)
    {
        return specialFreightMapper.findDefaultPieceFreightById(id);
    }

    /**
     * 更新默认特殊运费模板
     * @param defaultPieceFreightDto 要更新的模板
     * @return DefaultPieceFreightDt 更新后的模板
     */
    public DefaultPieceFreightDto updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto){
        int success = specialFreightMapper.updateDefaultPieceFreight(defaultPieceFreightDto);
        if(success ==0)
        {
            return null;
        }
        else
        {
            System.out.print("同步修改redis中的默认特殊运费模板:   ");
            initDefaultPieceInRedisFromDB();
            return specialFreightMapper.findDefaultPieceFreightById(defaultPieceFreightDto.getId());
        }

    }

    /**
     * 添加默认特殊模板
     * @param defaultPieceFreightDto 要添加的特殊默认模板
     * @return DefaultPieceFreightDt 添加成功的特殊默认模板
     */
    public DefaultPieceFreightDto addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto){
        int success = specialFreightMapper.addDefaultPieceFreight(defaultPieceFreightDto);
        if(success ==0)
        {
            return null;
        }
        else
        {
            System.out.print("同步新增redis中的默认特殊运费模板:   ");
            initDefaultPieceInRedisFromDB();
            return specialFreightMapper.findDefaultPieceFreightById(defaultPieceFreightDto.getId());
        }

    }


}
