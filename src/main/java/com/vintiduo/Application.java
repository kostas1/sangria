package com.vintiduo;

import com.vintiduo.page.PageResource;
import com.vintiduo.page.log.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Created by kostas on 2014.12.17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = PageResource.class) })
public class Application {

    private static Logger logger = Logger.forClass(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        for (String name: context.getBeanDefinitionNames()) {
            logger.info("main", "Got bean definition", "definition", name);
        }
    }
}
