package com.chuangmeng.cmzc.user.dao;

import com.chuangmeng.cmzc.commons.PO.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    TbUser selectUserByTel(TbUser tbUser);
    void useradd(TbUser tbUser);
}
