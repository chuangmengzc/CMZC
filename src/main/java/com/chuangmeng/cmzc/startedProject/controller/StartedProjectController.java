package com.chuangmeng.cmzc.startedProject.controller;

import com.chuangmeng.cmzc.commons.DIR;
import com.chuangmeng.cmzc.commons.dto.Project;
import com.chuangmeng.cmzc.commons.po.TbPackage;
import com.chuangmeng.cmzc.commons.po.TbProject;
import com.chuangmeng.cmzc.commons.po.TbProjectType;
import com.chuangmeng.cmzc.commons.utils.BackStageUtils;
import com.chuangmeng.cmzc.commons.vo.PackageImgsVo;
import com.chuangmeng.cmzc.startedProject.service.StartedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@Controller
@RequestMapping("/startedProject")
public class StartedProjectController {
    @Autowired
    private StartedProjectService startedProjectService;


    //添加项目
//    @ResponseBody
//    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
//    public  ResultVo  addProject(MultipartFile[] packagePic){
//        ResultVo resultVo=new ResultVo();
//        try {
//            System.out.println(packagePic[1].getOriginalFilename());
//            System.out.println(packagePic[0].getOriginalFilename());
//           resultVo.setCode("1");
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultVo.setCode("0");
//            resultVo.setMsg("项目发起失败!");
//        }
//          return resultVo;
//    }
//添加项目


@ResponseBody
@RequestMapping(value = "/addProject",method = RequestMethod.POST)
public  String   addProject(PackageImgsVo packageImgsVo, String TbPackage, Project project, MultipartFile[] projectImgs, MultipartFile[] projectPic,
                            MultipartFile projectTitlePic1, MultipartFile projectTitlePic2, String projectStartTime, String projectEndTime){
    try {
        TbProject tbProject=new TbProject();
        tbProject.setProjectName(project.getProjectName());
        tbProject.setTypeId(project.getTypeId());
        tbProject.setBusinessId("test1");
        tbProject.setProjectExpectMoney(project.getProjectExpectMoney());
//        String video = projectVideo.getOriginalFilename()+ RandString.generateString(20);
//        String video= BackStageUtils.uploadFile(projectVideo);
        StringBuilder imgs=new StringBuilder();
        StringBuilder pic=new StringBuilder();
        for(MultipartFile multipartFile:projectImgs){
//            imgs.append(multipartFile.getOriginalFilename()+RandString.generateString(20)+"|");
            imgs.append(BackStageUtils.uploadFile(multipartFile,DIR.PROJECT));
        }
        for(MultipartFile multipartFile:projectPic){
//            pic.append(multipartFile.getOriginalFilename()+RandString.generateString(20)+"|");
            pic.append(BackStageUtils.uploadFile(multipartFile,DIR.PROJECT));
        }
//        String pic1= projectTitlePic1.getOriginalFilename()+RandString.generateString(20);
        String pic1=BackStageUtils.uploadFile(projectTitlePic1,DIR.PROJECT);
//        String pic2= projectTitlePic2.getOriginalFilename()+RandString.generateString(20);
        String pic2=BackStageUtils.uploadFile(projectTitlePic2,DIR.PROJECT);
//        tbProject.setProjectVideo(video);
        tbProject.setProjectImgs(imgs.toString());
        tbProject.setProjectPic(pic.toString());
        tbProject.setProjectTitlePic1(pic1);
        tbProject.setProjectTitlePic2(pic2);
        String[] p=TbPackage.split("\\$");
        List<TbPackage> packages=new ArrayList<>();
        int i=1;
        for (String string:p){
            String[] f = string.split("\\|");
            TbPackage tbPackage=new TbPackage();
            tbPackage.setPackagePrice(Long.parseLong(f[1]));
            tbPackage.setPackageNum(Integer.parseInt(f[2]));
            tbPackage.setPackageInfo(f[3]);
//            tbPackage.setPackagePic(f[4]);
            String s;
            if(i==1){
                s = BackStageUtils.uploadFile(packageImgsVo.getPackagePic1(), DIR.PACKAGE);
            }else if(i==2){
               s = BackStageUtils.uploadFile(packageImgsVo.getPackagePic2(),DIR.PACKAGE);
            }else if(i==3){
                s = BackStageUtils.uploadFile(packageImgsVo.getPackagePic3(),DIR.PACKAGE);
            }else if(i==4){
                s = BackStageUtils.uploadFile(packageImgsVo.getPackagePic4(),DIR.PACKAGE);
            }else{
                s = BackStageUtils.uploadFile(packageImgsVo.getPackagePic5(),DIR.PACKAGE);
            }
            i++;
            tbPackage.setPackagePic(s);
            tbPackage.setPackageGift(f[4]);
            tbPackage.setPackagePostFee(f[5]);
            tbPackage.setPackageReturnTime(f[6]);
            packages.add(tbPackage);
        }
        startedProjectService.addProject(tbProject,packages,projectStartTime,projectEndTime);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "ok";
}

//    查询项目类型
    @RequestMapping("/queryType")
    public  String  queryType(Model model){
        try {
            List<TbProjectType> types = startedProjectService.queryType();
            model.addAttribute("types",types);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "startedproject/startedProject";
    }
}
