package com.xmu.freight.standardController;


import com.xmu.freight.standardDomain.DefaultPieceFreight;
import com.xmu.freight.standardDomain.SpecialFreight;
import com.xmu.freight.vo.OrderFreightRequestVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@RestController
@RequestMapping("/admin-freightSerice")// /wx/order

public interface FreightController {


    @GetMapping("/defaultFreights")
    public Object getDefaultFreights();


    @GetMapping("/specialFreights")
    public Object getSpecialFreight();


    @PostMapping("/defaultFreights")
    public Object addDefaultFreights(DefaultPieceFreight defaultPieceFreight);

    @PostMapping("/specialFreight")
    public Object addSpecialFreight(SpecialFreight specialFreight);

    @DeleteMapping("/defaultFreights/{id}")
    public Object deleteDefaultFreight(@PathVariable Integer id);

    @DeleteMapping("/specialFreights/{id}")
    public Object deleteSpecialFreight(@PathVariable Integer id);

    @PutMapping("/specialFreights/{id}")
    public Object updateSpecialFreight(@PathVariable Integer id, @RequestBody SpecialFreight specialFreight);

    @DeleteMapping("/defaultFreights/{id}")
    public Object updateDefaultFreight(@PathVariable Integer id, @RequestBody DefaultPieceFreight defaultPieceFreight);

    @GetMapping("/freight/{orderid}")
    public Object getFreight(@RequestBody OrderFreightRequestVo orderFreightRequestVo);
}