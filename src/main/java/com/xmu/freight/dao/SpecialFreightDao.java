package com.xmu.freight.dao;

import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.SpecialFreightDto;
import com.xmu.freight.mapper.SpecialFreightMapper;
import com.xmu.freight.standardDomain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class SpecialFreightDao {
    @Autowired
    SpecialFreightMapper specialFreightMapper;

    /**
     * 根据目的地址获得默认比率
     * @param address 目的地址
     * @return BigDecimal 比率
     */
    public BigDecimal findRateOfDefaultPieceByAddress(Address address)
    {
        List<DefaultPieceFreightDto> defaultPieceFreightDtoList = this.getDefaultPieceFreight();
        BigDecimal result = this.findRateOfDefaultPieceByRegionId(defaultPieceFreightDtoList,address.getProvinceId());
        if(result != null)
        {
            return result;
        }
        else
        {
            result = this.findRateOfDefaultPieceByRegionId(defaultPieceFreightDtoList,address.getCityId());
            if(result !=null)
            {
                return result;
            }
            else
            {
                result = this.findRateOfDefaultPieceByRegionId(defaultPieceFreightDtoList,address.getCountyId());
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
    public BigDecimal findRateOfDefaultPieceByRegionId(List<DefaultPieceFreightDto> defaultPieceFreightDtoList,Integer id)
    {
        for(DefaultPieceFreightDto defaultPieceFreightDto:defaultPieceFreightDtoList)
        {
            for(int regionId : defaultPieceFreightDto.getDestination())
            {
                if(regionId == id)
                {
                    return  defaultPieceFreightDto.getDefaultPieceFreight().getUnitRate();
                }

            }
        }
        return null;
    }


    public List<SpecialFreightDto> getSpecialFreights(){
        return specialFreightMapper.getSpecialFreights();
    }

    public SpecialFreightDto findSpecialFreightById(Integer id){
        return specialFreightMapper.findSpecialFreightById(id);
    }


    public  void updateSpecialFreight(SpecialFreightDto specialFreightDto) {
        specialFreightMapper.updateSpecialFreight(specialFreightDto);
    }

    public void addSpecialFreight(SpecialFreightDto specialFreightDto){
        specialFreightMapper.addSpecialFreight(specialFreightDto);
    }

    public List<DefaultPieceFreightDto> getDefaultPieceFreight(){
        return specialFreightMapper.getDefaultPieceFreight();
    }

    public DefaultPieceFreightDto findDefaultPieceFreightById(Integer id)
    {
        return specialFreightMapper.findDefaultPieceFreightById(id);
    }

    public void updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto){
        specialFreightMapper.updateDefaultPieceFreight(defaultPieceFreightDto);
    }

    public void addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto){
        specialFreightMapper.addDefaultPieceFreight(defaultPieceFreightDto);
    }


}
