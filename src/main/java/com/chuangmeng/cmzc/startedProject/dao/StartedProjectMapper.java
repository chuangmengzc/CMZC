package com.chuangmeng.cmzc.startedProject.dao;

import com.chuangmeng.cmzc.commons.po.TbPackage;
import com.chuangmeng.cmzc.commons.po.TbProject;
import com.chuangmeng.cmzc.commons.po.TbProjectType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StartedProjectMapper {
    //添加项目
    void addProject(TbProject tbProject);
    //添加项目套餐
    void addPackage(TbPackage tbPackage);
    //查询项目类型
    List<TbProjectType> queryType();
}
