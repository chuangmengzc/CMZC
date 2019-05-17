package com.chuangmeng.cmzc.commons.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/{page}")
    public String forwardPage(@PathVariable("page") String page){
        return page;
    }

    @RequestMapping("/{dir}/{page}")
    public String forwardPage(@PathVariable("dir") String dir,@PathVariable("page") String page){
        return dir+"/"+page;
    }
}