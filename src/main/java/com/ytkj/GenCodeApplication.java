package com.ytkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.ytkj.*.mapper")
@EnableTransactionManagement
@EnableScheduling
public class GenCodeApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GenCodeApplication.class);
		//app.setWebEnvironment(false);//单元测试时打开可提高速度
		app.run(args);
	}
}
