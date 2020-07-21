package com.zp.football.test;

import com.zp.football.process.JobProcess;
import us.codecraft.webmagic.Spider;

public class TestClass {


    /**
     * 1。获取比赛信息
     */


    public static void main(String[] args) {
        String url = "http://live.500.com/wanchang.php?e=2014-01-01";
        Spider.create(new JobProcess())
                .addUrl(url)
                .thread(5)
                .run();
    }
}
