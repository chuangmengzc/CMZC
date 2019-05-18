package com.chuangmeng.cmzc.startedProject.service.Impl;

import com.chuangmeng.cmzc.commons.PO.TbPackage;
import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.PO.TbProjectType;
import com.chuangmeng.cmzc.commons.utils.RandString;
import com.chuangmeng.cmzc.startedProject.dao.StartedProjectMapper;
import com.chuangmeng.cmzc.startedProject.service.StartedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StartedProjectServiceImpl implements StartedProjectService {

    @Autowired
    private StartedProjectMapper startedProjectMapper;



//    添加项目和套餐
    @Override
    public void addProject(TbProject tbProject, List<TbPackage> packageList , String projectStartTime, String projectEndTime) throws  Exception {

        if(null==tbProject||null==packageList||packageList.isEmpty()){
            throw  new NullPointerException("The information to be added could not be found!");
        }
         //项目ID
        String projectId=RandString.generateString(20);
        tbProject.setProjectId(projectId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        String startT=projectStartTime.replace("/","-");
//        String endT=projectEndTime.replace("/","-");
        Date start = sdf.parse(projectStartTime);//项目开始时间
        Date end = sdf.parse(projectEndTime);//项目结束时间
        tbProject.setProjectStartTime(start);
        tbProject.setProjectEndTime(end);
        startedProjectMapper.addProject(tbProject);//添加项目
        for(TbPackage tbPackage:packageList){
            //套餐Id
            String packageId=RandString.generateString(20);
            tbPackage.setPackageId(packageId);
            tbPackage.setProjectId(projectId);
            tbPackage.setPackageResidue(tbPackage.getPackageNum());
            startedProjectMapper.addPackage(tbPackage);//添加套餐
        }
    }

    @Override
    public List<TbProjectType> queryType() throws Exception{
        List<TbProjectType> types = startedProjectMapper.queryType();
        if(null==types||types.isEmpty()){
            throw  new  NullPointerException("The information you inquired for was not found!");
        }
        return types;
    }


}
