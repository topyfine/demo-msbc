package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author 杨帆
 * @date 2018/6/8 13:58
 * @desc v1.0.0
 */
@Data
@ToString
public class NormalResponse {
    @JSONField(name = "code")
    private String code = "0";
    @JSONField(name = "msg")
    private String msg = "success";
}
