package com.android.baseline.framework.logic;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 网络请求返回数据
 *
 * @author liuteng
 */
public class InfoResult<T> implements Serializable {
    // 成功Code
    public static final String DEFAULT_SUCCESS_CODE = "0";
    // App内部错误码
    public static final String INNER_ERROR_CODE = "-1";

    private String code;
    private String desc;
    private T data;

    public InfoResult(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public InfoResult(String code) {
        this.code = code;
    }

    public InfoResult() {
    }

    public boolean isSuccess() {
        return DEFAULT_SUCCESS_CODE.equals(code) || "201".equals(code)||"202".equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "InfoResult{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
