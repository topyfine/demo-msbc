package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author 杨帆
 * @date 2018/6/7 14:21
 * @desc v1.0.0
 */
@Data
@ToString
public class CardApplyRequest {
    @JSONField(name = "biz_channel")
    private String bizChannel;
    @JSONField(name = "partner_pin")
    private String partnerPin;
    @JSONField(name = "partner_no")
    private String partnerNo;
    @JSONField(name = "card_no")
    private String cardNo;
    @JSONField(name = "card_level")
    private String cardLevel;
    @JSONField(name = "apply_name")
    private String applyName;
    @JSONField(name = "apply_id_no")
    private String applyIdNo;
    @JSONField(name = "apply_phone_no")
    private String applyPhoneNo;
    @JSONField(name = "callback")
    private String callback;
    @JSONField(name = "field1")
    private String field1;
    @JSONField(name = "field2")
    private String field2;
    @JSONField(name = "field3")
    private String field3;
}
