package com.zp.football.domain;

import javax.persistence.*;

@Entity
@Table(name = "game_detail")
public class GameDetail {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //比赛id
    private String gameId;
    //主队名称
    private String homeTeam;
    //交战得分
    private int homeTeamWinScore;
    //交战失分分
    private int homeTeamLoseScore;
    //小球次数
    private int smallBallTimes;
    //大球次数
    private int bigBallTimes;
    //交战胜场次
    private int winTimes;
    //交战平场次
    private int drawTimes;
    //交战负场次
    private int defeatTimes;
    //主队近10场胜负平
    private int lastTenHomeTeamWinTimes;
    private int lastTenHomeTeamDrawTimes;
    private int lastTenHomeTeamDefeatTimes;
    //客队近十场胜负平
    private int lastTenVisitingTeamWinTimes;
    private int lastTenVisitingTeamDrawTimes;
    private int lastTenVisitingTeamDefeatTimes;
    //主队联赛胜率
    private double homeTeamRateOfWinning;
    //主队联赛主场胜率
    //主队联赛客场胜率
    //客队联赛主场胜率
    //客队联赛客场胜率
    //客队联赛客场胜率
    //主队平均进球
    //主队平均失球
    //客队平均进球
    //客队平均失球
}
