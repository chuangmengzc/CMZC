package com.chuangmeng.cmzc.index.service;

import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.PO.TbProjectType;
import com.chuangmeng.cmzc.commons.PO.TbStory;
import com.chuangmeng.cmzc.commons.dto.EndProject;
import com.chuangmeng.cmzc.commons.dto.FProjectType;
import com.chuangmeng.cmzc.commons.dto.FirFProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IndexService {
    //查询热门的
    List<TbProject> queryHot() throws  Exception;
    //查询最新上架
    List<TbProject>  queryNew() throws  Exception;
    //查询即将结束
    List<EndProject> queryEnd() throws Exception;
    //查询类型及其类型下的子类型的所有项目
    List<FProjectType> queryType() throws  Exception;
    //即将上架
    List<FirFProject>  queryStart() throws Exception;
    //查询故事
    List<TbStory> queryStory() throws  Exception;
    //查询项目类型
    List<TbProjectType> queryAllType()throws Exception;
    //首页的搜索功能
    List<FirFProject>  search(String categoryId,String key) throws  Exception;
}
