package testconfigservice.service;

import lombok.ToString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import testconfigservice.data.LocalResumeConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 10.04.2017.
 */
@SpringBootApplication
public class LocalConfigService {

    public static void main(String[] args) {
        SpringApplication.run(LocalConfigService.class, args);
    }
}

@RestController
class LocalRestController {


    @RequestMapping("/first/default")
    public String mappingFirstClient() {

        LocalResumeConfig  localResumeConfig = new LocalResumeConfig();

        return localResumeConfig.toString();
    }
}


