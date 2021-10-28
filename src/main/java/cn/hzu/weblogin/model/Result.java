package cn.hzu.weblogin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: Result<T>
 * @description: 请求结果类
 * @author: Hzu_rang
 * @createDate: 2021/10/23
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 消息
     */
    private String message;
    /**
     * 是否操作成功
     */
    private boolean success;
    /**
     * 返回的数据主体（返回的内容）
     */
    private T data;
    /**
     * 设定结果为成功
     *
     * @param msg  消息
     * @param data 数据体
     */
    public void setResultSuccess(String msg, T data) {
        this.message = msg;
        this.success = true;
        this.data = data;
    }

    /**
     * 设定结果为失败
     *
     * @param msg 消息
     */
    public void setResultFailed(String msg) {
        this.message = msg;
        this.success = false;
        this.data = null;
    }

    public void printResult() {
        System.out.println(this.success);
        System.out.println(this.data);
        System.out.println(this.message);
    }
}