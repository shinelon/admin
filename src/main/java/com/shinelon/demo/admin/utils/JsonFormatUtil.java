package com.shinelon.demo.admin.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author syq 统一返回json格式，主要用于接口使用
 *         {errorCode:返回code,errorMsg:返回msg,returnData:返回数据}
 */

public class JsonFormatUtil {

    public static final String ERRORCODE = "errorCode";
    public static final String ERRORMSG = "errorMsg";
    public static final String DATA = "returnData";

    public static final String ERRORCODE_OK = "0";
    public static final String ERRORCODE_OK_MSG = "success";
    public static final String ERRORCODE_ERROR = "99999";
    public static final String ERRORCODE_ERROR_MSG = "failure";

    public static JSONObject getFailureJson() {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_ERROR);
        jsonObj.put(ERRORMSG, ERRORCODE_ERROR_MSG);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getFailureJson(Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_ERROR);
        jsonObj.put(ERRORMSG, ERRORCODE_ERROR_MSG);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static JSONObject getFailureJson(String msg) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_ERROR);
        jsonObj.put(ERRORMSG, msg);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getFailureJson(String msg, Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_ERROR);
        jsonObj.put(ERRORMSG, msg);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static JSONObject getJson(String code, String msg, Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, code);
        jsonObj.put(ERRORMSG, msg);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static JSONObject getSuccessJson() {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_OK);
        jsonObj.put(ERRORMSG, ERRORCODE_OK_MSG);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getSuccessJson(Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_OK);
        jsonObj.put(ERRORMSG, ERRORCODE_OK_MSG);
        jsonObj.put(DATA, data);
        return jsonObj;
    }

    public static JSONObject getSuccessJson(String msg) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_OK);
        jsonObj.put(ERRORMSG, msg);
        jsonObj.put(DATA, "");
        return jsonObj;
    }

    public static JSONObject getSuccessJson(String msg, Object data) {
        JSONObject jsonObj = new JSONObject(true);
        jsonObj.put(ERRORCODE, ERRORCODE_OK);
        jsonObj.put(ERRORMSG, msg);
        jsonObj.put(DATA, data);
        return jsonObj;
    }
}
