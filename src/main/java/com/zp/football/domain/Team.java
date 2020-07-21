package com.zp.football.domain;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Team {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //球队id
    //号码
    //球员名
    //位置
    //年龄
    //身高
    //体重
    //出场次数
    //出场时间
    //进球
    //助攻
    //黄牌
    //红牌
    //身价
}
