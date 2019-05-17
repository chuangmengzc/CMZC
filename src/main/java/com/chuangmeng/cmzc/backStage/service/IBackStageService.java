package com.chuangmeng.cmzc.backStage.service;

import com.chuangmeng.cmzc.commons.VO.BackStage.AdminInfo;
import com.chuangmeng.cmzc.commons.VO.BackStage.AuditProject;
import com.chuangmeng.cmzc.commons.VO.BackStage.QueryBill;

import java.util.List;

public interface IBackStageService {
    //查询首页信息
    List<AdminInfo> queryBackStageAdminInfo();
    //查询待审核项目
    List<AuditProject> queryAuditProject();
    //查询需解冻项目
    List<AuditProject> queryFrozenProject();
    //查询账单
    List<QueryBill> querybill();
    //审核
    void examine(String projectId);
    //解冻保证金
    void thawMoney(String projectId,String FrozenMoney,String businessId) throws Exception;
}
