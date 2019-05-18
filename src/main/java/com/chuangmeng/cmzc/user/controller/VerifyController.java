package com.chuangmeng.cmzc.user.controller;

import com.chuangmeng.cmzc.commons.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class VerifyController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("getVerify/{random}")
    public void getVerify(@PathVariable("random") String random, HttpServletResponse response){
        Random rand = new Random();
        BufferedImage img = new BufferedImage(Constant.width, Constant.height,BufferedImage.TYPE_INT_ARGB);
        //得到绘制对象
        Graphics graph = img.getGraphics();
        //条背景颜色
        graph.setColor(new Color(rand.nextInt(255),rand.nextInt(255),(rand.nextInt(255))));
        //填充图片
        graph.fillRect(0, 0, Constant.width, Constant.height);
        //设置字体
        graph.setFont(new Font("宋体", Font.BOLD,26));

        //产生随机码
        //随机验证码

        int count = 0;
        String str = "";
        do{
            int i = rand.nextInt(122);
            if(i>=48&&i<=57||i>=65&&i<=90||i>=97&&i<=122){
                char c = (char) i;
                String s = "";
                s+=c;
                str+=c;
                count++;
                //s的颜色
                graph.setColor(new Color(rand.nextInt(255),rand.nextInt(255),(rand.nextInt(255))));
                graph.drawString(s,5+(count-1)*25, 20);
            }
        }while(count<4);

        //随机线条
        for(int i =0;i<5;i++){
            graph.setColor(new Color(rand.nextInt(255),rand.nextInt(255),(rand.nextInt(255))));
            graph.drawLine(rand.nextInt(200),rand.nextInt(50), rand.nextInt(200),rand.nextInt(50));
        }
        String verifykey = "com.chuangmeng.cmzc.user.regist"+random;
        //保存3分钟
        redisTemplate.boundValueOps(verifykey).set(str,180, TimeUnit.SECONDS);
        System.out.println(str);
        response.addHeader("Cache-Control", "no-cache");
        try {
            ImageIO.write(img, "PNG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
