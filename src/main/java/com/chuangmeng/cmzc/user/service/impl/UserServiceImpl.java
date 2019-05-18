package com.chuangmeng.cmzc.user.service.impl;

import com.chuangmeng.cmzc.commons.PO.TbUser;
import com.chuangmeng.cmzc.commons.VO.LoginVo;
import com.chuangmeng.cmzc.user.dao.UserMapper;
import com.chuangmeng.cmzc.user.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录验证
     * @param loginVo
     * @return
     */
    @Override
    public com.chuangmeng.cmzc.commons.vo.ResultVo login(LoginVo loginVo) {
        com.chuangmeng.cmzc.commons.vo.ResultVo resultVo = new com.chuangmeng.cmzc.commons.vo.ResultVo();
        resultVo.setCode("0");
        resultVo.setMsg("先定一个错误");
        TbUser tbUser = new TbUser();
        String phonenum="";
        String password="";
        UsernamePasswordToken token = null;
        if(loginVo!=null&&"phonenum".equals(loginVo.getType())){
            phonenum = loginVo.getName();
            if (redisTemplate.hasKey(phonenum)){
                String code = redisTemplate.boundValueOps(phonenum).get();
                if (code.equals(loginVo.getCode())){
                    resultVo.setCode("1");
                    resultVo.setMsg("登录成功");
                    //存到shiro中保存登录
                    token=new UsernamePasswordToken(loginVo.getName(),loginVo.getCode());
                    String shirokey = "com.chuangmeng.cmzc.user.login"+phonenum;
                    redisTemplate.boundValueOps(shirokey).set(loginVo.getType(),180, TimeUnit.SECONDS);
                }else{
                    resultVo.setCode("0");
                    resultVo.setMsg("验证码错误");
                    return resultVo;
                }
            }
        }else if(loginVo!=null&&"username".equals(loginVo.getType())){
            phonenum = loginVo.getName();
            password = loginVo.getCode();
            token=new UsernamePasswordToken(phonenum,password);
            String shirokey = "com.chuangmeng.cmzc.user.login"+phonenum;
            redisTemplate.boundValueOps(shirokey).set(loginVo.getType(),180,TimeUnit.SECONDS);
        }
        Subject subject = SecurityUtils.getSubject();
        //进入Shiro
        subject.login(token);
        //shiro没问题将user存在redis
        tbUser.setUserTel(phonenum);
        TbUser tbUser1 = userMapper.selectUserByTel(tbUser);
        if(null!=tbUser1){
            SimpleHash md5 = new SimpleHash("MD5");
            md5.setBytes((tbUser1.getUserTel()+System.currentTimeMillis()).getBytes());
            String usertoken =md5.toHex();
            ObjectMapper objectMapper = new ObjectMapper();
            String userJSON=null;
            try {
                userJSON = objectMapper.writeValueAsString(tbUser1);
                resultVo.setCode("1");
                resultVo.setMsg(usertoken);
                redisTemplate.boundValueOps(usertoken).set(userJSON,1800, TimeUnit.SECONDS);
                return resultVo;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return resultVo;
    }

    @Override
    public TbUser userCenter(String token) throws IOException {
        String userJSON = redisTemplate.boundValueOps(token).get();
        ObjectMapper objectMapper = new ObjectMapper();
        TbUser tbUser = objectMapper.readValue(userJSON, TbUser.class);
        return tbUser;
    }

    @Override
    public TbUser selectUser(TbUser tbUser) throws Exception {
        TbUser user = userMapper.selectUserByTel(tbUser);
        return user;
    }

}
