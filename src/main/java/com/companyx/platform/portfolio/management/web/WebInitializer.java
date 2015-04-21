package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.config.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
         return application.sources(Application.class);
    }
}
