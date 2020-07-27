package com.zp.football.controller;

import com.zp.football.process.JobProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/22 16:25
 */
@Controller
@RequestMapping("/")
public class TestController {


    @Autowired
    private JobProcess jobProcess;

    @RequestMapping(value = "run")
    public void runSpider(){
        String url = "http://odds.500.com/fenxi/shuju-424798.shtml";
        Spider.create(jobProcess)
                .addUrl(url)
                .thread(5)
                .run();
    }
}
