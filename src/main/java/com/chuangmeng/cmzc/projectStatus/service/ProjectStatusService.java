package com.chuangmeng.cmzc.projectStatus.service;


import com.chuangmeng.cmzc.commons.PO.TbProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectStatusService {
    //项目众筹失败（商家取消）
    void  projectCancellation(String order1pare1) throws Exception;
    //项目众筹结束
    void kickEnd() throws  Exception;
    //项目完成
    void projectCompletion(String projectId) throws  Exception;
    //项目正在返利
    void rebate(String projectId) throws  Exception;
    //项目开始众筹
    void start()throws  Exception;
}
