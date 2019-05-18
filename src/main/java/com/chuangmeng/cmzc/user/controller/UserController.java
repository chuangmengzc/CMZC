package com.chuangmeng.cmzc.user.controller;

import com.chuangmeng.cmzc.commons.PO.TbUser;
import com.chuangmeng.cmzc.commons.VO.LoginVo;
import com.chuangmeng.cmzc.user.service.IRegist;
import com.chuangmeng.cmzc.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userServiceImpl;
    @Autowired
    private IRegist RegistImpl;

    @ResponseBody
    @RequestMapping("login")
    public com.chuangmeng.cmzc.commons.vo.ResultVo login(LoginVo loginVo){
        com.chuangmeng.cmzc.commons.vo.ResultVo login = userServiceImpl.login(loginVo);
        return login;
    }

    @RequestMapping("userCenter/{token}")
    public String userCenter(@PathVariable("token") String token, Model model){
        TbUser tbUser =null;
        try {
           tbUser = userServiceImpl.userCenter(token);
            model.addAttribute("user",tbUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(tbUser==null){
            return "login2.html";
        }
        return "usercenter.html";
    }

    @ResponseBody
    @RequestMapping("selectTel")
    public String selectTel(String userTel){
        TbUser tbUser = new TbUser();
        tbUser.setUserTel(userTel);
        try {
            TbUser user = userServiceImpl.selectUser(tbUser);
            if(null==user){
                return "true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
}
