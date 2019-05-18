package com.chuangmeng.cmzc.backStage.service.impl;

import com.chuangmeng.cmzc.backStage.dao.IBackStageMapper;
import com.chuangmeng.cmzc.backStage.service.IBackStageService;
import com.chuangmeng.cmzc.commons.PO.TbBill;
import com.chuangmeng.cmzc.commons.VO.BackStage.AdminInfo;
import com.chuangmeng.cmzc.commons.VO.BackStage.AuditProject;
import com.chuangmeng.cmzc.commons.VO.BackStage.QueryBill;
import com.chuangmeng.cmzc.commons.utils.BackStageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BackStageServiceImpl implements IBackStageService {
    @Autowired
    private IBackStageMapper mapper;

    @Override
    public List<AdminInfo> queryBackStageAdminInfo() {
        List<AdminInfo> adminInfos = mapper.queryBackStageAdminInfo();
        return adminInfos;
    }

    @Override
    public List<AuditProject> queryAuditProject() {
        List<AuditProject> auditProjects = mapper.queryAuditProject();
        return auditProjects;
    }

    @Override
    public List<AuditProject> queryFrozenProject() {
        List<AuditProject> auditProjects = mapper.queryFrozenProject();
        return auditProjects;
    }

    @Override
    public List<QueryBill> querybill() {
        List<QueryBill> queryBills = mapper.queryBill();
        return queryBills;
    }

    @Override
    public void examine(String projectId) {
        mapper.changeProjectStatus(1,projectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void thawMoney(String projectId, String FrozenMoney, String businessId) throws Exception {
        int frozenMoney = (int) (Integer.parseInt(FrozenMoney)*0.3);
        mapper.businessGetMoney(frozenMoney,businessId);
        mapper.changeProjectStatus(7,projectId);
        TbBill bill = new TbBill();
        bill.setBillId(BackStageUtils.getString(20));
        bill.setProjectId(projectId);
        bill.setBillCash(frozenMoney*-1);
        bill.setBillDate(new java.sql.Timestamp(System.currentTimeMillis()));
        bill.setBillDescribe("退还保证金");
        mapper.insertBill(bill);
//        throw new Exception("人造错误");
    }
}
