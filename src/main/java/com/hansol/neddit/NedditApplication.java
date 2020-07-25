package com.hansol.neddit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NedditApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NedditApplication.class)
                .properties("spring.config.name:common,application")
                .run(args);
    }

}
