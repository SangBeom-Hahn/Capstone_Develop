package com.kyonggi.Capstone_Develop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyonggi.Capstone_Develop.service.dto.auth.AccessTokenSaveResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, AccessTokenSaveResponseDto> redisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            ObjectMapper objectMapper
    ) {
        Jackson2JsonRedisSerializer<AccessTokenSaveResponseDto> jsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(AccessTokenSaveResponseDto.class);
        jsonRedisSerializer.setObjectMapper(objectMapper);

        RedisTemplate<String, AccessTokenSaveResponseDto> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        return redisTemplate;
    }
}
