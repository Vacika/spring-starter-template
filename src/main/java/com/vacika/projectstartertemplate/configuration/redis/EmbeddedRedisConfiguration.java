package com.vacika.projectstartertemplate.configuration.redis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
@ConditionalOnProperty(name = "embedded-redis.enabled", havingValue = "true", matchIfMissing = true)
public class EmbeddedRedisConfiguration {

    private final BeanFactory beanFactory;

    public EmbeddedRedisConfiguration(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Bean
    public static RedisServer redisEmbeddedServer() {
        return RedisServer.builder()
                .port(6379)
                .setting("appendonly no")
                // Uncomment the lines below if on Windows machine
                .setting("maxheap 128M")
                .setting("daemonize no")
                .build();
    }

    @PostConstruct
    public void startRedisServer() {
        RedisServer redisServer = beanFactory.getBean(RedisServer.class);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedisServer() {
        RedisServer redisServer = beanFactory.getBean(RedisServer.class);
        redisServer.stop();
    }
}
