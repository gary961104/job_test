package com.example.job_test.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class ForexConfig {

    @Value("${webdriverpath}")
    private String webDriverPath;

    @Value("${webdriverurl}")
    private String webDriverUrl;

}
