package cn.tommyyang.tools.httptool.model;

import java.util.List;

/**
 * Created by TommyYang on 2018/2/24.
 */
public class Result {

    private Integer errorCode;

    private  String message;

    private List<User> result;

    public Result(Integer errorCode, String message, List<User> result) {
        this.errorCode = errorCode;
        this.message = message;
        this.result = result;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
