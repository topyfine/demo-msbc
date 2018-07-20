package com.example.demo;

import cn.com.cmbc.creditcard.api.security.RsaService;
import com.alibaba.fastjson.JSON;
import com.example.demo.entity.NormalResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author 杨帆
 * @date 2018/6/7 16:27
 * @desc v1.0.0
 */
@Controller
public class IndexController {

    private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJM+uRT06cWbHKc8RcZ2P4EQpcgOKXWfK2d5FPsWYYBPXrh2ii0ikKnwlPUFaLLYfjL97hxhArsEDN5/dX65C08E9e4TWo4v/yl0nZxhrR3hq/FKFQv1AYcl/G6McLqQwH9K93Mf4/VxB2W8+IROeRO2CJGFc7blpUdN4122tpbAgMBAAECgYASgxtWGy7D22S29+Ms1AhHNpW7YOGLdiiqhfctkTO63Sw97B6Rj+zgwXLTdBtK0Lhvn5LnSoFnB9jZZ2iP4SBPsj439N+8VrGcxAk4Lq7dc5S10SCuNkYyhzZgV2rqCvPx/gvR0fk5QZGW+zWXRrjXMXji9HCgHaohSMrumTWKuQJBAP0NWPasO2Uwahi623kDYN6kQiorwhFzliQdCa7OwqL9BWL1alXX1NOJOqzzs49SU/JSBbLYMQM6bXnckGVMDA0CQQCD0ZAhWJCAh3n7GPlckEzZTKC/18YjpPVxybCmJTxwkfuOzsYB26Iv3/mTL25eRMWqFVFpDGLqsSQtCo6JkR4HAkBfO563dqoDsuy60de2+yaBo5+i70Th/HYMsUDroLRvUnmsfF/AXrGhyotie3SgA4PENkoevG31oQ+CRReyh6QhAkB+VvO6UiUlWzOZa4VMp21Qc/Y6CPf6H9ezOKkbhOLZzcD6TcpP648WLagJ3tL8fROqgYYmld2UrTVMCis1RKYvAkEA5U4owI37szGu+qAkfhCds5YfD2gbTzBvD5RaTyLmpCOL8OhN49JKEBaEW5ZYBZk9etvgV3RmzDscuCN2Bg9+LQ==";

    @RequestMapping("/index")
    @ResponseBody
    public String index(String tpenstr) {
        System.out.println(tpenstr);
        System.out.println(">>> request index page");
        // 解密
        try {
            RsaService rsaService = new RsaService();
            rsaService.setPrvkey(privateKey);
            String deStr = rsaService.RSADecryptDataPEM(tpenstr);
            System.out.println(">>> 提交申请后立即回调结果，" + deStr);
        } catch (Exception e) {
            System.err.println(">>> 解密失败");
        }
        return "Hello world!";
    }

    @RequestMapping(value = "/cmbc/creditcard", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String callback(@RequestBody String tpenstr, HttpServletRequest request) {
        String characterEncoding = request.getCharacterEncoding();
        System.out.println(characterEncoding);
        String contentType = request.getContentType();
        System.out.println(contentType);
//        request.getInputStream().toString()
        System.out.println(tpenstr);
        System.out.println(">>> 审核反馈接口调用");
        // 解密
        RsaService rsaService = new RsaService();
        rsaService.setPrvkey(privateKey);
        try {
            /*String deTpEnstr = URLDecoder.decode(tpenstr, "utf-8");
            System.out.println(deTpEnstr);*/
            String deStr = rsaService.RSADecryptDataPEM(tpenstr);
            System.out.println(">>> 审核反馈接口回调结果，" + deStr);
        } catch (Exception e) {
            System.err.println(">>> 解密失败");
        }
        return JSON.toJSONString(new NormalResponse());
    }

    public static void main(String[] args) {
        /*String str = new IndexController().callback(null);
        System.out.println(str);*/
    }
}
