package com.chuangmeng.cmzc.evolve.controller;

import com.chuangmeng.cmzc.commons.DIR;
import com.chuangmeng.cmzc.commons.PO.TbProjectProgress;
import com.chuangmeng.cmzc.commons.utils.BackStageUtils;
import com.chuangmeng.cmzc.evolve.service.EvolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
@RequestMapping("evolve")
public class EvolveController {
    @Autowired
    private EvolveService evolveService;

    @RequestMapping(value = "/addEvolve",method = RequestMethod.POST)
    @ResponseBody
    public String  addEvolve(TbProjectProgress tbProjectProgress, MultipartFile progressPic1) throws Exception {
        String progressPic= BackStageUtils.uploadFile(progressPic1, DIR.PROJECT);
        tbProjectProgress.setProgressPic(progressPic);
        evolveService.addEvolve(tbProjectProgress);
            return "ok";
    }
}
