package org.springframework.amqp.rabbit.stocks.main;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:server-bootstrap-config.xml")
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
	}
}
