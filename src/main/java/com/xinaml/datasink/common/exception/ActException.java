/**
 * action层 自定义异常
 *
 * @author lgq
 * @date 2018/4/15
 **/
package com.xinaml.datasink.common.exception;

public class ActException extends Exception {
    public ActException(String msg) {
        super(msg);
    }

    public ActException() {
        super();
    }

    public ActException(String msg, int code) {
        super(code + "@" + msg);
    }
}
