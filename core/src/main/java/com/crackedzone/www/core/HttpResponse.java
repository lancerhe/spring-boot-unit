package com.crackedzone.www.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * Package com.crackedzone.www.core.util
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
public class HttpResponse {

    private static String KEY_CODE = "code";

    private static String KEY_MESSAGE = "message";

    private Map<String, Object> responseBodyMap = new HashMap<>();

    public HttpResponse put(String key, Object value) {
        responseBodyMap.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(responseBodyMap, SerializerFeature.WriteMapNullValue);
    }

    public String toString(String dateFormat) {
        return JSON.toJSONStringWithDateFormat(responseBodyMap, dateFormat, SerializerFeature.WriteMapNullValue);
    }

    public static HttpResponse success() {
        return new HttpResponse().put(KEY_CODE, HttpResponseCode.SUCCESS);
    }

    public static HttpResponse success(String message) {
        return new HttpResponse().put(KEY_CODE, HttpResponseCode.SUCCESS).put(KEY_MESSAGE, message);
    }

    public static HttpResponse failed(int code, String message) {
        return new HttpResponse().put(KEY_CODE, code).put(KEY_MESSAGE, message);
    }
}