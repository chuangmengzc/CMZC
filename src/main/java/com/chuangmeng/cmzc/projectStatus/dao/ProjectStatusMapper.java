package com.chuangmeng.cmzc.projectStatus.dao;

import com.chuangmeng.cmzc.commons.PO.TbBill;
import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.dto.UserAndMoney;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectStatusMapper {
    //查询该项目下所有订单的所有总金额
      Integer queryAllMoney(@Param("orderSpare1") String orderSpare1);
      //查询该项目下所有订单的用户和该用户花的钱
     List<UserAndMoney>  queryUserAndMoney(@Param("orderSpare1") String orderSpare1);
     //修改用户的钱包
      void refund(@Param("orderMoney") Integer orderMoney,@Param("userId") String userID);
      //修改该项目下的所有订单状态
      void updateOrderStatus(@Param("status") Integer status,@Param("orderSpare1") String orderSpare1);
      //增加一个流水
      void addBill(TbBill tbBill);
      //修改项目的状态为众筹失败
     void updateProjectStatus(@Param("projectId") String projectId);

     //查询所有在众筹中的项目
    List<TbProject> queryProject();
    // 修改项目的状态为众筹结束
    void updateStatus(@Param("projectId") String projectId);

    //众筹结束给商家钱
    void giveMonet(@Param("wallet") Integer wallet,@Param("frozen") Integer frozen,@Param("businessId") String businessId);

    //根据Id查询项目
    TbProject  queryProjectById(@Param("projectId") String projectId);
    // 修改项目的状态为项目完成
    void updateStatus2(@Param("projectId") String projectId);
    //修改该项目下的所有订单状态为已完成
    void updateOrderStatus2(@Param("orderSpare1") String orderSpare1);
    // 修改项目的状态为返利中
    void updateStatus3(@Param("projectId") String projectId);

    //项目众筹结束  使未付款的订单为失效
    void orderLose(@Param("projectId") String projectId);

    //开始众筹
   void  start(@Param("projectId") String projectId);
    List<TbProject>  queryStart();
}


