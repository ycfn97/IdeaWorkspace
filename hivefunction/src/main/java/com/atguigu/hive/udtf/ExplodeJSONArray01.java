package com.atguigu.hive.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: hivefunction
 * Package: com.atguigu.hive.udtf
 * ClassName: ExplodeJSONArray01
 *
 * @author 18729 created on date: 2020/10/31 13:59
 * @version 1.0
 * @since JDK 1.8
 */
public class ExplodeJSONArray01 extends GenericUDTF {
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //1 约束函数传入参数的个数
        if (argOIs.getAllStructFieldRefs().size() != 1) {
            throw new UDFArgumentLengthException("explode_json_array函数的参数个数只能为1...");
        }

        //2 约束函数传入参数的类型
        String typeName = argOIs.getAllStructFieldRefs().get(0).getFieldObjectInspector().getTypeName();
        if(!"string".equals(typeName)){
            throw new UDFArgumentTypeException(0,"explode_json_array函数的第1个参数的类型只能为String...");
        }

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<ObjectInspector> objectInspectors = new ArrayList<>();

        strings.add("item");
        objectInspectors.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(strings,objectInspectors);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        String s = args[0].toString();
        JSONArray jsonArray = new JSONArray(s);
        for (int i = 0; i < jsonArray.length(); i++) {
            String string = jsonArray.getString(i);
            String[] result=new String[1];
            result[0]=string;
            forward(result);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}





























