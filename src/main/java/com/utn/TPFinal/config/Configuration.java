package com.utn.TPFinal.config;

import com.utn.TPFinal.session.SessionFilter;
import com.utn.TPFinal.session.SuperSessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@org.springframework.context.annotation.Configuration
@PropertySource("application.properties")
@EnableScheduling
@EnableCaching
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;

    @Autowired
    SuperSessionFilter superSessionFilter;

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/backoffice/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean myFilter1() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(superSessionFilter);
        registration.addUrlPatterns("/web/*");
        return registration;
    }
}
