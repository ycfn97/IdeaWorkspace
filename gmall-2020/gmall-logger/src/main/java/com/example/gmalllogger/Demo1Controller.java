package com.example.gmalllogger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: controller
 * ClassName: Demo1Controller
 *
 * @author 18729 created on date: 2020/11/3 13:51
 * @version 1.0
 * @since JDK 1.8
 */

//@Controller
@RestController
public class Demo1Controller {
//    @ResponseBody
    @RequestMapping("testDemo")
    public String testDemo(){
        return "hello demo";
    }
}

