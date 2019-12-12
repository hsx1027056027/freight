package com.xmu.freight.dao;

import com.alibaba.fastjson.JSON;
import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.mapper.DefaultFreightMapper;
import com.xmu.freight.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.xmu.freight.standardDomain.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class DefaultFreightDao {
    @Autowired
    DefaultFreightMapper defaultFreightMapper;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 根据目的地址获得一行默认模板
     * @param addressPo 目的地址
     * @return DefaultFreightDto 一行默认模板
     */
    public DefaultFreightDto findDefaultByAddress(AddressPo addressPo){
        List<DefaultFreightDto> defaultFreightDtoList = this.getDefaultFreightsFromRedis();
        //redis中无默认模板或过期
        if(defaultFreightDtoList.toString().equals("[]") )
        {
            System.out.println("redis中无默认模板或过期");
            initDefaultInRedisFromDB();
            defaultFreightDtoList = this.getDefaultFreightsFromRedis();
        }
        else
        {
            System.out.println("从redis中获取默认模板");
        }
        DefaultFreightDto result = this.findDefaultByRegionId(defaultFreightDtoList, addressPo.getProvinceId());
        if(result != null)
        {
            return result;
        }
        else
        {
            result = this.findDefaultByRegionId(defaultFreightDtoList, addressPo.getCityId());
            if(result !=null)
            {
                return result;
            }
            else
            {
                result = this.findDefaultByRegionId(defaultFreightDtoList, addressPo.getCountyId());
                return result;
            }
        }
    }

    /**
     * 根据地区id获得一行默认模板
     * @param defaultFreightDtoList 全部的默认模板
     * @param id 地区id
     * @return DefaultFreightDto 一行默认模板
     */
    public DefaultFreightDto findDefaultByRegionId(List<DefaultFreightDto> defaultFreightDtoList, Integer id){
        for(DefaultFreightDto defaultFreightDto:defaultFreightDtoList)
        {
            for(int regionId : defaultFreightDto.getDestinationList())
            {
                if(regionId == id)
                {
                    return  defaultFreightDto;
                }
            }
        }
        return null;
    }

    /**
     * 获得默认运费模板列表(未删除的）
     * @return List<DefaultFreightDto>模板列表
     */
    public List<DefaultFreightDto> getDefaultFreights(){
        return defaultFreightMapper.getDefaultFreights();
    }

    /**
     * 从redis中获得默认运费模板列表(未删除的）
     * @return
     */
    public List<DefaultFreightDto> getDefaultFreightsFromRedis(){
        return redisTemplate.opsForList().range("default",0,-1);
    }

    /**
     * 从DB中放置默认运费模板到redis
     */
    public void initDefaultInRedisFromDB(){
        List<DefaultFreightDto> defaultFreightDtoList = this.getDefaultFreights();
        redisTemplate.opsForList().rightPushAll("default",defaultFreightDtoList);
        System.out.println("从DB中放置默认运费模板到redis");
    }


    /**
     * 获得默认运费模板列表（所有的）
     * @return List<DefaultFreightDto>模板列表
     */
    public List<DefaultFreightDto> getDefaultFreightsAll(){
        return defaultFreightMapper.getDefaultFreights();
    }

    /**
     * 通过id查看运费模板
     * @param id 模板Id
     * @return DefaultFreightDto 默认模板
     */
    public DefaultFreightDto findDefaultFreightById(Integer id){
        return defaultFreightMapper.findDefaultFreightById(id);
    }


    /**
     *修改默认运费模板
     * @param defaultFreightDto 新的模板
     * @return  DefaultFreightDto修改后的该模板
     */
    public void UpdateDefaultFreight(DefaultFreightDto defaultFreightDto){
        defaultFreightMapper.updateDefaultFreight(defaultFreightDto);
        List<DefaultFreightDto> defaultFreightDtoList = this.getDefaultFreights();
        System.out.println(defaultFreightDtoList);
        redisTemplate.delete("default");
        redisTemplate.opsForList().rightPushAll("default",defaultFreightDtoList);
        //redisTemplate.opsForValue().getAndSet("default",defaultFreightDtoList);
        System.out.println("同步修改redis中的默认运费模板");
    }

    /**
     *新增运费模板
     * @param defaultFreightDto 新增的模板
     * @return DefaultFreightDto 新增的模板
     */
    public void addDefaultFreight(DefaultFreightDto defaultFreightDto){
        defaultFreightMapper.addDefaultFreight(defaultFreightDto);
        List<DefaultFreightDto> defaultFreightDtoList = this.getDefaultFreights();
        redisTemplate.delete("default");
        redisTemplate.opsForList().rightPushAll("default",defaultFreightDtoList);
        //redisTemplate.opsForValue().getAndSet("default",defaultFreightDtoList);
        System.out.println("同步新增redis中的默认运费模板");
    }


}
