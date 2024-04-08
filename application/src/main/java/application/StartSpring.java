package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"scheduled", "fronius"})
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class StartSpring {

    public static void main(String[] args) {
        SpringApplication.run(StartSpring.class, args);
    }
}