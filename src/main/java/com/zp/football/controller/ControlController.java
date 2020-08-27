package com.zp.football.controller;

import cn.hutool.core.io.IoUtil;
import com.zp.football.domain.DataDictionary;
import com.zp.football.domain.DataDictionnaryType;
import com.zp.football.pipeline.DataProcessPipeline;
import com.zp.football.porxy.GetProxyIP;
import com.zp.football.process.GameProcess;
import com.zp.football.process.JobProcess;
import com.zp.football.service.DataDictionaryService;
import com.zp.football.service.DataDictionnaryTypeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: zhanpeng
 * @Date: 2020/7/22 16:25
 */
@RestController
@RequestMapping("/")
public class ControlController {


    @Autowired
    private GameProcess gameProcess;

    @Autowired
    private Environment environment;

    @Autowired
    private JobProcess jobProcess;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private DataDictionnaryTypeService dataDictionnaryTypeService;

    @Autowired
    private DataProcessPipeline dataProcessPipeline;

    @Autowired
    private GetProxyIP getProxyIP;

    @RequestMapping(value = "run")
    public void runSpider() throws IOException {

        //读取开始时间
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setCode("startDate");
        List<DataDictionary> all = dataDictionaryService.findAll(dataDictionary);
        // 配置混播代理
        System.out.println("初始化代理");
        System.out.println("初始化代理");
        System.out.println("初始化代理");
        DataDictionary proxyDataDictionary = new DataDictionary();
        proxyDataDictionary.setDateType("proxy");
        List<DataDictionary> proxyDataDictionarys = dataDictionaryService.findAll(proxyDataDictionary);
        //从数据库中查询ip
        List<Proxy> proxies = new ArrayList<>();
        for (int i=0;i<proxyDataDictionarys.size();i++
             ) {
            Proxy proxy = new Proxy(proxyDataDictionarys.get(i).getCode(), Integer.parseInt(proxyDataDictionarys.get(i).getValue()));
            if(!ifUseless(proxyDataDictionarys.get(i).getCode(),proxyDataDictionarys.get(i).getValue())){
                proxies.add(proxy);
            }
        }
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy(proxies.get(0).getHost(),proxies.get(0).getPort())
                ,new Proxy(proxies.get(1).getHost(),proxies.get(1).getPort())));
        if(all!=null && all.size()>0) {
            DataDictionary dataDictionary1 = all.get(0);
            String url = "https://live.500.com/wanchang.php?e=";
            Spider.create(gameProcess)
                    .addUrl(url+dataDictionary1.getValue())
                    .thread(10)
                    .addPipeline(dataProcessPipeline)
                    .setDownloader(httpClientDownloader)
                    .start();
        }
    }

    //设置开始时间与结束时间
    @RequestMapping(value = "setting_date")
    public String settingDate(@RequestParam(value = "startDate") String startDate,@RequestParam(value = "endDate") String endDate){
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setDateType("time");
        dataDictionary.setCode("startDate");
        dataDictionary.setValue(startDate);
        DataDictionary dataDictionary2 = new DataDictionary();
        dataDictionary2.setDateType("time");
        dataDictionary2.setCode("endDate");
        dataDictionary2.setValue(endDate);
        dataDictionaryService.create(dataDictionary);
        dataDictionaryService.create(dataDictionary2);
        return "ok";
    }

    //设置开始时间与结束时间
    @RequestMapping(value = "setting_date_type")
    public String settingDateType(@RequestParam(value = "code") String code,@RequestParam(value = "name") String name,@RequestParam(value = "des") String des){
        DataDictionnaryType dataDictionnaryType = new DataDictionnaryType();
        dataDictionnaryType.setCode(code);
        dataDictionnaryType.setName(name);
        dataDictionnaryType.setDescription(des);
        dataDictionnaryTypeService.create(dataDictionnaryType);
        return "ok";
    }

    @RequestMapping(value = "test")
    public void test() {
            Spider.create(jobProcess)
                    .addUrl("http://odds.500.com/fenxi/shuju-641855.shtml")
                    .thread(5)
                    .run();
        }

    @RequestMapping(value = "getProxyIp")
    public void getProxyIp() {
        Spider.create(getProxyIP)
                .addUrl("https://ip.jiangxianli.com/country/%E4%B8%AD%E5%9B%BD?country=%E4%B8%AD%E5%9B%BD")
                .thread(1)
                .run();
    }

    //无效的ip 返回true 有效的ip返回false
    boolean ifUseless(String ip,String port) {
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
            InetSocketAddress addr = new InetSocketAddress(ip,Integer.parseInt(port));
            java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, addr);
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
}
