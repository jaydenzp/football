package com.zp.football.domain;

import javax.persistence.*;

/**
 * , indexes = {
 *         @Index(name = "in_home_team",  columnList="homeTeam"),
 *         @Index(name = "in_visiting_team",  columnList="visitingTeam")}
 */

@Entity
@Table(name = "game_detail")
public class GameDetail {
    //主键
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    //比赛id
    @Id
    private String gameId;
    //主队名称
    private String homeTeam;
    //客队名称
    private String visitingTeam;
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

    //主队近10场进球数
    private int lastTenHomeGoals;

    //主队近10场失球数
    private int lastTenHomeLoseGoals;

    //客队近十场胜负平
    private int lastTenVisitingTeamWinTimes;
    private int lastTenVisitingTeamDrawTimes;
    private int lastTenVisitingTeamDefeatTimes;

    //客队近10场进球数
    private int lastTenVisitingGoals;

    //客队近10场失球数
    private int lastTenVisitingLoseGoals;

    //主队联赛胜率
    private double homeTeamRateOfWinning;
    //主队联赛主场胜率
    private double homeTeamHomeRateOfWinning;
    //主队联赛客场胜率
    private double homeTeamAwayRateOfWinning;
    //客队联赛胜率
    private double visitingTeamRateOfWinning;
    //客队联赛主场胜率
    private double visitingTeamHomeRateOfWinning;
    //客队联赛客场胜率
    private double visitingTeamAwayRateOfWinning;
    //主队联赛平均进球
    private double homeTeamAverageGoals;

    //主队联赛主场平均进球
    private double homeTeamHomeAverageGoals;

    //主队联赛平均失球
    private double homeTeamConcededAverage;

    //主队联赛主场平均失球
    private double homeTeamHomeConcededAverage;

    //客队联赛平均进球
    private double visitingTeamAverageGoals;

    //客队联赛客场平均进球
    private double visitingTeamVisitingAverageGoals;

    //客队联赛平均失球
    private double visitingTeamConcededAverage;

