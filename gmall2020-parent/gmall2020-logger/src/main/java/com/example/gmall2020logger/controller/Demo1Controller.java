package com.example.gmall2020logger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall2020-parent
 * Package: com.example.gmall2020logger.controller
 * ClassName: Demo1Controller
 *
 * @author 18729 created on date: 2020/11/12 15:13
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class Demo1Controller {
    @ResponseBody
    @RequestMapping("testDemo")
    public String testDemo(){
        return "hello demo";
    }
}

