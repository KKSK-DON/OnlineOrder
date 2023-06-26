package com.onlineorder.onlineOrder.controller;

import com.onlineorder.onlineOrder.entity.Customer;
import com.onlineorder.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SignUpController {
    private CustomerService customerService;

    @Autowired
    public SignUpController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)//给前端返回的header 没有默认返回200 可以省略 除非一些特殊case,想要返回
    public void signUp(@RequestBody Customer customer) {
        customerService.signUp(customer);
    }

}
