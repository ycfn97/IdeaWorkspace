package value;

import java.util.Random;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkCoreTest
 * Package: value
 * ClassName: TestRandom
 *
 * @author 18729 created on date: 2020/9/23 8:37
 * @version 1.0
 * @since JDK 1.8
 */
public class TestRandom {
    public static void main(String[] args) {
        Random random = new Random(100);
//        Random random1 = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }

        System.out.println("===================================");

        Random r2 = new Random(100);
//        Random r2 = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(r2.nextInt(10));
        }

    }
}
