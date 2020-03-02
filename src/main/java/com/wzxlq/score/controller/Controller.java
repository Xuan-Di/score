package com.wzxlq.score.controller;


import com.google.gson.Gson;
import com.wzxlq.score.entity.UserInfo;
import com.wzxlq.score.entity.UserInforesult;
import com.wzxlq.score.utils.sentUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @author 王照轩
 * @date 2020/2/27 - 16:04
 */
@org.springframework.stereotype.Controller
public class Controller {

    private static final String TOKEN = "bzrccj";
    String sCorpID = "ww9b8c761b26841ee0";
    String sEncodingAESKey = "gG9oSuO68PMvigQEOWBazyRMFP1yf3joNvJEA63bT9A";


    @RequestMapping("getUserInfo")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        //获取code
        String code = request.getParameter("code");
        String at = sentUtil.getAccessToken();
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
        url = url.replace("ACCESS_TOKEN", at).replace("CODE", code);
        String result = sentUtil.get(url);
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(result, UserInfo.class);
        //学号或者工号
        String userId = userInfo.getUserId();

        String deviceId = userInfo.getDeviceId();
        String user_ticket = userInfo.getUser_ticket();
        int expires_in = userInfo.getExpires_in();
        System.out.println(userId);
        System.out.println(expires_in);
        //获得更多信息
        String urlmsg="https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=ACCESS_TOKEN";
         urlmsg = urlmsg.replace("ACCESS_TOKEN", at);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_ticket",user_ticket);
        String user_ticketstr = new Gson().toJson(map);
        System.out.println(user_ticketstr);
        String user_result = sentUtil.post(urlmsg, user_ticketstr);
        UserInforesult userInforesult = gson.fromJson(user_ticketstr, UserInforesult.class);
        String name = userInforesult.getName();
        String avatar = userInforesult.getAvatar();
        List<Integer> department = userInforesult.getDepartment();
        String email = userInforesult.getEmail();
        String mobile = userInforesult.getMobile();
        System.out.println(name);
    }
}
