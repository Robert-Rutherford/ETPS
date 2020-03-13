package com.etps.etps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EtpsApplication {

    public static void main(String[] args) {

        SpringApplication.run(EtpsApplication.class, args);

//        SpringApplicationBuilder builder = new SpringApplicationBuilder(EtpsApplication.class);
//        builder.headless(false);
//        ConfigurableApplicationContext context = builder.run(args);
    }

}
