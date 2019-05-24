package com.chuangmeng.cmzc.index.service.Impl;

import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.PO.TbProjectType;
import com.chuangmeng.cmzc.commons.PO.TbStory;
import com.chuangmeng.cmzc.commons.dto.*;
import com.chuangmeng.cmzc.index.dao.IndexMapper;
import com.chuangmeng.cmzc.index.service.IndexService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IndexMapper indexMapper;
    @Override
    public List<TbProject> queryHot() throws Exception {
        //查询热门的
        ObjectMapper objectMapper=new ObjectMapper();
        String s = stringRedisTemplate.boundValueOps("cmzc-project-warm").get();
        List<FirFProject> lendReco = objectMapper.readValue(s,new TypeReference<List<FirFProject>>() { });



        List<TbProject> hots = indexMapper.queryHot();
        if(null==hots||hots.isEmpty()){
            throw new NullPointerException("No item found!");
        }
        return  hots;
    }

    @Override
    public List<TbProject> queryNew() throws Exception {
        //最新上架
        List<TbProject> news = indexMapper.queryNew();
        if(null==news||news.isEmpty()){
            throw new NullPointerException("No item found!");
        }
        return news;
    }

    @Override
    public List<EndProject> queryEnd() throws Exception {
        //即将结束
        List<EndProject> ends = indexMapper.queryEnd();
        if(null==ends||ends.isEmpty()){
            throw new NullPointerException("No item found!");
        }
        for (EndProject endProject:ends){
            long endtime = endProject.getProjectEndTime().getTime();
            long startTime = new Date().getTime();
            endProject.setTime((endtime-startTime)/(1000*60*60));
        }
        return ends;
    }

    @Override
    public List<FProjectType> queryType() throws Exception {
        //6F
        List<FProjectType> types = indexMapper.queryType();
        if(null==types||types.isEmpty()){
            throw new NullPointerException("No item found!");
        }
//        //第一个项目
//         firFProjects=new ArrayList<>();
//        //后六个项目
//        projects=new ArrayList<>();
        for(FProjectType fType:types){
            for(SProjectType sType:fType.getsProjectTypes()){
                String t1=indexMapper.queryId(sType.getTypeName2());
                List<FirFProject>   firFProjects = indexMapper.queryFirstProject(t1);
                List<EndProject>  projects=indexMapper.querySixProject(indexMapper.queryId(sType.getTypeName2()));
                for(EndProject endProject:projects){
                    long endtime = endProject.getProjectEndTime().getTime();
                    long startTime = new Date().getTime();
                    endProject.setTime((endtime-startTime)/(1000*60*60));
                }
                for(FirFProject firFProject:firFProjects){
                    firFProject.setProjects(projects);
                }
                sType.setSixFProjects(firFProjects);
            }
        }
        return types;
    }

    @Override
    public List<FirFProject> queryStart() throws Exception {

        ObjectMapper objectMapper=new ObjectMapper();
//        String project = objectMapper.writeValueAsString(projects);
        String s = stringRedisTemplate.boundValueOps("cmzc-project-warm").get();
        List<FirFProject> lendReco = objectMapper.readValue(s,new TypeReference<List<FirFProject>>() { });
        return lendReco;
    }

    @Override
    public List<TbStory> queryStory() throws Exception {
        List<TbStory> storys = indexMapper.queryStory();
//        if(1==1){
//            throw new Exception("Sssssss");
//        }
        if(null==storys||storys.isEmpty()){
            throw new NullPointerException("No data was available for the story!");
        }
        return storys;
    }

    @Override
    public List<TbProjectType> queryAllType() throws Exception {
        List<TbProjectType> allTypes = indexMapper.queryAllType();
        return allTypes;
    }

    @Override
    public List<FirFProject> search(String categoryId, String key) throws Exception {

        List<FirFProject> search = indexMapper.search(categoryId, key);
        return search;
    }
}
