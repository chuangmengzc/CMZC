package com.chuangmeng.cmzc.warmUp.service.Impl;

import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.dto.FirFProject;
import com.chuangmeng.cmzc.warmUp.dao.WarmUpMapper;
import com.chuangmeng.cmzc.warmUp.service.WarmUpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimerTask;

@Service
public class WarmUpServiceImpl implements WarmUpService{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private WarmUpMapper warmUpMapper;
    @Override
    public void queryWarmUp(Long projectStatus) throws Exception {
        List<FirFProject> projects = warmUpMapper.queryWarmUp(1L);
//        if(null==projects||projects.isEmpty()){
//            throw new NullPointerException("No relevant items were found");
//        }
        for(FirFProject firFProject:projects){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            firFProject.setProjectSpare2(simpleDateFormat.format(firFProject.getProjectStartTime()));
        }
        ObjectMapper objectMapper=new ObjectMapper();
        String project = objectMapper.writeValueAsString(projects);
        stringRedisTemplate.boundValueOps("cmzc-project-warm").set(project);
    }

//    @Override
//    public void run() {
//        try {
//            queryWarmUp(1L);
//            System.out.println("预览存入缓存成功!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
