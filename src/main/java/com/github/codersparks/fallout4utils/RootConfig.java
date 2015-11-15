package com.github.codersparks.fallout4utils;

import com.github.codersparks.fallout4utils.interfaces.HackingSessionCalculationService;
import com.github.codersparks.fallout4utils.interfaces.HackingSessionService;
import com.github.codersparks.fallout4utils.repository.HackingSessionRepository;
import com.github.codersparks.fallout4utils.serivce.HackingSessionCalculationServiceImpl;
import com.github.codersparks.fallout4utils.serivce.HackingSessionServiceImpl;
import com.github.codersparks.fallout4utils.utils.HackingSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by codersparks on 14/11/2015.
 */
@Configuration
public class RootConfig {

    @Autowired
    private HackingSessionRepository repository;

    @Bean
    public HackingSessionService sessionService() {
        return new HackingSessionServiceImpl(repository, sessionUtils());
    }

    @Bean
    public HackingSessionCalculationService hackingSessionCalculationService() { return new HackingSessionCalculationServiceImpl(repository);}

    @Bean
    public HackingSessionUtils sessionUtils() {
        return new HackingSessionUtils();
    }
}
