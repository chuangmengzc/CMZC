package com.chuangmeng.cmzc.projectStatus.controller;

import com.chuangmeng.cmzc.projectStatus.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/projectStatus")
public class ProjectStatusController {
    @Autowired
    ProjectStatusService projectStatusService;

    //商家取消项目众筹
    @ResponseBody
    @RequestMapping(value ="/status/{projectId}",method = RequestMethod.GET)
    public String  Status(@PathVariable("projectId") String projectId){
        try {
            projectStatusService.projectCancellation(projectId);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //项目完成并且已经返利给用户
    @ResponseBody
    @RequestMapping(value ="/status2/{projectId}",method = RequestMethod.GET)
    public String   projectCompletion(@PathVariable("projectId") String projectId){
        try {
            projectStatusService.projectCompletion(projectId);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //项目完成正在返利给用户
    @ResponseBody
    @RequestMapping(value ="/rebate/{projectId}",method = RequestMethod.GET)
    public String   rebate(@PathVariable("projectId") String projectId){
        try {
            projectStatusService.rebate(projectId);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
