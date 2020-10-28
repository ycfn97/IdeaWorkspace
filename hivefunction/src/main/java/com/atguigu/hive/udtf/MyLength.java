package com.atguigu.hive.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: hivefunction
 * Package: com.atguigu.hive.udtf
 * ClassName: MyLength
 *
 * @author 18729 created on date: 2020/9/21 19:16
 * @version 1.0
 * @since JDK 1.8
 */
public class MyLength extends GenericUDF {
    /**
     * 约束函数传入参数个数，约束函数传入参数类型，约束函数返回值类型
     * @param objectInspectors
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        if (objectInspectors.length!=1){
            throw new UDFArgumentException("Input Args Num Error,You can only input one arg...");
        }
        if (!objectInspectors[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){
            throw new UDFArgumentTypeException(0,"Input Args Type Error,You can only input PRIMITIVE Type...");
        }
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
     * 函数逻辑处理方法，获取函数传入参数值的长度
     * @param deferredObjects
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        Object o=deferredObjects[0].get();
        int length=o.toString().length();
        return length;
    }

    /**
     * 返回显示字符串方法，这个方法不用管，直接返回一个空字符串
     * @param strings
     * @return
     */
    @Override
    public String getDisplayString(String[] strings) {
        return "";
    }
}
