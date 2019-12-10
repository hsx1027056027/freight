package com.xmu.freight.controller;

import com.xmu.freight.standardController.FreightController;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@RestController
@RequestMapping("/admin-freight")// /wx/order

public class standardControllerDto implements FreightController {

    @Override
    public Object getDefaultFreights() {
            return null;
    }

    @Override
    public Object getSpecialFreight() {
        return null;
    }

    @Override
    public Object addDefaultFreights(String body) {
        return null;
    }

    @Override
    public Object addSpecialFreight(String body) {
        return null;
    }

    @Override
    public Object deleteDefaultFreight(String defaultFreightsId) {
        return null;
    }

    @Override
    public Object deleteSpecialFreight(String specialFreightsId) {
        return null;
    }

    @Override
    public Object updateSpecialFreight(String specialFreightsId) {
        return null;
    }

    @Override
    public Object updateDefaultFreight(String defaultFreightsId) {
        return null;
    }

    @Override
    public Object getFreight() {
        return null;
    }
}
