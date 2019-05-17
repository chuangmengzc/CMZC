package com.chuangmeng.cmzc.backStage.dao;

import com.chuangmeng.cmzc.commons.PO.TbBill;
import com.chuangmeng.cmzc.commons.VO.BackStage.AdminInfo;
import com.chuangmeng.cmzc.commons.VO.BackStage.AuditProject;
import com.chuangmeng.cmzc.commons.VO.BackStage.QueryBill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface IBackStageMapper {
    List<AdminInfo> queryBackStageAdminInfo();
    List<AuditProject> queryAuditProject();
    List<AuditProject> queryFrozenProject();
    List<QueryBill> queryBill();
    void changeProjectStatus(@Param("status") int status,@Param("projectId") String projectId);
    void businessGetMoney(@Param("FrozenMoney") int FrozenMoney,@Param("businessId") String businessId);
    void insertBill(TbBill bill);
}
