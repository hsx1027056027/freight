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


    public DefaultFreightDto findDefaultByAddress(Address address){

        return  null;
    }

    public DefaultFreightDto findDefaultByRegionId(Integer id){
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
