package com.example.demo;

import cn.com.cmbc.creditcard.api.security.RSA;
import cn.com.cmbc.creditcard.api.security.RsaService;
import com.alibaba.fastjson.JSON;
import com.example.demo.entity.CardApplyRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author 杨帆
 * @date 2018/6/7 16:03
 * @desc v1.0.0
 */
public class RsaTest {

    private String publicKey;

    private String privateKey;

    private String bizChannel;

    @Before
    public void init() {
        /*rsa = new RSA();*/
        publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCTPrkU9OnFmxynPEXGdj+BEKXIDil1nytneRT7FmGAT164dootIpCp8JT1BWiy2H4y/e4cYQK7BAzef3V+uQtPBPXuE1qOL/8pdJ2cYa0d4avxShUL9QGHJfxujHC6kMB/SvdzH+P1cQdlvPiETnkTtgiRhXO25aVHTeNdtraWwIDAQAB";
        privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJM+uRT06cWbHKc8RcZ2P4EQpcgOKXWfK2d5FPsWYYBPXrh2ii0ikKnwlPUFaLLYfjL97hxhArsEDN5/dX65C08E9e4TWo4v/yl0nZxhrR3hq/FKFQv1AYcl/G6McLqQwH9K93Mf4/VxB2W8+IROeRO2CJGFc7blpUdN4122tpbAgMBAAECgYASgxtWGy7D22S29+Ms1AhHNpW7YOGLdiiqhfctkTO63Sw97B6Rj+zgwXLTdBtK0Lhvn5LnSoFnB9jZZ2iP4SBPsj439N+8VrGcxAk4Lq7dc5S10SCuNkYyhzZgV2rqCvPx/gvR0fk5QZGW+zWXRrjXMXji9HCgHaohSMrumTWKuQJBAP0NWPasO2Uwahi623kDYN6kQiorwhFzliQdCa7OwqL9BWL1alXX1NOJOqzzs49SU/JSBbLYMQM6bXnckGVMDA0CQQCD0ZAhWJCAh3n7GPlckEzZTKC/18YjpPVxybCmJTxwkfuOzsYB26Iv3/mTL25eRMWqFVFpDGLqsSQtCo6JkR4HAkBfO563dqoDsuy60de2+yaBo5+i70Th/HYMsUDroLRvUnmsfF/AXrGhyotie3SgA4PENkoevG31oQ+CRReyh6QhAkB+VvO6UiUlWzOZa4VMp21Qc/Y6CPf6H9ezOKkbhOLZzcD6TcpP648WLagJ3tL8fROqgYYmld2UrTVMCis1RKYvAkEA5U4owI37szGu+qAkfhCds5YfD2gbTzBvD5RaTyLmpCOL8OhN49JKEBaEW5ZYBZk9etvgV3RmzDscuCN2Bg9+LQ==";
        bizChannel = "YX-CDSK1";
    }

    @Test
    public void testRsa() throws Exception {
        Map<String, Object> keyPair = RSA.genKeyPair();
        assertNotNull(keyPair);
        String publicKey = RSA.getPublicKey(keyPair);
        System.out.println(">>> publicKey");
        System.out.println(publicKey);
        String privateKey = RSA.getPrivateKey(keyPair);
        System.out.println(">>> privateKey");
        System.out.println(privateKey);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("d:/temp/rsa-"
                + System.currentTimeMillis() + ".txt")));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("publicKey=")
                .append(publicKey)
                .append("\n")
                .append("privateKey=")
                .append(privateKey);
        outputStream.write(stringBuilder.toString().getBytes());
        outputStream.flush();
    }

    @Test
    public void testRsaKey() throws Exception {
        /*CardApplyRequest data = new CardApplyRequest();
        data.setBizChannel(bizChannel);
        data.setPartnerPin("201806070001");
        data.setPartnerNo(String.valueOf(System.currentTimeMillis()));*/
        /*Map<String, Object> data = new HashMap<>();
        data.put("approveStatus", "D");
        data.put("partnerPin", "00001234");
        data.put("partnerNo", String.valueOf(System.currentTimeMillis()));
        String json = JSON.toJSONString(data);*/
        RsaService rsaService = new RsaService();
        rsaService.setPubkey(publicKey);
        rsaService.setPrvkey(privateKey);
        /*
        String enStr = rsaService.RSAEncryptDataPEM(json);
        System.out.println(enStr);*/
        String enStr = "EV5ZfRufrzub9UZgbEZ05Qg7qALVs/g57E1X64DmgPJyDavRcvMpkgxcDyMQ9nPWiLocJfmqzxdQ97rdjdHmWIABc7pexFSvIea5TA5zT5FdpIqALqHZpzL9JnRUbQRJrFlR49xd+IjjVyO4Q+DKw35NeqGYY3QAjwJUlMC2RdM=";
        /*String enStr2 = URLDecoder.decode(enStr, "UTF-8");
        System.out.println(enStr2);*/
        /*String encode = URLEncoder.encode(enStr, "UTF-8");
        System.out.println(enStr);
        System.out.println(encode);*/
        String deStr = rsaService.RSADecryptDataPEM(enStr);
        System.out.println(deStr);
        // 解密
//        assertEquals(json, deStr);
    }

}
