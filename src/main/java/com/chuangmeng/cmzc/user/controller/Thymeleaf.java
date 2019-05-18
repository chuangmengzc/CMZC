package com.chuangmeng.cmzc.user.controller;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*chuangmeng/templates/{html}*/
@RequestMapping("chuangmeng")
@Controller
public class Thymeleaf {
    @RequestMapping("templates/{html}")
    public String toHtml(@PathVariable("html") String html, HttpRequest request){
        return html;
    }
}
