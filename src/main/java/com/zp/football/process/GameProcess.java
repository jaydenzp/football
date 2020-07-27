package com.zp.football.process;

import com.zp.football.domain.Game;
import com.zp.football.pipeline.DataProcessPipeline;
import com.zp.football.utis.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 16:08
 */
@Component
public class GameProcess implements PageProcessor {

    @Autowired
    private DataProcessPipeline dataProcessPipeline;


    private String url = "http://live.500.com/wanchang.php?e=";
   /* public void process() {
        //访问入口url地址
        String url = "https://search.51job.com/list/000000,000000,0000,01%252C32,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        Spider.create(new GameProcess())
                .addUrl(url)
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .thread(5)
                .run();
    }*/

    @Override
    public void process(Page page) {

        //获取页面数据
        page.getUrl();


        //获取比赛详细信息


        //获取球员详细信息


        //添加url

        //判断nodes是否为空
        /*if (nodes.isEmpty()) {
            try {
                //如果为空，表示这是招聘信息详情页保存信息详情
                this.saveJobInfo(page);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            //如果有值，表示这是招聘信息列表页
            for (Selectable node : nodes) {
                //获取招聘信息详情页url
                String jobUrl = node.links().toString();
                //添加到url任务列表中，等待下载
                page.addTargetRequest(jobUrl);

                //获取翻页按钮的超链接
                List<String> listUrl = page.getHtml().$("div.p_in li.bk").links().all();
                //添加到任务列表中
                page.addTargetRequests(listUrl);

            }
        }*/
    }
    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100)
    public void process() {
        String date = "2014-01-01";
        String newUrl = url+date;
        Spider.create(new GameProcess())
                .addUrl(newUrl)
                .addPipeline(this.dataProcessPipeline)
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .thread(5)
                .run();
    }

    @Override
    public Site getSite() {
        return site;
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



    /**
     * 解析页面，获取比赛信息
     *
     * @param
     */
    private void saveJobInfo(Page page) {
        //获取页面数据
        List<Selectable> nodes = page.getHtml().$("tbody:eq(1)").nodes();
        Selectable tr = nodes.get(0).$("tr");
        List<Selectable> nodes1 = tr.nodes();

        for (Selectable selectable : nodes1
                ) {
            //获取赛事id
            String gameId = selectable.$("tr", "id").get();
            //获取赛事
            String gameName = selectable.$("td:eq(0)>a", "text").get();
            //伦次
            String rounds = selectable.$("td:eq(1)", "text").get();
            //比赛时间
            String gameTime = selectable.$("td:eq(2)", "text").get();
            //状态
            String status = selectable.$("td:eq(3)>span", "text").get();
            //主队
            String homeTeam = selectable.$("td:eq(4)>a>span", "text").get();
            //主队链接
            String homeTeamUrl = selectable.$("td:eq(4)").links().get();
            //比分
            String score1 = selectable.$("td:eq(5)>div>a:eq(0)", "text").get();
            String score2 = selectable.$("td:eq(5)>div>a:eq(2)", "text").get();
            //客队
            String visitingTeam = selectable.$("td:eq(6)>a>span", "text").get();
            //客队链接
            String visitingTeamUrl = selectable.$("td:eq(6)").links().get();
            //半场
            String halfCourt = selectable.$("td:eq(7)", "text").get();
            //分析url
            String analysisUrl = selectable.$("td:eq(8)").links().get();

            Game game = new Game();
            game.setGameId(gameId);
            game.setGameName(gameName);
            game.setGameTime(gameTime);
            game.setStatus(status);
            game.setHomeTeam(homeTeam);
            game.setHomeTeamUrl(homeTeamUrl);
            game.setHomeTeamScore(StringUtils.myparseInt(score1));
            game.setVisitingTeamScore(StringUtils.myparseInt(score2));
            game.setVisitingTeam(visitingTeam);
            game.setVisitingTeamUrl(visitingTeamUrl);
            game.setHalfScore(halfCourt);
            //保存数据
            page.putField("game", game);
        }
    }
}

