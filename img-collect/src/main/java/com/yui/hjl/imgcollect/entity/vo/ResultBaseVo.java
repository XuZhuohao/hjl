package com.yui.hjl.imgcollect.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * 返回参数vo，基础类
 *
 * @author XuZhuohao
 * @date 2018/11/15
 */
@Getter
public class ResultBaseVo extends HashMap<String, Object> {
    private Boolean isSuccess;
    private String errMsg;

    public void setSuccess(Boolean success) {
        isSuccess = success;
        this.put("isSuccess", this.isSuccess);
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        this.put("isSuccess", this.errMsg);
    }
}
