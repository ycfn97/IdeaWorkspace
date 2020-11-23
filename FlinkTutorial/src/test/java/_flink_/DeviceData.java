package _flink_;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: _flink_
 * ClassName: DeviceData
 *
 * @author 18729 created on date: 2020/11/19 10:24
 * @version 1.0
 * @since JDK 1.8
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备数据的数据结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class DeviceData {
    String compID;
    String machID;
    String Type;
    String gateMac;
    String operationValue;
    String operationData;
    String gatherTime;
}