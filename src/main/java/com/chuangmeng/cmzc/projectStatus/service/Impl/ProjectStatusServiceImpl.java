package com.chuangmeng.cmzc.projectStatus.service.Impl;

import com.chuangmeng.cmzc.commons.PO.TbBill;
import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.RandString;
import com.chuangmeng.cmzc.commons.dto.UserAndMoney;
import com.chuangmeng.cmzc.projectStatus.dao.ProjectStatusMapper;
import com.chuangmeng.cmzc.projectStatus.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {

    @Autowired
    private ProjectStatusMapper projectStatusMapper;
    //商家取消项目
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void projectCancellation(String order1pare1) throws Exception {
        if(null==order1pare1||"".equals(order1pare1)){
            throw  new NullPointerException("No item was found to cancel");
        }
        //查询该项目下的所有订单的总金额
        Integer allMoney = projectStatusMapper.queryAllMoney(order1pare1);
        //查询该项目下的所有订单对应的用户及用户的钱
        List<UserAndMoney> userAndMonies = projectStatusMapper.queryUserAndMoney(order1pare1);
        for(UserAndMoney userAndMoney:userAndMonies){
            //退款给用户
            projectStatusMapper.refund(userAndMoney.getOrderMoney(),userAndMoney.getUserId());
        }
        TbBill tbBill=new TbBill();
        tbBill.setBillId(RandString.generateLowerString(20));
        tbBill.setProjectId(order1pare1);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        tbBill.setBillDate(timestamp);
        tbBill.setBillCash(allMoney*(-1));
        tbBill.setBillDescribe("项目取消退款");
        //增加一个流水
        projectStatusMapper.addBill(tbBill);

//        if(true){
//            throw new  NullPointerException("故意的！");
//        }
        //修改所有的订单的状态
        projectStatusMapper.updateOrderStatus(3,order1pare1);
        //修改项目的状态
        projectStatusMapper.updateProjectStatus(order1pare1);
    }

    //众筹结束
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void kickEnd() throws Exception {
        List<TbProject> projects = projectStatusMapper.queryProject();
        if(null==projects||projects.isEmpty()){
            return;
        }
        for(TbProject tbProject:projects){
            long nowTime=new Date().getTime();
            if(tbProject.getProjectEndTime().getTime()<=nowTime){
                projectStatusMapper.updateStatus(tbProject.getProjectId());
//                if(1==1){
//                    throw new  Exception("故意的");
//                }
                int money = (int) tbProject.getProjectRealMoney();
                int wallet= (int) (money*0.6);
                int frozen= (int) (money*0.3);
                projectStatusMapper.giveMonet(wallet , frozen,tbProject.getBusinessId());
                TbBill tbBill=new TbBill();
                tbBill.setBillId(RandString.generateString(20));
                tbBill.setProjectId(tbProject.getProjectId());
                Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                tbBill.setBillDate(timestamp);
                tbBill.setBillDescribe("给商家60%研发经费！");
                tbBill.setBillCash(wallet*(-1));
                projectStatusMapper.addBill(tbBill);
                projectStatusMapper.orderLose(tbProject.getProjectId());
            }
        }
    }

    //项目完成
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void projectCompletion(String projectId) throws Exception {
        if(null==projectId||"".equals(projectId)){
            throw  new NullPointerException("No item was found to cancel");
        }
        TbProject tbProject = projectStatusMapper.queryProjectById(projectId);
        projectStatusMapper.updateStatus2(projectId);
         projectStatusMapper.updateOrderStatus2(projectId);
        int money = (int) tbProject.getProjectRealMoney();
        int wallet= (int) (money*0.3);
        projectStatusMapper.giveMonet(wallet,wallet*(-1),tbProject.getBusinessId());
        TbBill tbBill=new TbBill();
        tbBill.setBillId(RandString.generateString(20));
        tbBill.setProjectId(tbProject.getProjectId());
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        tbBill.setBillDate(timestamp);
        tbBill.setBillDescribe("尾款的30%返给商家！");
        tbBill.setBillCash(wallet*(-1));
        projectStatusMapper.addBill(tbBill);

    }
// 项目返利正在进行中
    @Override
    public void rebate(String projectId) throws Exception {
        if(null==projectId||"".equals(projectId)){
        throw  new NullPointerException("No item was found to cancel");
    }
        projectStatusMapper.updateStatus3(projectId);
}

//开始众筹
    @Override
    public void start() throws Exception {

        List<TbProject> tbProjects = projectStatusMapper.queryStart();
        for(TbProject projectIds:tbProjects){
            long nowTime=new Date().getTime();
            if(projectIds.getProjectStartTime().getTime()<=nowTime){
                projectStatusMapper.start(projectIds.getProjectId());
            }
        }

    }


}
