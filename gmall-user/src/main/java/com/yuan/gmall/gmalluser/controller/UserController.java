package com.yuan.gmall.gmalluser.controller;



import com.yuan.gmall.gmalluser.bean.UserInfo;
import com.yuan.gmall.gmalluser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("userInfoList")
    public ResponseEntity<List<UserInfo>> userInfoList(HttpServletRequest request){

        List<UserInfo> userInfoList = userService.userInfoList();

        return ResponseEntity.ok(userInfoList);
    }
}
