package com.zp.football.domain;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //球队id
    private String teamId;
    //号码
    private String teamNumber;
    //球员名
    private String playerName;
    //位置
    private String position;
    //年龄
    private int age;
    //身高
    private double height;
    //体重
    private double weight;
    //出场次数
    private int appearance;
    //出场时间
    private int playingTime;
    //进球
    private int numberOfGoals;
    //助攻
    private int NumberOfGoalsAssists;
    //黄牌
    private int numberOfYellowCards;
    //红牌
    private int numberOfRedCards;
    //身价
    private int worth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAppearance() {
        return appearance;
    }

    public void setAppearance(int appearance) {
        this.appearance = appearance;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public int getNumberOfGoals() {
        return numberOfGoals;
    }

    public void setNumberOfGoals(int numberOfGoals) {
        this.numberOfGoals = numberOfGoals;
    }

    public int getNumberOfGoalsAssists() {
        return NumberOfGoalsAssists;
    }

    public void setNumberOfGoalsAssists(int numberOfGoalsAssists) {
        NumberOfGoalsAssists = numberOfGoalsAssists;
    }

    public int getNumberOfYellowCards() {
        return numberOfYellowCards;
    }

    public void setNumberOfYellowCards(int numberOfYellowCards) {
        this.numberOfYellowCards = numberOfYellowCards;
    }

    public int getNumberOfRedCards() {
        return numberOfRedCards;
    }

    public void setNumberOfRedCards(int numberOfRedCards) {
        this.numberOfRedCards = numberOfRedCards;
    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamId='" + teamId + '\'' +
                ", teamNumber='" + teamNumber + '\'' +
                ", playerName='" + playerName + '\'' +
                ", position='" + position + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", appearance=" + appearance +
                ", playingTime=" + playingTime +
                ", numberOfGoals=" + numberOfGoals +
                ", NumberOfGoalsAssists=" + NumberOfGoalsAssists +
                ", numberOfYellowCards=" + numberOfYellowCards +
                ", numberOfRedCards=" + numberOfRedCards +
                ", worth=" + worth +
                '}';
    }
}
