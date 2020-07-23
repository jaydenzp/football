package com.zp.football.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "game")
public class Game {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //比赛id
    private String gameId;
    //比赛名称
    private String gameName;
    //比赛时间
    private String gameTime;
    //主队名称
    private String homeTeam;
    //主队跳转url
    private String homeTeamUrl;
    //主队得分
    private int homeTeamScore;
    //客队名称
    private String visitingTeam;
    //客队跳转url
    private String visitingTeamUrl;
    //客队得分
    private int visitingTeamScore;
    //半场比分
    private String halfScore;
    //状态
    private String status;
    //状态 分析url
    private String analysisUrl;

    //是否是胜负彩
    private boolean isSportsWinOrLose;
    //是否体彩
    private boolean isSports;
    //预测主队得分
    private double pHomeTeamScore;
    //预测客队得分
    private double pVisitingTeamScore;
    //预测胜负平
    private String pWinOrLose;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getHomeTeamUrl() {
        return homeTeamUrl;
    }

    public void setHomeTeamUrl(String homeTeamUrl) {
        this.homeTeamUrl = homeTeamUrl;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public String getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(String visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public String getVisitingTeamUrl() {
        return visitingTeamUrl;
    }

    public void setVisitingTeamUrl(String visitingTeamUrl) {
        this.visitingTeamUrl = visitingTeamUrl;
    }

    public int getVisitingTeamScore() {
        return visitingTeamScore;
    }

    public void setVisitingTeamScore(int visitingTeamScore) {
        this.visitingTeamScore = visitingTeamScore;
    }

    public String getHalfScore() {
        return halfScore;
    }

    public void setHalfScore(String halfScore) {
        this.halfScore = halfScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSportsWinOrLose() {
        return isSportsWinOrLose;
    }

    public void setSportsWinOrLose(boolean sportsWinOrLose) {
        isSportsWinOrLose = sportsWinOrLose;
    }

    public boolean isSports() {
        return isSports;
    }

    public void setSports(boolean sports) {
        isSports = sports;
    }

    public double getpHomeTeamScore() {
        return pHomeTeamScore;
    }

    public void setpHomeTeamScore(double pHomeTeamScore) {
        this.pHomeTeamScore = pHomeTeamScore;
    }

    public double getpVisitingTeamScore() {
        return pVisitingTeamScore;
    }

    public void setpVisitingTeamScore(double pVisitingTeamScore) {
        this.pVisitingTeamScore = pVisitingTeamScore;
    }

    public String getpWinOrLose() {
        return pWinOrLose;
    }

    public void setpWinOrLose(String pWinOrLose) {
        this.pWinOrLose = pWinOrLose;
    }

    public String getAnalysisUrl() {
        return analysisUrl;
    }

    public void setAnalysisUrl(String analysisUrl) {
        this.analysisUrl = analysisUrl;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameId='" + gameId + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameTime='" + gameTime + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", homeTeamUrl='" + homeTeamUrl + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", visitingTeam='" + visitingTeam + '\'' +
                ", visitingTeamUrl='" + visitingTeamUrl + '\'' +
                ", visitingTeamScore=" + visitingTeamScore +
                ", halfScore='" + halfScore + '\'' +
                ", status='" + status + '\'' +
                ", analysisUrl='" + analysisUrl + '\'' +
                ", isSportsWinOrLose=" + isSportsWinOrLose +
                ", isSports=" + isSports +
                ", pHomeTeamScore=" + pHomeTeamScore +
                ", pVisitingTeamScore=" + pVisitingTeamScore +
                ", pWinOrLose='" + pWinOrLose + '\'' +
                '}';
    }
}
