package com.chuangmeng.cmzc.startedProject.service;


import com.chuangmeng.cmzc.commons.PO.TbPackage;
import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.PO.TbProjectType;

import java.util.List;

public interface StartedProjectService {
    //添加项目接口
    void addProject(TbProject tbProject, List<TbPackage> packageList , String projectStartTime, String projectEndTime) throws Exception;
    //查询项目类型
    List<TbProjectType> queryType() throws Exception;
}
