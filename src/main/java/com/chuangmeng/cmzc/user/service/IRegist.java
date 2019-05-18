package com.chuangmeng.cmzc.user.service;

import com.chuangmeng.cmzc.commons.PO.TbUser;
import org.springframework.web.multipart.MultipartFile;

public interface IRegist {
    com.chuangmeng.cmzc.commons.vo.ResultVo giveDuanxin(String tel) throws Exception;
    void adduser(TbUser user, MultipartFile userpic) throws Exception;
    TbUser selectUserByTel(String userTel) throws Exception;
    //验证验证码
    com.chuangmeng.cmzc.commons.vo.ResultVo checkCode(String code, String random);
    //验证短信码
    com.chuangmeng.cmzc.commons.vo.ResultVo checkMassage(String msg, String tel);
}
