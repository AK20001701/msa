package com.msa.module10;

import com.msa.module10.events.consumers.ConsumerChannels;
import com.msa.module10.events.consumers.DemoMessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class Module10Application {

	public static void main(String[] args) {
		SpringApplication.run(Module10Application.class, args);
	}

}
