package com.chuangmeng.cmzc.user.service.impl;

import com.chuangmeng.cmzc.commons.DIR;
import com.chuangmeng.cmzc.commons.PO.TbUser;
import com.chuangmeng.cmzc.commons.utils.BackStageUtils;
import com.chuangmeng.cmzc.commons.utils.DuanxinController;
import com.chuangmeng.cmzc.commons.utils.DuanxinResult;
import com.chuangmeng.cmzc.commons.utils.RandString;
import com.chuangmeng.cmzc.user.dao.UserMapper;
import com.chuangmeng.cmzc.user.service.IRegist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Service
public class RegistImpl implements IRegist {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取验证码并根据电话作为key保存在redis中以便比对
     * @return
     */
    @Override
    public com.chuangmeng.cmzc.commons.vo.ResultVo giveDuanxin(String tel) throws Exception {
        TbUser tbUser = selectUserByTel(tel);
        com.chuangmeng.cmzc.commons.vo.ResultVo resultVo = new com.chuangmeng.cmzc.commons.vo.ResultVo();
        /*if(null!=tbUser){
            //判断用户是否注册
            resultVo.setCode("0");
            resultVo.setMsg("该用户已注册");
            return resultVo;
        }*/
        String code = RandString.generateStringOfNum(6);
        String success = DuanxinController.giveDuanxin(tel, code);
        System.out.println(success);
        ObjectMapper objectMapper = new ObjectMapper();
        if("".equals(success)){
            resultVo.setCode("1");
            resultVo.setMsg("验证码错误");
        }
        DuanxinResult duanxinResult = objectMapper.readValue(success, DuanxinResult.class);
        if(!"0".equals(duanxinResult.getResult())){
            resultVo.setCode("0");
            String msg = duanxinResult.getErrmsg();
            resultVo.setMsg(msg);
            return resultVo;
        }
        redisTemplate.boundValueOps(tel).set(code,180, TimeUnit.SECONDS);

        resultVo.setCode("1");
        resultVo.setMsg(code);
        return resultVo;
    }
    /**
     * 用户注册
     * @param user
     * @param userpic
     * @throws Exception
     */
    @Override
    public void adduser(TbUser user, MultipartFile userpic) throws Exception {
        String filepath = BackStageUtils.uploadFile(userpic, DIR.USER);
        user.setUserPic(filepath);
        String userId = creatNoRepeatUserId();
        user.setUserId(userId);
        userMapper.useradd(user);
    }


    /**
     * 根据电话查询用户
     * @param tel
     * @return
     * @throws Exception
     */
    @Override
    public TbUser selectUserByTel(String tel) throws Exception {
        TbUser uer = new TbUser();
        uer.setUserTel(tel);
        TbUser tbUser = userMapper.selectUserByTel(uer);
        return tbUser;
    }

    /**
     * 检查注册时验证码是否正确
     * @param code
     * @param random
     * @return
     */
    @Override
    public com.chuangmeng.cmzc.commons.vo.ResultVo checkCode(String code, String random) {
        com.chuangmeng.cmzc.commons.vo.ResultVo resultVo = new com.chuangmeng.cmzc.commons.vo.ResultVo();
        String verifykey = "com.chuangmeng.cmzc.user.regist"+random;
        if(!redisTemplate.hasKey(verifykey)){
            resultVo.setCode("0");
            resultVo.setMsg("注册超时请重新登录");
            return resultVo;
        }
        String rediscode = redisTemplate.boundValueOps(verifykey).get();
        if(rediscode.equals(code)){
            resultVo.setCode("1");
            resultVo.setMsg("验证码匹配成功");
            return resultVo;
        }else{
            resultVo.setCode("0");
            resultVo.setMsg("请正确填写验证码");
            return resultVo;
        }
    }

    /**
     * 注册时检车短信验证码是否正确
     * @param msg
     * @param tel
     * @return
     */
    @Override
    public com.chuangmeng.cmzc.commons.vo.ResultVo checkMassage(String msg, String tel) {
        com.chuangmeng.cmzc.commons.vo.ResultVo resultVo = new com.chuangmeng.cmzc.commons.vo.ResultVo();
        if(!redisTemplate.hasKey(tel)){
            resultVo.setCode("0");
            resultVo.setMsg("注册超时请重新登录");
            return resultVo;
        }
        String redismsg = redisTemplate.boundValueOps(tel).get();
        if(redismsg.equals(msg)){
            resultVo.setCode("1");
            resultVo.setMsg("验证码匹配成功");
            return resultVo;
        }
        resultVo.setCode("0");
        resultVo.setMsg("短信验证码错误");
        return resultVo;
    }

    /*
     * unicode编码转中文
     */
    public String decodeUnicode(String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 生成ID不重复的用户ID
     * @return
     */
    private String creatNoRepeatUserId(){
        TbUser tbUser = new TbUser();
        TbUser user =null;
        String id="";
        do {
            String type = "user";
            id =type+ RandString.generateString(16);
            tbUser.setUserId(id);
            user = userMapper.selectUserByTel(tbUser);
        }while (user!=null);
        return id;
    }
}
