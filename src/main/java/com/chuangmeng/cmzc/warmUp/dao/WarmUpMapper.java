package com.chuangmeng.cmzc.warmUp.dao;

import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.dto.FirFProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarmUpMapper {
     List<FirFProject> queryWarmUp(@Param("projectStatus") Long projectStatus);
}
