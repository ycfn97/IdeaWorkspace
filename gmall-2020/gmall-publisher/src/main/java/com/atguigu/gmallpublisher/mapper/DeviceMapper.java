package com.atguigu.gmallpublisher.mapper;

import java.util.List;
import java.util.Map;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: com.atguigu.gmallpublisher.mapper
 * ClassName: DeviceMapper
 *
 * @author 18729 created on date: 2020/11/9 1:03
 * @version 1.0
 * @since JDK 1.8
 */
public interface DeviceMapper {
    public Integer selectDeviceTotal(String date);

    public List<Map> selectDeviceTotalHourMap(String date);
}
