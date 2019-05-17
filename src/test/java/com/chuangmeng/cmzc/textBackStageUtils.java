package com.chuangmeng.cmzc;

import com.chuangmeng.cmzc.backStage.mapper.IBackStageMapper;
import com.chuangmeng.cmzc.backStage.service.IBackStageService;
import com.chuangmeng.cmzc.commons.PO.TbBill;
import com.chuangmeng.cmzc.commons.VO.BackStage.AdminInfo;
import com.chuangmeng.cmzc.commons.VO.BackStage.AuditProject;
import com.chuangmeng.cmzc.commons.utils.BackStageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class textBackStageUtils {

    @Autowired
    private IBackStageMapper mapper;
    @Autowired
    private IBackStageService service;

    @Test
    public void textGetString() {
        BackStageUtils backStageUtils = new BackStageUtils();
        System.out.println(backStageUtils.getString(20));
    }

    @Test
    public void textQueryAdminInfo(){
        List<AdminInfo> list = mapper.queryBackStageAdminInfo();
        list.forEach((d)-> System.out.println(d));
    }

    @Test
    public void testQueryAuditProject(){
        List<AuditProject> auditProjects = mapper.queryAuditProject();
        auditProjects.forEach((d)-> System.out.println(d));
    }

    @Test
    public void testQueryFrozenProject(){
        service.queryFrozenProject().forEach((d)-> System.out.println(d));
    }

    @Test
    public void testQueryBill(){
        service.querybill().forEach((d)-> System.out.println(d));
    }

    @Test
    public void testExamine(){
        service.examine("tes1");
    }

    @Test
    public void testThawMoney(){
        try {
            service.thawMoney("tes1","20000","test1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddBill(){
        TbBill bill = new TbBill();
        bill.setBillId(BackStageUtils.getString(20));
        bill.setProjectId("tes1");
        bill.setBillCash(-200);
        bill.setBillDate(new java.sql.Timestamp(System.currentTimeMillis()));
        bill.setBillDescribe("测试");
        mapper.insertBill(bill);
    }
}
