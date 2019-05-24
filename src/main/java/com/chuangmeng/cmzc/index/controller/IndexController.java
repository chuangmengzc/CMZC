package com.chuangmeng.cmzc.index.controller;

import com.chuangmeng.cmzc.commons.PO.TbProject;
import com.chuangmeng.cmzc.commons.PO.TbProjectType;
import com.chuangmeng.cmzc.commons.PO.TbStory;
import com.chuangmeng.cmzc.commons.dto.EndProject;
import com.chuangmeng.cmzc.commons.dto.FProjectType;
import com.chuangmeng.cmzc.commons.dto.FirFProject;
import com.chuangmeng.cmzc.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/index")
public class IndexController{

    @Autowired
    private IndexService indexService;
    @RequestMapping("/show")
    public String showIndex(Model model) throws Exception {
            List<TbProject> hots = indexService.queryHot();
            List<TbProject> news = indexService.queryNew();
            List<EndProject> ends = indexService.queryEnd();
            List<FProjectType> types = indexService.queryType();
            List<FirFProject> starts = indexService.queryStart();
            List<TbStory> stories = indexService.queryStory();
            List<TbProjectType> allTypes = indexService.queryAllType();
            model.addAttribute("hots",hots);
            model.addAttribute("news",news);
            model.addAttribute("ends",ends);
            model.addAttribute("types",types);
            model.addAttribute("starts",starts);
            model.addAttribute("stories",stories);
            model.addAttribute("allTypes",allTypes);
        return "index/index";
    }

    @RequestMapping("/search")
    public String search(String categoryId, String keyword,Model model){
        try {
            List<FirFProject> search = indexService.search(categoryId, keyword);
            model.addAttribute("search",search);
//            for(FirFProject firFProject:search){
//                System.out.println(firFProject.getProjectName());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }
}
