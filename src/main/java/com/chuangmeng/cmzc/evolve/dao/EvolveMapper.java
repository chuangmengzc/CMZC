package com.chuangmeng.cmzc.evolve.dao;

import com.chuangmeng.cmzc.commons.PO.TbProjectProgress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvolveMapper {
   void addEvolve(TbProjectProgress tbProjectProgress);
}
