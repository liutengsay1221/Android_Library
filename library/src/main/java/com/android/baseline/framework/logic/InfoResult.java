package com.android.baseline.framework.logic;


import java.io.Serializable;
import java.util.List;

/**
 * 网络请求返回数据
 *
 * @author hiphonezhu@gmail.com
 * @version [Android-BaseLine, 2013-7-23]
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

    public <R> List<R> getRawListData() {
        if (data != null && data instanceof ListEntry) {
            ListEntry listEntry = (ListEntry)data;
            return  listEntry.getRows();
        } else {
            return null;
        }
    }

    public boolean isSuccess() {
        return DEFAULT_SUCCESS_CODE.equals(code);
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
