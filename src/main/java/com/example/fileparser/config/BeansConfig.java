package com.example.fileparser.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BeansConfig {

    @Bean
    public ExecutorService getExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }
}
