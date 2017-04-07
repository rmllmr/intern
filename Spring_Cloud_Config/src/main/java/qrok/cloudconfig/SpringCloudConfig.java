package qrok.cloudconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 07.04.2017.
 */
@Configuration
@EnableAutoConfiguration
@RestController
public class SpringCloudConfig {

    @Value("${config.name}")
    String name;

    @RequestMapping("/")
    public String home() {
        return "Hello " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfig.class, args);
    }

}