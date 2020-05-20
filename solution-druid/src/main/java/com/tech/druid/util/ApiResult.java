package com.tech.druid.util;

import javax.servlet.annotation.WebFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description api json class
 * @Classname ApiResult
 * @Description 接口返回结果实体类
 */
@WebFilter
public class ApiResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 5772761622327519887L;

    public static ApiResult failure(String message) {
        ApiResult result = new ApiResult();
        result.put("result", "failure");
        result.put("msg", message);
        return result;
    }

    public static ApiResult success(String message) {
        ApiResult result = new ApiResult();
        result.put("result", "success");
        result.put("msg", message);
        return result;
    }

    public static ApiResult success(List<Map<String, Object>> list) {
        ApiResult result = new ApiResult();
        result.put("result", "success");
        result.put("data", list);
        return result;
    }

    public static ApiResult success(Map<String, Object> map) {
        ApiResult result = new ApiResult();
        result.put("result", "success");
        result.putAll(map);
        return result;
    }

    public static ApiResult success() {
        ApiResult result = new ApiResult();
        result.put("result", "success");
        return result;
    }

    @Override
    public ApiResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}






