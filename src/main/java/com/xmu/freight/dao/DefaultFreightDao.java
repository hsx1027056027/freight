package com.xmu.freight.dao;

import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.mapper.DefaultFreightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xmu.freight.standardDomain.*;

import java.util.List;

@Repository
public class DefaultFreightDao {
    @Autowired
    DefaultFreightMapper defaultFreightMapper;

    /**
     * 根据目的地址获得一行默认模板
     * @param addressPo 目的地址
     * @return DefaultFreightDto 一行默认模板
     */
    public DefaultFreightDto findDefaultByAddress(AddressPo addressPo){
        List<DefaultFreightDto> defaultFreightDtoList = this.getDefaultFreights();
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
    }

    /**
     *新增运费模板
     * @param defaultFreightDto 新增的模板
     * @return DefaultFreightDto 新增的模板
     */
    public void addDefaultFreight(DefaultFreightDto defaultFreightDto){
        defaultFreightMapper.addDefaultFreight(defaultFreightDto);
    }


}
