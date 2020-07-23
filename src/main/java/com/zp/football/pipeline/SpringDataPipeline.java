package com.zp.football.pipeline;

import com.zp.football.domain.Game;
import com.zp.football.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 16:10
 */
@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private GameService gameService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取需要保存到MySQL的数据
        Game game = resultItems.get("game");

        //判断获取到的数据不为空
        if(game!=null) {
            //如果有值则进行保存
            this.gameService.create(game);
        }
    }
}
