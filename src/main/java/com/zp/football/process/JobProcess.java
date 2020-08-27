package com.zp.football.process;


import com.zp.football.domain.Game;
import com.zp.football.service.GameService;
import com.zp.football.utis.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GameService gameService;


    @Override
    public void process(Page page) {
        //getGameData(page);
        getGameDetailData(page);
        //getTeamPlayer(page);
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


    //获取分析数据

    public void getGameDetailData(Page page) {
        //获取赛事id
        String url = page.getUrl().get();
        String gameId = StringUtils.getEndOfNumParams(url);
        List<String> all = page.getHtml().$("a.hd_name").$("a", "text").all();
        //主队名称
        String homeTeam = all.get(0);
        //客队名称
        String visitingTeam =  all.get(2);;

        Selectable selectable = page.getHtml().xpath("//*[@id=\"team_jiaozhan\"]/div[1]/span");

        //胜次数 x胜
        String sl = selectable.$("em.red", "text").get();
        String hsheng = StringUtils.getPrefixNumberText(sl);

        //String winTimes = StringUtils.getPrefixNumberText(selectable.$("em.red", "text").get());

        //平次数 x平
        String pl = selectable.$("em.green", "text").get();
        String hping = StringUtils.getPrefixNumberText(pl);

        //String drawTimes = StringUtils.getPrefixNumberText(selectable.$("em.green", "text").get());

        //负次数 x负
        String fl = selectable.$("em.blue", "text").get();
        String hfu = StringUtils.getPrefixNumberText(fl);

        //String loseTimes = StringUtils.getPrefixNumberText(selectable.$("em.blue", "text").get());

        //获取全部信息
        String xinxi = selectable.$("span.his_info", "text").get();
        List<String> strings = StringUtils.spitWithQuotationMark(xinxi, "，");
        if(strings.size()>=4) {
            //小球次数
            String prefixNumberText1 = StringUtils.getPrefixNumberText(strings.get(strings.size() - 1));
            //大球次数
            String prefixNumberText2 = StringUtils.getPrefixNumberText(strings.get(strings.size() - 2));
            //失球个数
            String prefixNumberText3 = StringUtils.getPrefixNumberText(strings.get(strings.size() - 3));
            //进球个数
            String prefixNumberText4 = StringUtils.getPrefixNumberText(strings.get(strings.size() - 4));
        }

        //主队近10场胜负平
        //胜 x胜
        String  ww = page.getHtml().xpath("//*[@id=\"zhanji_01\"]/div[3]/div/p/span[1]/span[1]").$("span","text").get();
        String htensheng = StringUtils.getPrefixNumberText(ww);

        //ping x ping
        String  dd = page.getHtml().xpath("//*[@id=\"zhanji_01\"]/div[3]/div/p/span[1]/span[2]").$("span","text").get();
        String htenping = StringUtils.getPrefixNumberText(dd);
        //负 x fu
        String  ll = page.getHtml().xpath("//*[@id=\"zhanji_01\"]/div[3]/div/p/span[1]/span[3]").$("span","text").get();
        String htenfu = StringUtils.getPrefixNumberText(ll);

        //主队近10场进球数  x qiu
        //*[@id="zhanji_01"]/div[3]/div/p/span[2]/span[1]
        String  jj = page.getHtml().xpath("//*[@id=\"zhanji_01\"]/div[3]/div/p/span[2]/span[1]").$("span","text").get();
        String htengoals = StringUtils.getPrefixNumberText(jj);
        //主队近10场失球数 x qiu
        //*[@id="zhanji_01"]/div[3]/div/p/span[2]/span[2]
        String  ss = page.getHtml().xpath("//*[@id=\"zhanji_01\"]/div[3]/div/p/span[2]/span[2]").$("span","text").get();
        String htenlosegoals = StringUtils.getPrefixNumberText(ss);


        //客队近十场胜负平
        //胜 x sheng
        //*[@id="zhanji_00"]/div[3]/div/p/span[1]/span[1]
        String  kww = page.getHtml().xpath("//*[@id=\"zhanji_00\"]/div[3]/div/p/span[1]/span[1]").$("span","text").get();
        String kww2 = StringUtils.getPrefixNumberText(kww);

        //平 xping
        //*[@id="zhanji_00"]/div[3]/div/p/span[1]/span[2]
        String  kdd= page.getHtml().xpath("//*[@id=\"zhanji_00\"]/div[3]/div/p/span[1]/span[2]").$("span","text").get();
        String kdd2 = StringUtils.getPrefixNumberText(kdd);

        //负 x fu
        //*[@id="zhanji_00"]/div[3]/div/p/span[1]/span[3]
        String  kll= page.getHtml().xpath("//*[@id=\"zhanji_00\"]/div[3]/div/p/span[1]/span[3]").$("span","text").get();
        String kll2 = StringUtils.getPrefixNumberText(kll);

        //客队近10场进球数  //*[@id="zhanji_00"]/div[3]/div/p/span[2]/span[1]
        String goal10= page.getHtml().xpath("//*[@id=\"zhanji_00\"]/div[3]/div/p/span[2]/span[1]").$("span","text").get();

        //客队近10场失球数 //*[@id="zhanji_00"]/div[3]/div/p/span[2]/span[2]
        String lose10= page.getHtml().xpath("//*[@id=\"zhanji_00\"]/div[3]/div/p/span[2]/span[2]").$("span","text").get();

        //主队联赛胜率 xx%
        String s1 = page.getHtml().$("body > div.odds_content > div:nth-child(2) > div.M_content > div.team_a > table > tbody > tr:nth-child(2) > td:nth-child(11)","text").get();
        //主队联赛主场胜率 xx%
        String s2 = page.getHtml().$("body > div.odds_content > div:nth-child(2) > div.M_content > div.team_a > table > tbody > tr.tr3 > td:nth-child(11)","text").get();
        //主队联赛客场胜率 xx%
        String s3 = page.getHtml().$("body > div.odds_content > div:nth-child(2) > div.M_content > div.team_a > table > tbody > tr:nth-child(4) > td:nth-child(11)","text").get();
        //客队联赛胜率 xx%
        String s4 = page.getHtml().$("body > div.odds_content > div:nth-child(2) > div.M_content > div.team_b > table > tbody > tr:nth-child(2) > td:nth-child(11)","text").get();

        //客队联赛主场胜率 xx%
        String s5 = page.getHtml().$("body > div.odds_content > div:nth-child(2) > div.M_content > div.team_b > table > tbody > tr:nth-child(3) > td:nth-child(11)","text").get();
        //客队联赛客场胜率 xx%
        String s6 = page.getHtml().$("body > div.odds_content > div:nth-child(2) > div.M_content > div.team_b > table > tbody > tr.tr3 > td:nth-child(11)","text").get();

        //主队联赛平均进球 x.xx球
        String s7 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_a > table > tbody > tr.tr1 > td:nth-child(2)","text").get();
        //主队主场平均进球 x.xx 球
        String s8 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_a > table > tbody > tr.tr1 > td:nth-child(3)","text").get();
        //主队联赛平均失球 x.xx 球
        String s9 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_a > table > tbody > tr.tr2 > td:nth-child(2)","text").get();
        //主队主场平均失球 x.xx 球
        String s10 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_a > table > tbody > tr.tr2 > td:nth-child(3)","text").get();

        //客队联赛平均进球 x.xx qiu
        String s11 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_b > table > tbody > tr.tr1 > td:nth-child(2)","text").get();

        //客队联赛客场平均进球 x.xxqiu
        String s12 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_b > table > tbody > tr.tr1 > td:nth-child(4)","text").get();
        //客队联赛平均失球 x.xxqiu
        String s13 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_b > table > tbody > tr.tr2 > td:nth-child(2)", "text").get();

        //客队联赛客场平均失球 x.xx qiu
        String s14 = page.getHtml().$("body > div.odds_content > div:nth-child(6) > div.M_content > div.team_b > table > tbody > tr.tr2 > td:nth-child(4)","text").get();
        System.out.println(gameId);
    }


    //获取比赛数据
     public void getGameData(Page page) {
         //获取页面数据
         List<Selectable> nodes = page.getHtml().$("tbody:eq(1)").nodes();
         Selectable tr = nodes.get(0).$("tr");
         List<Selectable> nodes1 = tr.nodes();

         for (Selectable selectable: nodes1
                 ) {
             //获取赛事id
            // String gameId = selectable.$("tr", "id").get();
             String gameId = StringUtils.getEndOfUrlParams(selectable.$("td:eq(5)").links().get());
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
             game.setAnalysisUrl(analysisUrl);
             Game game1 = gameService.create(game);
             System.out.println(game1);
         }
     }

    //获取球员数据
    public void getTeamPlayer(Page page) {
        List<Selectable> nodes = page.getHtml().$("body > div.lwrap > div > div > div.lcontent_full > div").nodes();;
        List<Selectable> trs = nodes.get(0).$("tr").nodes();
        //球队id
        //*[@id="link125"]
        String url = page.getHtml().$("body > div.lwrap > div > div > div.lsnav_qdnav.clearfix > ul > li.on").links().get();
        if(StringUtils.isNullorBlank(url)){
           return;
        }else {
            String[] split1 = url.split("/");
            String teamId = split1[split1.length - 2];
        }

        for (Selectable selectable: trs
                ) {
            //去掉第一个tr，第一个tr是表头信息
            String text = selectable.$("td:eq(1)", "text").get();
            if(text!=null && text.length()>0){
                //判断第一个是否有空格，不显示位置
                if(selectable.$("td").nodes().size()<15){
                    //如果第一个值为空，则表明为后面同类型的
                    //号码
                    String teamNum = selectable.$("td:eq(0)", "text").get();
                    //球员名
                    String name = selectable.$("td:eq(2)>span>a","text").get();
                    //位置
                    String position = selectable.$("td:eq(3)","text").get();
                    //年龄
                    String age = selectable.$("td:eq(4)","text").get();
                    //身高 181cm
                    String high = selectable.$("td:eq(5)","text").get();
                    String high2 = StringUtils.getPrefixNumberText(high);
                    //体重 69kg
                    String weight = selectable.$("td:eq(6)","text").get();
                    String weight2 = StringUtils.getPrefixNumberText(weight);
                    //出场次数
                    String appearance = selectable.$("td:eq(7)>span","text").get();
                    //出场时间
                    String appearanceTime = selectable.$("td:eq(8)>span","text").get();
                    //进球
                    String goals = selectable.$("td:eq(9)>span","text").get();
                    //助攻
                    String assists = selectable.$("td:eq(10)>span","text").get();
                    //黄牌
                    String yellow = selectable.$("td:eq(11)>span","text").get();
                    //红牌
                    String red = selectable.$("td:eq(12)>span","text").get();
                    //身价 带欧元符号
                    String worth = selectable.$("td:eq(13)>span:eq(0)","text").get();
                    String worth2 = StringUtils.getPrefixNumberText(worth);
                }else {
                    //号码
                    String teamNum = selectable.$("td:eq(1)", "text").get();
                    //球员名
                    String name = selectable.$("td:eq(3)>span>a","text").get();
                    //位置
                    String position = selectable.$("td:eq(4)","text").get();
                    //年龄
                    String age = selectable.$("td:eq(5)","text").get();
                    //身高 181cm
                    String high = selectable.$("td:eq(6)","text").get();
                    //体重 69kg
                    String weight = selectable.$("td:eq(7)","text").get();
                    //出场次数
                    String appearance = selectable.$("td:eq(8)>span","text").get();
                    //出场时间
                    String appearanceTime = selectable.$("td:eq(9)>span","text").get();
                    //进球
                    String goals = selectable.$("td:eq(10)>span","text").get();
                    //助攻
                    String assists = selectable.$("td:eq(11)>span","text").get();
                    //黄牌
                    String yellow = selectable.$("td:eq(12)>span","text").get();
                    //红牌
                    String red = selectable.$("td:eq(13)>span","text").get();
                    //身价
                    String worth = selectable.$("td:eq(14)>span:eq(0)","text").get();
                    String worth2 = StringUtils.getPrefixNumberText(worth);

                }
            }
        }

    }
    
}

