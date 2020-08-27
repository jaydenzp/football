package com.zp.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages={"com.zp.football.domain"})
@SpringBootApplication
@EnableScheduling//开启定时任务
public class FootballApplication {
	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

}