    //客队联赛客场平均失球
    private double visitingTeamVisitingConcededAverage;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(String visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public int getHomeTeamWinScore() {
        return homeTeamWinScore;
    }

    public void setHomeTeamWinScore(int homeTeamWinScore) {
        this.homeTeamWinScore = homeTeamWinScore;
    }

    public int getHomeTeamLoseScore() {
        return homeTeamLoseScore;
    }

    public void setHomeTeamLoseScore(int homeTeamLoseScore) {
        this.homeTeamLoseScore = homeTeamLoseScore;
    }

    public int getSmallBallTimes() {
        return smallBallTimes;
    }

    public void setSmallBallTimes(int smallBallTimes) {
        this.smallBallTimes = smallBallTimes;
    }

    public int getBigBallTimes() {
        return bigBallTimes;
    }

    public void setBigBallTimes(int bigBallTimes) {
        this.bigBallTimes = bigBallTimes;
    }

    public int getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(int winTimes) {
        this.winTimes = winTimes;
    }

    public int getDrawTimes() {
        return drawTimes;
    }

    public void setDrawTimes(int drawTimes) {
        this.drawTimes = drawTimes;
    }

    public int getDefeatTimes() {
        return defeatTimes;
    }

    public void setDefeatTimes(int defeatTimes) {
        this.defeatTimes = defeatTimes;
    }

    public int getLastTenHomeTeamWinTimes() {
        return lastTenHomeTeamWinTimes;
    }

    public void setLastTenHomeTeamWinTimes(int lastTenHomeTeamWinTimes) {
        this.lastTenHomeTeamWinTimes = lastTenHomeTeamWinTimes;
    }

    public int getLastTenHomeTeamDrawTimes() {
        return lastTenHomeTeamDrawTimes;
    }

    public void setLastTenHomeTeamDrawTimes(int lastTenHomeTeamDrawTimes) {
        this.lastTenHomeTeamDrawTimes = lastTenHomeTeamDrawTimes;
    }

    public int getLastTenHomeTeamDefeatTimes() {
        return lastTenHomeTeamDefeatTimes;
    }

    public void setLastTenHomeTeamDefeatTimes(int lastTenHomeTeamDefeatTimes) {
        this.lastTenHomeTeamDefeatTimes = lastTenHomeTeamDefeatTimes;
    }

    public int getLastTenHomeGoals() {
        return lastTenHomeGoals;
    }

    public void setLastTenHomeGoals(int lastTenHomeGoals) {
        this.lastTenHomeGoals = lastTenHomeGoals;
    }

    public int getLastTenHomeLoseGoals() {
        return lastTenHomeLoseGoals;
    }

    public void setLastTenHomeLoseGoals(int lastTenHomeLoseGoals) {
        this.lastTenHomeLoseGoals = lastTenHomeLoseGoals;
    }

    public int getLastTenVisitingTeamWinTimes() {
        return lastTenVisitingTeamWinTimes;
    }

    public void setLastTenVisitingTeamWinTimes(int lastTenVisitingTeamWinTimes) {
        this.lastTenVisitingTeamWinTimes = lastTenVisitingTeamWinTimes;
    }

    public int getLastTenVisitingTeamDrawTimes() {
        return lastTenVisitingTeamDrawTimes;
    }

    public void setLastTenVisitingTeamDrawTimes(int lastTenVisitingTeamDrawTimes) {
        this.lastTenVisitingTeamDrawTimes = lastTenVisitingTeamDrawTimes;
    }

    public int getLastTenVisitingTeamDefeatTimes() {
        return lastTenVisitingTeamDefeatTimes;
    }

    public void setLastTenVisitingTeamDefeatTimes(int lastTenVisitingTeamDefeatTimes) {
        this.lastTenVisitingTeamDefeatTimes = lastTenVisitingTeamDefeatTimes;
    }

    public int getLastTenVisitingGoals() {
        return lastTenVisitingGoals;
    }

    public void setLastTenVisitingGoals(int lastTenVisitingGoals) {
        this.lastTenVisitingGoals = lastTenVisitingGoals;
    }

    public int getLastTenVisitingLoseGoals() {
        return lastTenVisitingLoseGoals;
    }

    public void setLastTenVisitingLoseGoals(int lastTenVisitingLoseGoals) {
        this.lastTenVisitingLoseGoals = lastTenVisitingLoseGoals;
    }

    public double getHomeTeamRateOfWinning() {
        return homeTeamRateOfWinning;
    }

    public void setHomeTeamRateOfWinning(double homeTeamRateOfWinning) {
        this.homeTeamRateOfWinning = homeTeamRateOfWinning;
    }

    public double getHomeTeamHomeRateOfWinning() {
        return homeTeamHomeRateOfWinning;
    }

    public void setHomeTeamHomeRateOfWinning(double homeTeamHomeRateOfWinning) {
        this.homeTeamHomeRateOfWinning = homeTeamHomeRateOfWinning;
    }

    public double getHomeTeamAwayRateOfWinning() {
        return homeTeamAwayRateOfWinning;
    }

    public void setHomeTeamAwayRateOfWinning(double homeTeamAwayRateOfWinning) {
        this.homeTeamAwayRateOfWinning = homeTeamAwayRateOfWinning;
    }

    public double getVisitingTeamRateOfWinning() {
        return visitingTeamRateOfWinning;
    }

    public void setVisitingTeamRateOfWinning(double visitingTeamRateOfWinning) {
        this.visitingTeamRateOfWinning = visitingTeamRateOfWinning;
    }

    public double getVisitingTeamHomeRateOfWinning() {
        return visitingTeamHomeRateOfWinning;
    }

    public void setVisitingTeamHomeRateOfWinning(double visitingTeamHomeRateOfWinning) {
        this.visitingTeamHomeRateOfWinning = visitingTeamHomeRateOfWinning;
    }

    public double getVisitingTeamAwayRateOfWinning() {
        return visitingTeamAwayRateOfWinning;
    }

    public void setVisitingTeamAwayRateOfWinning(double visitingTeamAwayRateOfWinning) {
        this.visitingTeamAwayRateOfWinning = visitingTeamAwayRateOfWinning;
    }

    public double getHomeTeamAverageGoals() {
        return homeTeamAverageGoals;
    }

    public void setHomeTeamAverageGoals(double homeTeamAverageGoals) {
        this.homeTeamAverageGoals = homeTeamAverageGoals;
    }

    public double getHomeTeamHomeAverageGoals() {
        return homeTeamHomeAverageGoals;
    }

    public void setHomeTeamHomeAverageGoals(double homeTeamHomeAverageGoals) {
        this.homeTeamHomeAverageGoals = homeTeamHomeAverageGoals;
    }

    public double getHomeTeamConcededAverage() {
        return homeTeamConcededAverage;
    }

    public void setHomeTeamConcededAverage(double homeTeamConcededAverage) {
        this.homeTeamConcededAverage = homeTeamConcededAverage;
    }

    public double getHomeTeamHomeConcededAverage() {
        return homeTeamHomeConcededAverage;
    }

    public void setHomeTeamHomeConcededAverage(double homeTeamHomeConcededAverage) {
        this.homeTeamHomeConcededAverage = homeTeamHomeConcededAverage;
    }

    public double getVisitingTeamAverageGoals() {
        return visitingTeamAverageGoals;
    }

    public void setVisitingTeamAverageGoals(double visitingTeamAverageGoals) {
        this.visitingTeamAverageGoals = visitingTeamAverageGoals;
    }

    public double getVisitingTeamVisitingAverageGoals() {
        return visitingTeamVisitingAverageGoals;
    }

    public void setVisitingTeamVisitingAverageGoals(double visitingTeamVisitingAverageGoals) {
        this.visitingTeamVisitingAverageGoals = visitingTeamVisitingAverageGoals;
    }

    public double getVisitingTeamConcededAverage() {
        return visitingTeamConcededAverage;
    }

    public void setVisitingTeamConcededAverage(double visitingTeamConcededAverage) {
        this.visitingTeamConcededAverage = visitingTeamConcededAverage;
    }

    public double getVisitingTeamVisitingConcededAverage() {
        return visitingTeamVisitingConcededAverage;
    }

    public void setVisitingTeamVisitingConcededAverage(double visitingTeamVisitingConcededAverage) {
        this.visitingTeamVisitingConcededAverage = visitingTeamVisitingConcededAverage;
    }

    @Override
    public String toString() {
        return "GameDetail{" +
                "gameId='" + gameId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", visitingTeam='" + visitingTeam + '\'' +
                ", homeTeamWinScore=" + homeTeamWinScore +
                ", homeTeamLoseScore=" + homeTeamLoseScore +
                ", smallBallTimes=" + smallBallTimes +
                ", bigBallTimes=" + bigBallTimes +
                ", winTimes=" + winTimes +
                ", drawTimes=" + drawTimes +
                ", defeatTimes=" + defeatTimes +
                ", lastTenHomeTeamWinTimes=" + lastTenHomeTeamWinTimes +
                ", lastTenHomeTeamDrawTimes=" + lastTenHomeTeamDrawTimes +
                ", lastTenHomeTeamDefeatTimes=" + lastTenHomeTeamDefeatTimes +
                ", lastTenHomeGoals=" + lastTenHomeGoals +
                ", lastTenHomeLoseGoals=" + lastTenHomeLoseGoals +
                ", lastTenVisitingTeamWinTimes=" + lastTenVisitingTeamWinTimes +
                ", lastTenVisitingTeamDrawTimes=" + lastTenVisitingTeamDrawTimes +
                ", lastTenVisitingTeamDefeatTimes=" + lastTenVisitingTeamDefeatTimes +
                ", lastTenVisitingGoals=" + lastTenVisitingGoals +
                ", lastTenVisitingLoseGoals=" + lastTenVisitingLoseGoals +
                ", homeTeamRateOfWinning=" + homeTeamRateOfWinning +
                ", homeTeamHomeRateOfWinning=" + homeTeamHomeRateOfWinning +
                ", homeTeamAwayRateOfWinning=" + homeTeamAwayRateOfWinning +
                ", visitingTeamRateOfWinning=" + visitingTeamRateOfWinning +
                ", visitingTeamHomeRateOfWinning=" + visitingTeamHomeRateOfWinning +
                ", visitingTeamAwayRateOfWinning=" + visitingTeamAwayRateOfWinning +
                ", homeTeamAverageGoals=" + homeTeamAverageGoals +
                ", homeTeamHomeAverageGoals=" + homeTeamHomeAverageGoals +
                ", homeTeamConcededAverage=" + homeTeamConcededAverage +
                ", homeTeamHomeConcededAverage=" + homeTeamHomeConcededAverage +
                ", visitingTeamAverageGoals=" + visitingTeamAverageGoals +
                ", visitingTeamVisitingAverageGoals=" + visitingTeamVisitingAverageGoals +
                ", visitingTeamConcededAverage=" + visitingTeamConcededAverage +
                ", visitingTeamVisitingConcededAverage=" + visitingTeamVisitingConcededAverage +
                '}';
    }
}
