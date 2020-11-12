package com.atguigu.gmallpublisher.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PublisherService {

    public Integer getDauTotal(String date);

    public Map getDauTotalHourMap(String date);

    public Double getOrderAmount(String date);

    public Map getOrderAmountHour(String date);

    Integer getDeviceTotal(String date);

    public List<Map> selectDeviceTotalHourMap(String date);

    public String getSaleDetail(String date, int startpage, int size, String keyword) throws IOException, IOException;
}
