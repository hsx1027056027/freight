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
            initDefaultPieceInRedisFromDB();
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
    public void initSpecialInRedisFromDB(){
        List<SpecialFreightDto> specialFreightDtoList = this.getSpecialFreights();
        redisTemplate.opsForList().rightPushAll("special",specialFreightDtoList);
        System.out.println("从DB中放置特殊模板到redis");
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
            initSpecialInRedisFromDB();
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
    public void updateSpecialFreight(SpecialFreightDto specialFreightDto) {
        specialFreightMapper.updateSpecialFreight(specialFreightDto);
        List<SpecialFreightDto> specialFreightDtoList = this.getSpecialFreights();
        redisTemplate.delete("special");
        redisTemplate.opsForList().rightPushAll("special",specialFreightDtoList);
        System.out.println("同步修改redis中的特殊运费模板");
    }

    /**
     * 添加特殊运费模板
     * @param specialFreightDto  要添加的特殊模板
     * @return SpecialFreightDto 添加后的特殊模板
     */
    public void addSpecialFreight(SpecialFreightDto specialFreightDto){
        specialFreightMapper.addSpecialFreight(specialFreightDto);
        List<SpecialFreightDto> specialFreightDtoList = this.getSpecialFreights();
        redisTemplate.delete("special");
        redisTemplate.opsForList().rightPushAll("special",specialFreightDtoList);
        System.out.println("同步添加redis中的特殊运费模板");
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
     */
    public void initDefaultPieceInRedisFromDB() {
        List<DefaultPieceFreightDto> defaultPieceFreightDtoList = getDefaultPieceFreight();
        redisTemplate.opsForList().rightPushAll("defaultPiece",defaultPieceFreightDtoList);
        System.out.println("从DB中放置默认特殊运费模板到redis");
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
    public void updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto){
        specialFreightMapper.updateDefaultPieceFreight(defaultPieceFreightDto);
        List<DefaultPieceFreightDto> defaultPieceFreightDtoList = this.getDefaultPieceFreight();
        redisTemplate.delete("default");
        redisTemplate.opsForList().rightPushAll("defaultPiece",defaultPieceFreightDtoList);
        System.out.println("同步修改redis中的默认特殊运费模板");

    }

    /**
     * 添加默认特殊模板
     * @param defaultPieceFreightDto 要添加的特殊默认模板
     * @return DefaultPieceFreightDt 添加成功的特殊默认模板
     */
    public void addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto){
        specialFreightMapper.addDefaultPieceFreight(defaultPieceFreightDto);
        List<DefaultPieceFreightDto> defaultPieceFreightDtoList = this.getDefaultPieceFreight();
        redisTemplate.delete("default");
        redisTemplate.opsForList().rightPushAll("defaultPiece",defaultPieceFreightDtoList);
        System.out.println("同步新增redis中的默认特殊运费模板");
    }


}
