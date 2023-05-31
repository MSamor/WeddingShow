package vip.maosi.weddingServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan({"vip.maosi.weddingServer.mapper"})
public class WeddingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingServerApplication.class, args);
	}

}
