package com.xmu.freight.standardController;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@RestController
@RequestMapping("/admin-freight")// /wx/order

public interface FreightController {


    @GetMapping("/defaultFreights")
    public Object getDefaultFreights();


    @GetMapping("/specialFreight")
    public Object getSpecialFreight();


    @PostMapping("/defaultFreights")
    public Object addDefaultFreights(@RequestBody String body);

    @PostMapping("/specialFreight")
    public Object addSpecialFreight(@RequestBody String body);

    @DeleteMapping("/defaultFreights/{id}")
    public Object deleteDefaultFreight(@PathVariable("defaultFreightsId") String defaultFreightsId);

    @DeleteMapping("/specialFreights/{id}")
    public Object deleteSpecialFreight(@PathVariable("specialFreightsId") String specialFreightsId);

    @PutMapping("/specialFreights/{id}")
    public Object updateSpecialFreight(@PathVariable("specialFreightsId") String specialFreightsId);

    @DeleteMapping("/defaultFreights/{id}")
    public Object updateDefaultFreight(@PathVariable("defaultFreightsId") String defaultFreightsId);

    @GetMapping("/freight/{orderitemid}")
    public Object getFreight();
}