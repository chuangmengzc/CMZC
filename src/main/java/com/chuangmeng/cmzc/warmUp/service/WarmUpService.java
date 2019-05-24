package com.chuangmeng.cmzc.warmUp.service;

import com.chuangmeng.cmzc.commons.PO.TbProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarmUpService {
     void queryWarmUp(@Param("projectStatus") Long projectStatus) throws  Exception;
}
