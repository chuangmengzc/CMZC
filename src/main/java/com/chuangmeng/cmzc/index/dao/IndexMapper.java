package com.chuangmeng.cmzc.index.dao;

import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.PO.TbProjectType;
import com.chuangmeng.cmzc.commons.PO.TbStory;
import com.chuangmeng.cmzc.commons.dto.EndProject;
import com.chuangmeng.cmzc.commons.dto.FProjectType;
import com.chuangmeng.cmzc.commons.dto.Project;
import com.chuangmeng.cmzc.commons.dto.FirFProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IndexMapper {
    //查询热门的
       List<TbProject>  queryHot();

       //查询最新上架
       List<TbProject>  queryNew();

       //即将结束
    List<EndProject> queryEnd();

    //查询所有的类型及类型的子类型
    List<FProjectType> queryType();

   //查询6F的第一个项目
    List<FirFProject> queryFirstProject(@Param("projecrType") String projecrType);
    //查询6F的后六个项目
    List<EndProject> querySixProject(@Param("projecrType") String projecrType);
    //根据项目名称查询项目Id
    String queryId(@Param("typeName") String typeName);

    //查询故事
    List<TbStory> queryStory();

    //查询项目类型
    List<TbProjectType> queryAllType();

    //首页的搜索功能
     List<FirFProject>  search(@Param("categoryId") String categoryId,@Param("key") String key);


}
