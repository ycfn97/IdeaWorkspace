package utils;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: utils
 * ClassName: RandomNum
 *
 * @author 18729 created on date: 2020/11/3 13:41
 * @version 1.0
 * @since JDK 1.8
 */
import java.util.Random;

public class RandomNum {
    public static int getRandInt(int fromNum,int toNum){
        return fromNum + new Random().nextInt(toNum-fromNum+1);
    }
}
