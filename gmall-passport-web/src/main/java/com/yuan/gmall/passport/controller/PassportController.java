package com.yuan.gmall.passport.controller;

import com.yuan.gmall.bean.UmsMember;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PassportController {

    @RequestMapping("verify")
    @ResponseBody
    public String verify(String token){

        // 通过jwt校验token真假

        return "success";
    }


    @RequestMapping("login")
    @ResponseBody
    public String login(UmsMember umsMember){

        // 调用用户服务验证用户名和密码

        return "token";
    }

    @RequestMapping("index")
    public String index(String ReturnUrl, ModelMap map){

        map.put("ReturnUrl",ReturnUrl);
        return "index";
    }
}
