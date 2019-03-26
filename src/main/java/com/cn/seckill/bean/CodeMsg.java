package com.cn.seckill.bean;

/**
 * Created by zhumc on 2019/3/25.
 */
public class CodeMsg {
    private int code;
    private String msg;

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static CodeMsg SUCCESS = new CodeMsg(0,"成功");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
}
