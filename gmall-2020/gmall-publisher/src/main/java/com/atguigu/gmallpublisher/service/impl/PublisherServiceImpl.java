package com.atguigu.gmallpublisher.service.impl;

import com.atguigu.gmallpublisher.mapper.DauMapper;
import com.atguigu.gmallpublisher.mapper.DeviceMapper;
import com.atguigu.gmallpublisher.mapper.OrderMapper;
import com.atguigu.gmallpublisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private DauMapper dauMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Integer getDauTotal(String date) {
        return dauMapper.selectDauTotal(date);
    }

    @Override
    public Map getDauTotalHourMap(String date) {
        //1.查询Phoenix获取数据
        List<Map> list = dauMapper.selectDauTotalHourMap(date);
        //2.创建Map用于存放数据
        HashMap<String, Long> result = new HashMap<>();
        //3.遍历list
        for (Map map : list) {
            result.put((String) map.get("LH"), (Long) map.get("CT"));
        }
        //4.返回数据
        return result;
    }

    @Override
    public Double getOrderAmount(String date) {
        return orderMapper.selectOrderAmountTotal(date);
    }

    @Override
    public Map getOrderAmountHour(String date) {
        List<Map> mapList = orderMapper.selectOrderAmountHourMap(date);
        Map orderAmountHourMap=new HashMap();
        for (Map map : mapList) {
            orderAmountHourMap.put(map.get("CREATE_HOUR"), map.get("SUM_AMOUNT"));
        }
        return orderAmountHourMap;
    }

    @Override
    public Integer getDeviceTotal(String date) {
        return deviceMapper.selectDeviceTotal(date);
    }

    @Override
    public List<Map> selectDeviceTotalHourMap(String date) {
        return null;
    }
}
