package com.chuangmeng.cmzc.evolve.service.Impl;

import com.chuangmeng.cmzc.commons.PO.TbProjectProgress;
import com.chuangmeng.cmzc.commons.RandString;
import com.chuangmeng.cmzc.evolve.dao.EvolveMapper;
import com.chuangmeng.cmzc.evolve.service.EvolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EvolveServiceImpl  implements EvolveService {

    @Autowired
    private EvolveMapper evolveMapper;
    @Override
    public void addEvolve(TbProjectProgress tbProjectProgress) throws Exception {
        if(null==tbProjectProgress){
            throw  new  NullPointerException("Can't find information to post");
        }
        String string = RandString.generateString(20);
        tbProjectProgress.setProgressId(string);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date time=df.parse(df.format(new Date()));
        tbProjectProgress.setProgressTime(time);
        evolveMapper.addEvolve(tbProjectProgress);

    }
}
