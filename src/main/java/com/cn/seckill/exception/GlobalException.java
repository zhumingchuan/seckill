package com.cn.seckill.exception;

import com.cn.seckill.result.CodeMsg;

/**
 * Created by admin on 2019/3/27.
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
