package com.chuangmeng.cmzc.warmUp;

import com.chuangmeng.cmzc.projectStatus.service.Impl.ProjectStatusServiceImpl;
import com.chuangmeng.cmzc.projectStatus.service.ProjectStatusService;
import com.chuangmeng.cmzc.warmUp.service.Impl.WarmUpServiceImpl;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
public class Dad  extends TimerTask{


    @Override
    public void run() {
        try {
            //获取WarmUpServiceImpl对象
            WarmUpServiceImpl warmUpService= (WarmUpServiceImpl) Ser.getBean("warmUpServiceImpl");
            warmUpService.queryWarmUp(1L);
            System.out.println("预览存入缓存成功!");
            //获取ProjectStatusServiceImpl对象
            ProjectStatusService projectStatusService= (ProjectStatusService) Ser.getBean("projectStatusServiceImpl");
            projectStatusService.kickEnd();

            System.out.println("众筹时间结束的项目状态已改变！");
            projectStatusService.start();
            System.out.println("准备上架的上架成功！");
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
    public static void runn() {
        try {

            WarmUpServiceImpl warmUpService= (WarmUpServiceImpl) Ser.getBean("warmUpServiceImpl");
            warmUpService.queryWarmUp(1L);
            System.out.println("预览存入缓存成功!");
            ProjectStatusService projectStatusService= (ProjectStatusService) Ser.getBean("projectStatusServiceImpl");
            projectStatusService.kickEnd();
            System.out.println("众筹时间结束的项目状态已改变！");
            projectStatusService.start();
            System.out.println("准备上架的上架成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
