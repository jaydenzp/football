package com.zp.football.process;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.xsoup.XPathEvaluator;
import us.codecraft.xsoup.Xsoup;

import java.util.List;

@Component
public class JobProcess implements PageProcessor {


    @Override
    public void process(Page page) {
        //获取页面数据
        List<Selectable> nodes = page.getHtml().$("tbody:eq(1)").nodes();
        Selectable tr = nodes.get(0).$("tr");
        List<Selectable> nodes1 = tr.nodes();

        for (Selectable selectable: nodes1
             ) {
            //获取赛事id
            String gameId = selectable.$("tr", "id").get();
            //获取赛事
            String gameName = selectable.$("td:eq(0)>a","text").get();
            //伦次
            String rounds = selectable.$("td:eq(1)","text").get();
            //比赛时间
            String gameTime = selectable.$("td:eq(2)","text").get();
            //状态
            String status = selectable.$("td:eq(3)>span","text").get();
            //主队
            String homeTeam = selectable.$("td:eq(4)>a>span","text").get();
            //主队链接
            String homeTeamUrl = selectable.$("td:eq(4)").links().get();
            //比分
            String score1 = selectable.$("td:eq(5)>div>a:eq(0)","text").get();
            String score2 = selectable.$("td:eq(5)>div>a:eq(2)","text").get();
            //客队
            String visitingTeam = selectable.$("td:eq(6)>a>span","text").get();
            //客队链接
            String visitingTeamUrl = selectable.$("td:eq(6)").links().get();
            //半场
            String halfCourt = selectable.$("td:eq(7)","text").get();
            //分析url
            String analysisUrl = selectable.$("td:eq(8)").links().get();
        }
    }

    //设置
    private Site site = Site.me()
            .setCharset("gbk")//设置编码
            .setTimeOut(10 * 1000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试的间隔时间
            .setRetryTimes(3)//设置重试的次数
            .addHeader("Connection","keep-alive")
            .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
            .addHeader("Accept-Encoding","gzip, deflate")
            .addHeader("Accept-Language:","zh-CN,zh;q=0.9")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");


    @Override
    public Site getSite() {
        return site;
    }

}

