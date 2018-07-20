package com.example.demo;

import cn.com.cmbc.creditcard.api.security.RsaService;
import com.alibaba.fastjson.JSON;
import com.example.demo.entity.CardApplyRequest;

/**
 * @author 杨帆
 * @date 2018/6/7 14:15
 * @desc v1.0.0
 */
public class ApiTest {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYUjNVIoOlFO7WK7td09t/QpZ6cF6MeBm7QyBFM7suGdDzFNit4J3JG3/1UyrywJHtbsB+5VWZDJvqn2XUpccryeMIYNlcz/3YIajKAO62IXs3GsXP2tcNujKsQIRQiL42do87Duzl01gByd5X3EBef1443od5R+Vn9i/Wm6BMIwIDAQAB";
//    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCTPrkU9OnFmxynPEXGdj+BEKXIDil1nytneRT7FmGAT164dootIpCp8JT1BWiy2H4y/e4cYQK7BAzef3V+uQtPBPXuE1qOL/8pdJ2cYa0d4avxShUL9QGHJfxujHC6kMB/SvdzH+P1cQdlvPiETnkTtgiRhXO25aVHTeNdtraWwIDAQAB"


    private static final String BIZ_CHANNEL = "YX-CDSK1";

    public static void main(String[] args) throws Exception {
        RsaService rsaService = new RsaService();
        rsaService.setPubkey(PUBLIC_KEY);
        CardApplyRequest data = new CardApplyRequest();
        data.setBizChannel(BIZ_CHANNEL);
        data.setPartnerPin("00003125");
        data.setPartnerNo(String.valueOf(System.currentTimeMillis()));
        data.setCallback("http://192.168.4.168:8080/index");
        String json = JSON.toJSONString(data);
        System.out.println(json);
        String outString2 = rsaService.RSAEncryptDataPEM(json);
        System.out.println(outString2);
        // 生成链接
        String path = "https://creditcard.cmbc.com.cn/wsv2/index?tpenstr=" + outString2;
        System.out.println(path);
        /*String path1 = "http://yyws.dev.creditcard.cmbc.com.cn/?tpenstr=" + outString2;
        System.out.println(path1);
        String path2 = "http://195.203.56.30/onlinenet/index/index.jhtml?tpenstr=" + outString2;
        System.out.println(path2);*/
    }
}
