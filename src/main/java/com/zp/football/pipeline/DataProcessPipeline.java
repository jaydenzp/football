package com.zp.football.pipeline;

import com.zp.football.domain.Game;
import com.zp.football.domain.GameDetail;
import com.zp.football.domain.Team;
import com.zp.football.service.GameDetailService;
import com.zp.football.service.GameService;
import com.zp.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 16:10
 */
@Component
public class DataProcessPipeline implements Pipeline {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameDetailService gameDetailService;

    @Autowired
    private TeamService teamService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取需要保存到MySQL的数据
        List<Game> games = resultItems.get("games");
        GameDetail gameDetail = resultItems.get("gameDetail");
        List<Team> teams = resultItems.get("teams");

        //判断获取到的数据不为空
        if(games!=null && games.size()>0) {
            //如果有值则进行保存
            this.gameService.saveAll(games);
        }

        //判断获取到的数据不为空
        if(gameDetail!=null) {
            //如果有值则进行保存
            this.gameDetailService.create(gameDetail);
        }

        //判断获取到的数据不为空
        if(teams!=null && teams.size()>0) {
            //如果有值则进行保存
            for (Team team :teams) {
                this.teamService.create(team);
            }

        }
    }
}
