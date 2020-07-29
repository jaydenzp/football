package com.zp.football.controller;

import cn.hutool.core.io.IoUtil;
import com.zp.football.process.GameProcess;
import com.zp.football.process.JobProcess;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;

import java.io.*;
import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/22 16:25
 */
@Controller
@RequestMapping("/")
public class TestController {


    @Autowired
    private GameProcess gameProcess;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "run")
    public void runSpider() throws IOException {
        String startDate = environment.getProperty("startDate");
        String endDate = environment.getProperty("endDate");

        FileUtils.writeStringToFile(new File("date.txt"),"2020-020-1");
        List<String> list = FileUtils.readLines(new File("date.txt"));
        String url = "https://liansai.500.com/team/4285/teamlineup/";
        Spider.create(gameProcess)
                .addUrl(url)
                .thread(5)
                .run();
    }
}
