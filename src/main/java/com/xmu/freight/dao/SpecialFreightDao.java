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

    public BigDecimal findDefaultPiece(Address address)
    {

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
