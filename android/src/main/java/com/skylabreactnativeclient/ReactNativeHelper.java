package com.skylabreactnativeclient;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ReactNativeHelper {
    /**
     * This method will convert the input of type ReadableMap into the JSONObject.
     */
    private static final String UNSUPPORTED_TYPE = "Unsupported data type";

    public static JSONObject convertMapToJson(ReadableMap value) throws JSONException {
        JSONObject properties = new JSONObject();
        if (value != null) {
            ReadableMapKeySetIterator iterator = value.keySetIterator();
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                ReadableType valueType = value.getType(key);
                switch (valueType) {
                    case Null:
                        properties.put(key, JSONObject.NULL);
                        break;
                    case Boolean:
                        properties.put(key, value.getBoolean(key));
                        break;
                    case Number:
                        properties.put(key, value.getDouble(key));
                        break;
                    case String:
                        properties.put(key, value.getString(key));
                        break;
                    case Map:
                        properties.put(key, convertMapToJson(value.getMap(key)));
                        break;
                    case Array:
                        properties.put(key, convertArrayToJson(value.getArray(key)));
                        break;
                    default:
                        throw new IllegalArgumentException(UNSUPPORTED_TYPE + valueType);
                }
            }
        }
        return properties;
    }

    /**
     * This method will convert the input of type ReadableArray into the Json
     * object.
     */
    public static JSONArray convertArrayToJson(ReadableArray value) throws JSONException {
        JSONArray properties = new JSONArray();
        for (int i = 0; i < value.size(); i++) {
            ReadableType valueType = value.getType(i);
            switch (valueType) {
                case Null:
                    properties.put(JSONObject.NULL);
                    break;
                case Boolean:
                    properties.put(value.getBoolean(i));
                    break;
                case Number:
                    properties.put(value.getDouble(i));
                    break;
                case String:
                    properties.put(value.getString(i));
                    break;
                case Map:
                    properties.put(convertMapToJson(value.getMap(i)));
                    break;
                case Array:
                    properties.put(convertArrayToJson(value.getArray(i)));
                    break;
                default:
                    throw new IllegalArgumentException(UNSUPPORTED_TYPE + valueType);
            }
        }
        return properties;
    }

    public static WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
        WritableMap map = new WritableNativeMap();
        for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
            String key = it.next();
            Object value = jsonObject.get("it");
            if (value == null) {
                map.putNull(key);
            } else if (value instanceof Boolean) {
                map.putBoolean(key, (Boolean) value);
            } else if (value instanceof Double) {
                map.putDouble(key, (Double) value);
            } else if (value instanceof Integer) {
                map.putInt(key, (Integer) value);
            } else if (value instanceof String) {
                map.putString(key, (String) value);
            } else if (value instanceof JSONObject) {
                map.putMap(key, convertJsonToMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                map.putArray(key, convertJsonToMap((JSONArray) value));
            }
        }
        return map;
    }

    public static WritableArray convertJsonToMap(JSONArray jsonArray) throws JSONException {
        WritableArray array = new WritableNativeArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value == null) {
                array.pushNull();
            } else if (value instanceof Boolean) {
                array.pushBoolean((Boolean) value);
            } else if (value instanceof Double) {
                array.pushDouble((Double) value);
            } else if (value instanceof Integer) {
                array.pushInt((Integer) value);
            } else if (value instanceof String) {
                array.pushString((String) value);
            } else if (value instanceof JSONObject) {
                array.pushMap(convertJsonToMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                array.pushArray(convertJsonToMap((JSONArray) value));
            } else {
                array.pushNull();
            }
        }
        return array;
    }
}
