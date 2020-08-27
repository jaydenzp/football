package com.zp.football.porxy;

import com.zp.football.domain.DataDictionary;
import com.zp.football.service.DataDictionaryService;
import com.zp.football.service.impl.DataDictionaryServiceImpl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/8/4 14:16
 */
@Component
public class GetProxyIP implements PageProcessor {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Scheduled(cron = "0 0/15 * * * ?")
    public  void update() {
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setDateType("proxy");
        List<DataDictionary> all = dataDictionaryService.findAll(dataDictionary);
        //从数据库中查询ip
        for (DataDictionary dataDictionary1:all
             ) {
            if (ifUseless(dataDictionary1.getCode(),dataDictionary1.getValue())) {

                System.err.println(dataDictionary1.getCode() + "从数据库中删除");
                //删除数据库中的数据
                dataDictionaryService.deleteById(dataDictionary1.getId());
            }
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void runGetPorxyIps(){
        Spider.create(this)
                .addUrl("https://ip.jiangxianli.com/country/%E4%B8%AD%E5%9B%BD?country=%E4%B8%AD%E5%9B%BD")
                .thread(1)
                .run();
    }

    public void ips(Page page) {
        //获取ips
        Selectable sel = page.getHtml().$("body > div.layui-layout.layui-layout-admin > div.layui-row > div.layui-col-md9.ip-tables > div.layui-form > table > tbody");
        List<Selectable> nodes = sel.$("tr").nodes();
        for (Selectable selectable: nodes
             ) {
            //去掉表头
                String ip = selectable.$("td:eq(0)","text").get();
                String port = selectable.$("td:eq(1)","text").get();
                String type = selectable.$("td:eq(3)","text").get();
                DataDictionary dataDictionary = new DataDictionary();
                dataDictionary.setDateType("proxy");
                dataDictionary.setCode(ip);
                dataDictionary.setValue(port);
                dataDictionary.setDescription(type);
                dataDictionaryService.create(dataDictionary);
                //添加到数据库中
        }
    }

    //无效的ip 返回true 有效的ip返回false
    boolean ifUseless(String ip,String port) {
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
            InetSocketAddress addr = new InetSocketAddress(ip,Integer.parseInt(port));
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            InputStream in = null;
            try {
                URLConnection conn = url.openConnection(proxy);
                conn.setConnectTimeout(5000);
                in = conn.getInputStream();
            } catch (Exception e) {
                return true;
            }
            String s = IOUtils.toString(in);
            if (s.indexOf("baidu") > 0) {
                System.err.println("----------------有效--------------------");
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }
    @Override
    public void process(Page page) {
        ips(page);
    }

    @Override
    public Site getSite() {
        return site;
    }

    //设置
    private Site site = Site.me()
            .setCharset("utf-8")//设置编码
            .setTimeOut(10 * 1000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试的间隔时间
            .setRetryTimes(3)//设置重试的次数
            .setSleepTime(1000)
            .addHeader("Connection","keep-alive")
            .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
            .addHeader("Accept-Encoding","gzip, deflate")
            .addHeader("Accept-Language:","zh-CN,zh;q=0.9")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");

    public static void main(String[] args) throws IOException {
        /*Spider.create(new GetProxyIP())
                .addUrl("https://ip.jiangxianli.com/country/%E4%B8%AD%E5%9B%BD?country=%E4%B8%AD%E5%9B%BD")
                .thread(1)
                .run();*/
        //testIp();
    }


    public static void  testIp() throws IOException {
        //Proxy类代理方法
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
            // 创建代理服务器
            InetSocketAddress addr=null;
            addr=new InetSocketAddress("121.69.26.14",8080);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
            URLConnection conn = url.openConnection(proxy);
            InputStream in = conn.getInputStream();
            String s = IOUtils.toString(in);
            //System.out.println(s);
            if(s.indexOf("百度")>0){
                System.out.println("ok");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
