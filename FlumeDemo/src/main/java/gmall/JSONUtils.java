package gmall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: FlumeDemo
 * Package: gmall
 * ClassName: JSONUtils
 *
 * @author 18729 created on date: 2020/10/10 8:56
 * @version 1.0
 * @since JDK 1.8
 */
public class JSONUtils {
    public static boolean isJSONValidate(String log){
        try {
            JSON.parse(log);
            return true;
        }catch (JSONException e){
            return false;
        }
    }
}
