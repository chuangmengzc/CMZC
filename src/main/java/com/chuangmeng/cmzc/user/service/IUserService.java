package com.chuangmeng.cmzc.user.service;

import com.chuangmeng.cmzc.commons.PO.TbUser;
import com.chuangmeng.cmzc.commons.VO.LoginVo;

import java.io.IOException;

public interface IUserService {
    com.chuangmeng.cmzc.commons.vo.ResultVo login(LoginVo loginVo);
    TbUser userCenter(String token) throws IOException;
    TbUser selectUser(TbUser tbUser) throws Exception;

}
