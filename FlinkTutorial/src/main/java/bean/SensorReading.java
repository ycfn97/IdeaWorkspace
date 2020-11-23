package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlinkTutorial
 * Package: day02
 * ClassName: SensorReading
 *
 * @author 18729 created on date: 2020/11/17 11:02
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SensorReading {
    private String a;
    private Long b;
    private double c;

    @Override
    public String toString() {
        return "SensorReading{" +
                "a='" + a + '\'' +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
