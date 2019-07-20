package com.yui.hjl.love.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回结果
 *
 * @author XuZhuohao
 * @date 2019-07-21 1:00
 */
@Data
@Accessors(chain = true)
public class ResponseBean {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;


    public static ResponseBean success(String message, Object data){
        return new ResponseBean().setSuccess(true).setMessage(message).setData(data);
    }
    public static ResponseBean fail(String message, Object data){
        return new ResponseBean().setSuccess(false).setMessage(message).setData(data);
    }
    public static ResponseBean success(Object data){
        return new ResponseBean().setSuccess(true).setMessage("成功！").setData(data);
    }
    public static ResponseBean fail(Object data){
        return new ResponseBean().setSuccess(false).setMessage("失败！").setData(data);
    }
}
