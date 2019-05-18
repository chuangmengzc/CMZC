package com.chuangmeng.cmzc;

import com.chuangmeng.cmzc.commons.RandString;
import com.chuangmeng.cmzc.commons.po.TbPackage;
import com.chuangmeng.cmzc.commons.po.TbProject;
import com.chuangmeng.cmzc.startedProject.dao.StartedProjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmzcApplicationTests {

    @Autowired
    private StartedProjectMapper startedProjectMapper;
    @Test
    public void contextLoads() {
        String time1="2019/02/05";
        String s1 = time1.replace("/", "-");
//        String time2="2020/02/05";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        try {
        try {
            Date date1 = sdf.parse(s1);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
////            Date date2=sdf.parse(time2);
////            startedProjectMapper.addProject(new Project("asdad","101000","test1","yayyay",22222222,0,date1,date2,0,"jhgjhgjh","jhhgj","hjhjbhb","hjjgjg","jgjhj",0,"dd","dd"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        startedProjectMapper.addPackage(new TbPackage("12345","asdad",39999,10,10,"dasda","dasd","dasdd","10000","15","dddd","ddd"));
//
//
        String s = RandString.generateString(20);
        System.out.println(s);
    }

}
