package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan({"entity"})
@EnableJpaRepositories({"repository"})
@ComponentScan({"scheduled", "fronius", "mapper", "repository", "controller", "service", "config"})
@ConfigurationPropertiesScan({"config"})
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class StartSpring {

    public static void main(String[] args) {
        SpringApplication.run(StartSpring.class, args);
    }
}
