package com.github.codersparks.fallout4utils;

import com.github.codersparks.fallout4utils.interfaces.SessionService;
import com.github.codersparks.fallout4utils.repository.SessionRepository;
import com.github.codersparks.fallout4utils.serivce.SessionServiceImpl;
import com.github.codersparks.fallout4utils.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by codersparks on 14/11/2015.
 */
@Configuration
public class RootConfig {

    @Autowired
    private SessionRepository repository;

    @Bean
    public SessionService sessionService() {
        return new SessionServiceImpl(repository, sessionUtils());
    }

    @Bean
    public SessionUtils sessionUtils() {
        return new SessionUtils();
    }
}
