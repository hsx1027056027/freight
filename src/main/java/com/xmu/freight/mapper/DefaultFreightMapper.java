package com.xmu.freight.mapper;

import com.xmu.freight.domain.DefaultFreightDto;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Component
public interface DefaultFreightMapper {

    /**
     * 获得默认运费模板列表
     * @return List<DefaultFreightDto>模板列表
     */
    public List<DefaultFreightDto> getDefaultFreights();

    /**
     * 通过id查看运费模板
     * @param id 模板Id
     * @return DefaultFreightDto 默认模板
     */
    public DefaultFreightDto findDefaultFreightById(Integer id);

    /**
     *修改默认运费模板
     * @param defaultFreightDto 新的模板
     * @return  DefaultFreightDto修改后的该模板
     */
    public void updateDefaultFreight(DefaultFreightDto defaultFreightDto);

    /**
     *新增运费模板
     * @param defaultFreightDto 新增的模板
     * @return DefaultFreightDto 新增的模板
     */
    public void addDefaultFreight(DefaultFreightDto defaultFreightDto);

}
