package com.utn.TPFinal.config;

import com.utn.TPFinal.model.User;
import com.utn.TPFinal.model.UserType;
import com.utn.TPFinal.session.adminSessionFilter;
import com.utn.TPFinal.session.SessionFilter;
import com.utn.TPFinal.session.SessionManager;
import com.utn.TPFinal.session.SuperSessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestHeader;

@org.springframework.context.annotation.Configuration
@PropertySource("application.properties")
@EnableScheduling
@EnableCaching
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;
    SessionManager sessionManager;

    @Autowired
    SuperSessionFilter superSessionFilter;

    @Autowired
    adminSessionFilter adminSessionFilter;

    @Bean
    public FilterRegistrationBean myFilterEmployee() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(superSessionFilter);
        registration.addUrlPatterns("/backoffice/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean myFilterClient() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/web/*");

        return registration;
     }

    @Bean
    public FilterRegistrationBean myFilterAdmin() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(adminSessionFilter);
        registration.addUrlPatterns("/admin/*");
        return registration;
    }
}
