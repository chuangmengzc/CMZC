package com.chuangmeng.cmzc;

import com.chuangmeng.cmzc.commons.utils.TimerManager;
import com.chuangmeng.cmzc.warmUp.Dad;
import com.chuangmeng.cmzc.warmUp.service.Impl.WarmUpServiceImpl;
import com.chuangmeng.cmzc.warmUp.service.WarmUpService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Timer;

@EnableTransactionManagement  //增加事务
@SpringBootApplication
@MapperScan("com.chuangmeng.cmzc.*.dao")
public class CmzcApplication {
    public static void main(String[] args) {

        SpringApplication.run(CmzcApplication.class, args);
//        System.out.println("定时任务执行");
//        Timer timer = new Timer();
//            timer.schedule(new Dad(),  5000);
//        WarmUpServiceImpl warmUpService=new WarmUpServiceImpl();
//        try {
//            warmUpService.queryWarmUp(1L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Dad.runn();
        TimerManager timerManager=new TimerManager();
    }



}
