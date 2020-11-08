package com.atguigu.gmallpublisher.mapper;

import java.util.List;
import java.util.Map;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: com.atguigu.gmallpublisher.mapper
 * ClassName: OrderMapper
 *
 * @author 18729 created on date: 2020/11/7 12:32
 * @version 1.0
 * @since JDK 1.8
 */
public interface OrderMapper {
    //1 查询当日交易额总数
    public Double selectOrderAmountTotal(String date);

    //2 查询当日交易额分时明细
    public List<Map> selectOrderAmountHourMap(String date);

}
