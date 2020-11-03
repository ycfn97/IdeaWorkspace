//package com.example.gmalllogger;
//
///**
// * Copyright(c) 2020-2021 sparrow All Rights Reserved
// * Project: gmall-2020
// * Package: com.example.gmalllogger
// * ClassName: LoggerController
// *
// * @author 18729 created on date: 2020/11/3 15:39
// * @version 1.0
// * @since JDK 1.8
// */
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import gmall-common.constant.GmallConstants;
//
//@Slf4j
//@RestController //Controller+responsebody
//public class LoggerController {
//
//    @Autowired
//    KafkaTemplate<String,String> kafkaTemplate;
//
//    @PostMapping("log")
//    public String doLog(@RequestParam("logString") String logString ){
//
//        // 0 补充时间戳
//        JSONObject jsonObject = JSON.parseObject(logString);
//        jsonObject.put("ts",System.currentTimeMillis());
//        // 1 落盘 file
//        String jsonString = jsonObject.toJSONString();
//        log.info(jsonString);
//
//        // 2 推送到kafka
//        if( "startup".equals(jsonObject.getString("type"))){
//            kafkaTemplate.send(GmallConstants.KAFKA_TOPIC_STARTUP,jsonString);
//        }else{
//            kafkaTemplate.send(GmallConstants.KAFKA_TOPIC_EVENT,jsonString);
//        }
//
//        return "success";
//    }
//}
//
