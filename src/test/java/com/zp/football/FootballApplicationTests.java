package com.zp.football;

import com.zp.football.process.JobProcess;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

@SpringBootTest
class FootballApplicationTests {

	@Test
	void contextLoads() {
		String url = "http://live.500.com/wanchang.php?e=2014-01-01";
		Spider.create(new JobProcess())
				.addUrl(url)
				.thread(5)
				.run();
	}

}
