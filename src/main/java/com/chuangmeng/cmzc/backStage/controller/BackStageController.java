package com.chuangmeng.cmzc.backStage.controller;

import com.chuangmeng.cmzc.backStage.service.IBackStageService;
import com.chuangmeng.cmzc.commons.VO.BackStage.AdminInfo;
import com.chuangmeng.cmzc.commons.VO.BackStage.AuditProject;
import com.chuangmeng.cmzc.commons.VO.BackStage.QueryBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/backstage")
public class BackStageController {

    @Autowired
    private IBackStageService service;

    @RequestMapping("/index")
    @ResponseBody
    public List<AdminInfo> index(){
        List<AdminInfo> adminInfos = service.queryBackStageAdminInfo();
        return adminInfos;
    }

    @RequestMapping("/checkProject")
    @ResponseBody
    public List<AuditProject> checkAuditProject(){
        List<AuditProject> auditProjects = service.queryAuditProject();
        return auditProjects;
    }

    @RequestMapping("/checkFrozen")
    @ResponseBody
    public List<AuditProject> checkFrozenProject(){
        List<AuditProject> auditProjects = service.queryFrozenProject();
        return auditProjects;
    }

    @RequestMapping("/checkBill")
    @ResponseBody
    public List<QueryBill> checkBill(){
        List<QueryBill> querybill = service.querybill();
        return querybill;
    }

    @RequestMapping("/examine/{projectId}")
    @ResponseBody
    public String examine(@PathVariable("projectId") String projectId){
        service.examine(projectId);
        return "ok";
    }

    @RequestMapping("/thawMoney/{value}")
    @ResponseBody
    public String thawMoney(@PathVariable("value") String value){
        String[] val = value.split("-");
        try {
            service.thawMoney(val[0],val[2],val[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
