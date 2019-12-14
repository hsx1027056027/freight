package com.xmu.freight.mapper;

import com.xmu.freight.domain.DefaultPieceFreightDto;
import com.xmu.freight.domain.SpecialFreightDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SpecialFreightMapper {

    /**
     * 获得所有的特殊运费模板(未删除的）
     * @return List<SpecialFreightDto> 所有的运费模板
     */
    public List<SpecialFreightDto> getSpecialFreights();

    /**
     * 获得所有的特殊运费模板（所有的）
     * @return List<SpecialFreightDto> 所有的运费模板
     */
    public List<SpecialFreightDto> getSpecialFreightsAll();

    /**
     *通过Id获得特殊运费模板
     * @param id
     * @return SpecialFreightDto
     */
    public SpecialFreightDto findSpecialFreightById(Integer id);


    /**
     *  修改特殊运费模板
     * @param specialFreightDto 要修改的特殊模板
     * @return 行数
     */
    public int updateSpecialFreight(SpecialFreightDto specialFreightDto);

    /**
     * 添加特殊运费模板
     * @param specialFreightDto  要添加的特殊模板
     * @return 行数
     */
    public int addSpecialFreight(SpecialFreightDto specialFreightDto);

    /**
     * 获得所有默认特殊模板(未删除的）
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    public List<DefaultPieceFreightDto> getDefaultPieceFreight();

    /**
     * 获得所有默认特殊模板（所有的）
     * @return List<DefaultPieceFreightDto>所有默认特殊模板的列表
     */
    public List<DefaultPieceFreightDto> getDefaultPieceFreightAll();

    /**
     * 删除某个默认特殊模板
     * @param id 要删除模板的ID
     * @return DefaultPieceFreightDt
     */
    public DefaultPieceFreightDto findDefaultPieceFreightById(Integer id);

    /**
     * 更新默认特殊运费模板
     * @param defaultPieceFreightDto 要更新的模板
     * @return 行数
     */
    public int updateDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto);

    /**
     * 添加默认特殊模板
     * @param defaultPieceFreightDto 要添加的特殊默认模板
     * @return 行数
     */
    public int addDefaultPieceFreight(DefaultPieceFreightDto defaultPieceFreightDto);
}
