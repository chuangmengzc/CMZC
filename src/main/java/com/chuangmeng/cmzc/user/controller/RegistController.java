package com.chuangmeng.cmzc.user.controller;

import com.chuangmeng.cmzc.commons.PO.TbUser;
import com.chuangmeng.cmzc.user.service.IRegist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("Regist")
public class RegistController {
    @Autowired
    private IRegist registImpl;

    @RequestMapping("getRegistMessage/{tel}")
    @ResponseBody
    private com.chuangmeng.cmzc.commons.vo.ResultVo getRegistMessage(@PathVariable("tel") String tel){
        com.chuangmeng.cmzc.commons.vo.ResultVo resultVo = null;
        try {
            resultVo = registImpl.giveDuanxin(tel);
            return resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    @RequestMapping(value = "Regist/{random}",method = RequestMethod.POST)
    private String Regist(Model model, TbUser user, @RequestParam MultipartFile userpic, String code, String noteCode, @PathVariable("random") String random){
        com.chuangmeng.cmzc.commons.vo.ResultVo resultVo = new com.chuangmeng.cmzc.commons.vo.ResultVo();
        //验证验证码
        resultVo = registImpl.checkCode(code, random);
        if(!"1".equals(resultVo.getCode())){
            model.addAttribute("error",resultVo.getMsg());
            return "register.html";
        }
        //验证短信码
        com.chuangmeng.cmzc.commons.vo.ResultVo resultVo1 = registImpl.checkMassage(noteCode, user.getUserTel());
        if(!"1".equals(resultVo1.getCode())){
            model.addAttribute("error",resultVo1.getMsg());
            return "register.html";
        }
        try {
            /*registImpl.adduser(user,userpic);*/
            resultVo.setCode("1");
            resultVo.setMsg("注册成功");
            return "login2.html";
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultVo.setCode("0");
        resultVo.setMsg("注册失败请重新注册");
        model.addAttribute("error",resultVo.getMsg());
        return "register.html";
    }
    @RequestMapping(value = "Registtest/{random}")
    public String add(){
        return "login2.html";
    }

}
