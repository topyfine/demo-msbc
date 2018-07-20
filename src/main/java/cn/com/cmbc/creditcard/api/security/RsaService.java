package cn.com.cmbc.creditcard.api.security;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * RSA 加解密
 *
 * @author Administrator
 */
public class RsaService {
    private String pubkey;
    private String prvkey;

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public String getPrvkey() {
        return prvkey;
    }

    public void setPrvkey(String prvkey) {
        this.prvkey = prvkey;
    }

    /***
     * RSA解密
     *
     * @param encryptData
     * @return
     * @throws Exception
     */
    public String RSADecryptDataPEM(String encryptData) throws Exception {
        byte[] prvdata = RSA.decryptByPrivateKey(Base64Utils.decode(encryptData), prvkey);
        String outString = new String(prvdata, "GBK");
        return outString;
    }

    /***
     * RSA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public String RSAEncryptDataPEM(String data) throws Exception {

        byte[] pubdata = RSA.encryptByPublicKey(data.getBytes("GBK"), pubkey);
        String outString = new String(Base64Utils.encode(pubdata));
        return outString;
    }

    public static void main(String[] args) throws Exception {
        RsaService rsaService = new RsaService();
        rsaService.setPrvkey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJhSM1Uig6UU7tYr\n" +
                ".....\n" +
                "wAOZlpZ474kj8rM=");
        rsaService.setPubkey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYUjNVIoOlFO7WK7td09t/QpZ6\n" +
                "cF6MeBm7QyBFM7suGdDzFNit4J3JG3/1UyrywJHtbsB+5VWZDJvqn2XUpccryeMI\n" +
                "YNlcz/3YIajKAO62IXs3GsXP2tcNujKsQIRQiL42do87Duzl01gByd5X3EBef144\n" +
                "3od5R+Vn9i/Wm6BMIwIDAQAB");


        Map<String, String> data = new HashMap<>();

        data.put("referflag", "0");
        data.put("recommendId", "1483109675001");
        data.put("name", "张三");
        data.put("openid", "12365489kjhkj");
        data.put("newrefercode", "237549246624");
        data.put("tradeFrom", "CT-KHTJ");
        String json = JSON.toJSONString(data);

        String outString2 = rsaService.RSAEncryptDataPEM(json);
        System.out.println(outString2);
        //解密需要私钥，请自行设置私钥
//        String json23 = rsaService.RSADecryptDataPEM(outString2);
//        System.out.println(json23);
    }
}
