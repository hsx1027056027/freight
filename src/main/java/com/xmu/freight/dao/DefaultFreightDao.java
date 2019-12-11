package com.xmu.freight.dao;

import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.mapper.DefaultFreightMapper;
import com.xmu.freight.standardDomain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultFreightDao {
    @Autowired
    DefaultFreightMapper defaultFreightMapper;

    /**
     * 根据目的地址获得一行默认模板
     * @param address 目的地址
     * @return DefaultFreightDto 一行默认模板
     */
    public DefaultFreightDto findDefaultByAddress(Address address){
        List<DefaultFreightDto> defaultFreightDtoList = this.getDefaultFreights();
        DefaultFreightDto result = this.findDefaultByRegionId(defaultFreightDtoList,address.getProvinceId());
        if(result != null)
        {
            return result;
        }
        else
        {
            result = this.findDefaultByRegionId(defaultFreightDtoList,address.getCityId());
            if(result !=null)
            {
                return result;
            }
            else
            {
                result = this.findDefaultByRegionId(defaultFreightDtoList,address.getCountyId());
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
    public DefaultFreightDto findDefaultByRegionId(List<DefaultFreightDto> defaultFreightDtoList,Integer id){
        for(DefaultFreightDto defaultFreightDto:defaultFreightDtoList)
        {
            for(int regionId : defaultFreightDto.getDestination())
            {
                if(regionId == id)
                {
                    return  defaultFreightDto;
                }

            }
        }
        return null;
    }

    public List<DefaultFreightDto> getDefaultFreights(){
        return defaultFreightMapper.getDefaultFreights();
    }

    public DefaultFreightDto findDefaultFreightById(Integer id){
        return defaultFreightMapper.findDefaultFreightById(id);
    }


    public void UpdateDefaultFreight(DefaultFreightDto defaultFreightDto){
        defaultFreightMapper.updateDefaultFreight(defaultFreightDto);
    }

    public void addDefaultFreight(DefaultFreightDto defaultFreightDto){
        defaultFreightMapper.addDefaultFreight(defaultFreightDto);
    }


}
