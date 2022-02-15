package com.msa.module10.events.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoMessageListener {

    private Logger logger = LoggerFactory.getLogger(DemoMessageListener.class);

    @StreamListener(ConsumerChannels.DIRECTED)
    public void demo(String message) {
        logger.info("Demo: " + message);
    }


}

