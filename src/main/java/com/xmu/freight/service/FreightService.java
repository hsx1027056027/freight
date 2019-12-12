package com.xmu.freight.service;

import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.OrderItemDto;
import com.xmu.freight.domain.SpecialFreightDto;
import org.springframework.stereotype.Service;
import com.xmu.freight.standardDomain.*;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface FreightService {


    public List<DefaultFreightDto> getDefaultFreights();

    public List<DefaultFreightDto> getDefaultFreightsAll();

    public DefaultFreightDto findDefaultFreightById(Integer id);

    public DefaultFreightDto updateDefaultFreight(DefaultFreightDto defaultFreightDto);

    public DefaultFreightDto addDefaultFreight(DefaultFreightDto defaultFreightDto);

    public DefaultFreightDto deleteDefaultFreight(Integer id);

    public List<SpecialFreightDto> getSpecialFreights();

    public List<SpecialFreightDto> getSpecialFreightsAll();

    public SpecialFreightDto findSpecialFreightById(Integer id);

    public SpecialFreightDto updateSpecialFreight(SpecialFreightDto specialFreightDto);

    public SpecialFreightDto addSpecialFreight(SpecialFreightDto specialFreightDto);

    public SpecialFreightDto deleteSpecialFreight(Integer id);

    public List<DefaultPieceFreightDto> getDefaultPieceFreight();

    public List<DefaultPieceFreightDto> getDefaultPieceFreightAll();

    public DefaultPieceFreightDto findDefaultPieceFreightById(Integer id);

    public DefaultPieceFreightDto updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto);

    public DefaultPieceFreightDto addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto);

    public DefaultPieceFreightDto deleteDefaultPieceFreight(Integer id);

    BigDecimal getFreight(List<OrderItemDto> orderItemDtooList, AddressPo addressPo);
}
