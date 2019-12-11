package com.xmu.freight.mapper;

import com.xmu.freight.domain.DefaultFreightDto;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Component
public interface DefaultFreightMapper {
    public List<DefaultFreightDto> getDefaultFreights();

    public DefaultFreightDto findDefaultFreightById(Integer id);

    public void updateDefaultFreight(DefaultFreightDto defaultFreightDto);

    public void addDefaultFreight(DefaultFreightDto defaultFreightDto);

}
