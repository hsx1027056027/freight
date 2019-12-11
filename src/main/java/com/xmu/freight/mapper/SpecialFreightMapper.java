package com.xmu.freight.mapper;

import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.SpecialFreightDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SpecialFreightMapper {

    public List<SpecialFreightDto> getSpecialFreights();

    public SpecialFreightDto findSpecialFreightById(Integer id);

    public void updateSpecialFreight(SpecialFreightDto specialFreightDto);

    public void addSpecialFreight(SpecialFreightDto specialFreightDto);

    public List<DefaultPieceFreightDto> getDefaultPieceFreight();

    public DefaultPieceFreightDto findDefaultPieceFreightById(Integer id);

    public void updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto);

    public void addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto);
}
