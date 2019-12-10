package com.xmu.freight.service;

import com.xmu.freight.domain.DefaultFreightDto;
import com.xmu.freight.domain.SpecialFreightDto;

public interface FreightService {


    DefaultFreightDto getDefaultFreights();

    SpecialFreightDto getSpecialFreight();

    DefaultFreightDto addDefaultFreights();

    SpecialFreightDto addSpecialFreight();

    int deleteDefaultFreight();

    int deleteSpecialFreight();

    SpecialFreightDto updateSpecialFreight();

    DefaultFreightDto updateDefaultFreight();

    int getFreight();
}
