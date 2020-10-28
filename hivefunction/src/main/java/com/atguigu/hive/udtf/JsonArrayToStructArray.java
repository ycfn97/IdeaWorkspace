package com.atguigu.hive.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonArrayToStructArray extends GenericUDF {

    private ArrayList<ArrayList<Object>> array = new ArrayList<>();
    private ArrayList<Object> struct = new ArrayList<>();

    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {


        ArrayList<String> fieldNames = new ArrayList<>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<>();

        for (int i = 0; i < arguments.length; i++) {
            if (i == 0) {
                continue;
            }
            if (!(arguments[i] instanceof ConstantObjectInspector)) {
                throw new UDFArgumentException("argument " + (i + 1) + "to json_array_to_struct_array must be a constant STRING");
            }
            String field = ((ConstantObjectInspector) arguments[i]).getWritableConstantValue().toString();
            String[] split = field.split(":");
            fieldNames.add(split[0]);
            switch (split[1]) {
                case "string":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
                    break;
                case "boolean":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaBooleanObjectInspector);
                    break;
                case "tinyint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaByteObjectInspector);
                    break;
                case "smallint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaShortObjectInspector);
                    break;
                case "int":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector);
                    break;
                case "bigint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaLongObjectInspector);
                    break;
                case "float":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaFloatObjectInspector);
                    break;
                case "double":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaDoubleObjectInspector);
                    break;
                default:
                    throw new UDFArgumentException("json_array_to_struct_array does not support type :" + split[1]);
            }

        }

        return ObjectInspectorFactory.getStandardListObjectInspector(ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs));
    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {

        array.clear();

        if (arguments[0].get() == null) {
            return null;
        }

        String line = arguments[0].get().toString();

        JSONArray jsonArray = new JSONArray(line);


        for (int i = 0; i < jsonArray.length(); i++) {
            struct.clear();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                struct.add(jsonObject.get(keys.next()));
            }
            array.add(struct);

        }
        return array;
    }

    @Override
    public String getDisplayString(String[] children) {
        return getStandardDisplayString("json_array_to_struct_array", children);
    }
}