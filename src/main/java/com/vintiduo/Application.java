package com.vintiduo;

import com.vintiduo.page.PageResource;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Created by kostas on 2014.12.17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = PageResource.class) })
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        for (String name: context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        Object template = context.getBean("brokerMessagingTemplate");
    }
}
