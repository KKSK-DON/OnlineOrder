package com.onlineorder.onlineOrder.controller;

import com.onlineorder.onlineOrder.entity.MenuItem;
import com.onlineorder.onlineOrder.entity.Restaurant;
import com.onlineorder.onlineOrder.service.MenuInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuInfoController {

    private final MenuInfoService menuInfoService;

    public MenuInfoController(MenuInfoService menuInfoService) {
        this.menuInfoService = menuInfoService;
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody// return is body
    public List<MenuItem> getMenuItems(@PathVariable("restaurantId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody// = response.write
    public List<Restaurant> getRestaurants() {
        return menuInfoService.getRestaurants();
    }
}

