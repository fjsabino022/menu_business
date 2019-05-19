package com.fsabino.menu_business.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.fsabino.menu_business.converter",
        "com.fsabino.menu_business.repository",
        "com.fsabino.menu_business.service"})
public class ServiceConfig {
}
