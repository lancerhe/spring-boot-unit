package com.crackedzone.www.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * Package com.crackedzone.www.core.util
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
public class HttpResponseUtils {

    private static String KEY_CODE = "code";

    private static String KEY_MESSAGE = "message";

    private Map<String, Object> responseBodyMap = new HashMap<>();

    public HttpResponseUtils put(String key, Object value) {
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

    public static HttpResponseUtils success() {
        return new HttpResponseUtils().put(KEY_CODE, HttpResponseCode.SUCCESS);
    }

    public static HttpResponseUtils success(String message) {
        return new HttpResponseUtils().put(KEY_CODE, HttpResponseCode.SUCCESS).put(KEY_MESSAGE, message);
    }

    public static HttpResponseUtils failed(int code, String message) {
        return new HttpResponseUtils().put(KEY_CODE, code).put(KEY_MESSAGE, message);
    }

    public static class HttpResponseCode {
        public final static int SUCCESS        = 2000;
        public final static int LOGIN_FAILED   = 4001;
        public final static int IDTOKEN_FAILED = 4002;
        public final static int COMMON_FAILED  = 4003;
    }
}
